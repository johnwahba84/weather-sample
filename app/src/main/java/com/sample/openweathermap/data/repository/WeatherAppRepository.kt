package com.sample.openweathermap.data.repository

import com.sample.openweathermap.domain.model.forecast.ForecastRequest
import com.sample.openweathermap.domain.model.forecast.ForecastResponse
import com.sample.openweathermap.domain.model.weather.WeatherResponse
import com.sample.openweathermap.vo.Resource
import kotlinx.coroutines.flow.Flow

interface WeatherAppRepository {
    suspend fun weatherByCityName(cityName: String?): Flow<Resource<WeatherResponse>>
    suspend fun forecastByLocation(request: ForecastRequest?): Flow<Resource<ForecastResponse>>
}