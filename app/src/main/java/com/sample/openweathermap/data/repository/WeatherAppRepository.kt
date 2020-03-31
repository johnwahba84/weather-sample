package com.sample.openweathermap.data.repository

import androidx.lifecycle.LiveData
import com.sample.openweathermap.constants.AppConstants
import com.sample.openweathermap.data.remote.ApiResponse
import com.sample.openweathermap.data.remote.ApiWeatherService
import com.sample.openweathermap.model.forecast.ForecastResponse
import com.sample.openweathermap.model.weather.WeatherResponse
import com.sample.openweathermap.vo.Resource
import javax.inject.Inject

class WeatherAppRepository @Inject constructor(
    private val apiWeatherService: ApiWeatherService
) {

    fun weatherByCityName(cityName: String?): LiveData<Resource<WeatherResponse>> {
        return object : NetworkBoundNoCacheResource<WeatherResponse>() {
            override fun loadFromNetwork(): LiveData<ApiResponse<WeatherResponse>> {
                return apiWeatherService.weatherByCityName(
                    cityName,
                    AppConstants.ApiConfiguration.API_ID
                )
            }
        }.asLiveData()
    }

    fun forecastByLocation(lat: String?, lon: String?): LiveData<Resource<ForecastResponse>> {
        return object : NetworkBoundNoCacheResource<ForecastResponse>() {
            override fun loadFromNetwork(): LiveData<ApiResponse<ForecastResponse>> {
                return apiWeatherService.ForecastByLocation(
                    lat, lon,
                    AppConstants.ApiConfiguration.API_ID
                )
            }
        }.asLiveData()
    }
}