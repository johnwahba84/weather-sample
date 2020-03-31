package com.sample.openweathermap.data.repository

import androidx.lifecycle.LiveData
import com.sample.openweathermap.constants.AppConstants
import com.sample.openweathermap.data.remote.ApiResponse
import com.sample.openweathermap.data.remote.ApiWeatherService
import com.sample.openweathermap.model.Response
import com.sample.openweathermap.vo.Resource
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val apiWeatherService: ApiWeatherService
) {

    fun weatherByCityName(cityName: String?): LiveData<Resource<Response>> {
        return object : NetworkBoundNoCacheResource<Response>() {
            override fun loadFromNetwork(): LiveData<ApiResponse<Response>> {
                return apiWeatherService.weatherByCityName(
                    cityName,
                    AppConstants.ApiConfiguration.API_ID
                )
            }
        }.asLiveData()
    }
}