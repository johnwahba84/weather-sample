package com.sample.openweathermap.data.repository

import com.sample.openweathermap.constants.AppConstants
import com.sample.openweathermap.data.remote.ApiWeatherService
import com.sample.openweathermap.domain.model.forecast.ForecastRequest
import com.sample.openweathermap.domain.model.forecast.ForecastResponse
import com.sample.openweathermap.domain.model.weather.WeatherResponse
import com.sample.openweathermap.utils.network.ApiResponse
import com.sample.openweathermap.utils.network.NetworkBoundNoCacheResource
import com.sample.openweathermap.vo.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class WeatherAppRepositoryImp @Inject constructor(
    private val apiWeatherService: ApiWeatherService
): WeatherAppRepository {

    @FlowPreview
    @ExperimentalCoroutinesApi
    override suspend fun weatherByCityName(cityName: String?): Flow<Resource<WeatherResponse>> {
        return object : NetworkBoundNoCacheResource<WeatherResponse>() {
            override suspend fun fetchFromNetwork(): ApiResponse<WeatherResponse> {
                return ApiResponse.create(apiWeatherService.weatherByCityName(
                    cityName,
                    AppConstants.ApiConfiguration.API_ID
                ))
            }
        }.asFlow()
    }

    @FlowPreview
    @ExperimentalCoroutinesApi
    override suspend fun forecastByLocation(request: ForecastRequest?): Flow<Resource<ForecastResponse>> {
        return object : NetworkBoundNoCacheResource<ForecastResponse>() {
            override suspend fun fetchFromNetwork(): ApiResponse<ForecastResponse> {
                return ApiResponse.create(apiWeatherService.forecastByLocation(
                    request?.lat, request?.lon,
                    AppConstants.ApiConfiguration.API_ID
                ))
            }
        }.asFlow()
    }
}