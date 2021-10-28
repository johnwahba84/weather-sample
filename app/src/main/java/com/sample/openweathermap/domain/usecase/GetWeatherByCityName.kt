package com.sample.openweathermap.domain.usecase

import com.sample.openweathermap.domain.repository.WeatherAppRepository
import com.sample.openweathermap.data.model.weather.WeatherResponse
import com.sample.openweathermap.domain.model.weather.WeatherModel
import com.sample.openweathermap.vo.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetWeatherByCityName @Inject constructor(private val repository: WeatherAppRepository){

    suspend operator fun invoke(cityName: String?): Flow<Resource<WeatherModel>>{
        return repository.weatherByCityName(cityName)
    }
}