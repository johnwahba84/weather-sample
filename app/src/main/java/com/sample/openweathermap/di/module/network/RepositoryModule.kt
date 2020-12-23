package com.sample.openweathermap.di.module.network

import com.sample.openweathermap.data.repository.WeatherAppRepository
import com.sample.openweathermap.data.repository.WeatherAppRepositoryImp
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {
    @Binds
    fun bindWeatherAppRepository(weatherAppRepositoryImp: WeatherAppRepositoryImp): WeatherAppRepository
}