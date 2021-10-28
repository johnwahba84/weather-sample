package com.sample.openweathermap.di.module.network

import com.sample.openweathermap.data.remote.ApiWeatherService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class ApiModule {

    @Provides
    @Singleton
    internal fun provideApiWeatherService(retrofit: Retrofit): ApiWeatherService {
        return retrofit.create(ApiWeatherService::class.java)
    }
}