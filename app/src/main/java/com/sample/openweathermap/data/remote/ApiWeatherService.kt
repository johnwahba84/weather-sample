package com.sample.openweathermap.data.remote

import com.sample.openweathermap.constants.AppConstants
import com.sample.openweathermap.data.model.forecast.ForecastResponse
import com.sample.openweathermap.data.model.weather.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiWeatherService {

    @GET(AppConstants.ApiPath.WEATHER)
    suspend fun weatherByCityName(@Query("q") cities: String?, @Query("appid") appId: String): Response<WeatherResponse>

    @GET(AppConstants.ApiPath.FORECAST)
    suspend fun forecastByLocation(@Query("lat") lat: String?, @Query("lon") lon: String?, @Query("appid") appId: String): Response<ForecastResponse>
}