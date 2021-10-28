package com.sample.openweathermap.domain.usecase

import com.sample.openweathermap.domain.repository.WeatherAppRepository
import com.sample.openweathermap.data.model.forecast.ForecastRequest
import com.sample.openweathermap.data.model.forecast.ForecastResponse
import com.sample.openweathermap.domain.model.forecast.ForecastModel
import com.sample.openweathermap.vo.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetForecastByLocation @Inject constructor(private val repository: WeatherAppRepository) {

    suspend operator fun invoke(request: ForecastRequest?): Flow<Resource<ForecastModel>> {
        return repository.forecastByLocation(request)
    }
}