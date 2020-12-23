package com.sample.openweathermap.data.remote

import androidx.lifecycle.LiveData
import com.sample.openweathermap.constants.AppConstants
import com.sample.openweathermap.domain.model.forecast.ForecastResponse
import com.sample.openweathermap.domain.model.weather.WeatherResponse
import com.sample.openweathermap.utils.network.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiWeatherService {

    @GET(AppConstants.ApiPath.WEATHER)
    fun weatherByCityName(@Query("q") cities: String?, @Query("appid") appId: String): LiveData<ApiResponse<WeatherResponse>>

    @GET(AppConstants.ApiPath.FORECAST)
    fun ForecastByLocation(@Query("lat") lat: String?, @Query("lon") lon: String?, @Query("appid") appId: String): LiveData<ApiResponse<ForecastResponse>>
}