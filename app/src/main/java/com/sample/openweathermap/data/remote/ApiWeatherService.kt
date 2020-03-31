package com.sample.openweathermap.data.remote

import androidx.lifecycle.LiveData
import com.sample.openweathermap.constants.AppConstants
import com.sample.openweathermap.model.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiWeatherService {

    @GET(AppConstants.ApiPath.WEATHER)
    fun weatherByCityName(@Query("q") cities: String?, @Query("appid") appId: String): LiveData<ApiResponse<WeatherResponse>>

    @GET(AppConstants.ApiPath.WEATHER)
    fun weatherByLocation(@Query("lat") lat: String?, @Query("long") long: String?, @Query("appid") appId: String): LiveData<ApiResponse<WeatherResponse>>
}