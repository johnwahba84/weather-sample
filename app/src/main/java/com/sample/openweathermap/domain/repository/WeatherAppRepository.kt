package com.sample.openweathermap.domain.repository

import com.sample.openweathermap.data.model.forecast.ForecastRequest
import com.sample.openweathermap.data.model.forecast.ForecastResponse
import com.sample.openweathermap.data.model.weather.WeatherResponse
import com.sample.openweathermap.domain.model.forecast.ForecastModel
import com.sample.openweathermap.domain.model.weather.WeatherModel
import com.sample.openweathermap.vo.Resource
import kotlinx.coroutines.flow.Flow

interface WeatherAppRepository {
    suspend fun weatherByCityName(cityName: String?): Flow<Resource<WeatherModel>>
    suspend fun forecastByLocation(request: ForecastRequest?): Flow<Resource<ForecastModel>>
}