package com.sample.openweathermap.data.repository

import com.android.example.github.vo.Status
import com.sample.openweathermap.constants.AppConstants
import com.sample.openweathermap.data.local.dp.dao.WeatherDao
import com.sample.openweathermap.data.mapper.forecast.ForecastMapper
import com.sample.openweathermap.data.mapper.weather.WeatherMapper
import com.sample.openweathermap.data.model.forecast.ForecastRequest
import com.sample.openweathermap.data.model.forecast.ForecastResponse
import com.sample.openweathermap.data.model.weather.WeatherResponse
import com.sample.openweathermap.data.remote.ApiWeatherService
import com.sample.openweathermap.domain.model.forecast.ForecastModel
import com.sample.openweathermap.domain.model.weather.WeatherModel
import com.sample.openweathermap.domain.repository.WeatherAppRepository
import com.sample.openweathermap.utils.network.ApiResponse
import com.sample.openweathermap.utils.network.NetworkBoundNoCacheResource
import com.sample.openweathermap.utils.network.NetworkBoundResource
import com.sample.openweathermap.vo.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class WeatherAppRepositoryImp @Inject constructor(
    private val apiWeatherService: ApiWeatherService,
    private val weatherDao: WeatherDao,
    private val weatherMapper: dagger.Lazy<WeatherMapper>,
    private val forecastMapper: dagger.Lazy<ForecastMapper>
) : WeatherAppRepository {

    @FlowPreview
    @ExperimentalCoroutinesApi
    override suspend fun weatherByCityName(cityName: String?): Flow<Resource<WeatherModel>> {
        return object : NetworkBoundNoCacheResource<WeatherResponse>() {
            override suspend fun fetchFromNetwork(): ApiResponse<WeatherResponse> {
                return ApiResponse.create(
                    apiWeatherService.weatherByCityName(
                        cityName,
                        AppConstants.ApiConfiguration.API_ID
                    )
                )
            }
        }.asFlow().map {

            when (it.status) {
                Status.SUCCESS -> {
                    Resource.success(weatherMapper.get().toWeatherDetails(it.data!!))
                }
                Status.LOADING -> {
                    Resource.loading(null)
                }
                Status.ERROR -> {
                    Resource.error(it.message.toString(), null)
                }
            }
        }
    }

    @FlowPreview
    @ExperimentalCoroutinesApi
    override suspend fun forecastByLocation(request: ForecastRequest?): Flow<Resource<ForecastModel>> {
        return object : NetworkBoundResource<ForecastResponse>() {

            override suspend fun fetchFromNetwork(): ApiResponse<ForecastResponse> {
                return ApiResponse.create(
                    apiWeatherService.forecastByLocation(
                        request?.lat, request?.lon,
                        AppConstants.ApiConfiguration.API_ID
                    )
                )
            }

            override suspend fun saveNetworkResult(data: ForecastResponse) {
                weatherDao.updateForecast(data)
            }

            override fun shouldFetch(data: ForecastResponse?): Boolean {
                return data == null
            }

            override fun loadFromDb(): Flow<ForecastResponse> {
                return weatherDao.getForecast()
            }
        }.asFlow().map {

            when (it.status) {
                Status.SUCCESS -> {
                    Resource.success(forecastMapper.get().toForecastDetails(it.data!!))
                }
                Status.LOADING -> {
                    Resource.loading(null)
                }
                Status.ERROR -> {
                    Resource.error(it.message.toString(), null)
                }
            }
        }
    }
}