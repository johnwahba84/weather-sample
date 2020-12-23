package com.sample.openweathermap.domain.usecase

import com.sample.openweathermap.data.repository.WeatherAppRepository
import com.sample.openweathermap.domain.model.forecast.ForecastRequest
import com.sample.openweathermap.domain.model.forecast.ForecastResponse
import com.sample.openweathermap.vo.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetForecastByLocation @Inject constructor(private val repository: WeatherAppRepository) {

    suspend operator fun invoke(request: ForecastRequest?): Flow<Resource<ForecastResponse>> {
        return repository.forecastByLocation(request)
    }
}