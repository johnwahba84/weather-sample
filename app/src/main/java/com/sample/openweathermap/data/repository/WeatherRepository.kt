package com.sample.openweathermap.data.repository

import androidx.lifecycle.LiveData
import com.sample.openweathermap.constants.AppConstants
import com.sample.openweathermap.data.remote.ApiResponse
import com.sample.openweathermap.data.remote.ApiWeatherService
import com.sample.openweathermap.model.WeatherResponse
import com.sample.openweathermap.vo.Resource
import javax.inject.Inject

class WeatherRepository @Inject constructor(
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
}