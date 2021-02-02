package com.sample.openweathermap.di.module.network

import com.sample.openweathermap.data.remote.ApiSignZYService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class ApiModule {

    @Provides
    @Singleton
    internal fun provideApiWeatherService(retrofit: Retrofit): ApiSignZYService {
        return retrofit.create(ApiSignZYService::class.java)
    }
}