package com.sample.openweathermap.di.module

import com.sample.openweathermap.constants.AppConstants
import com.sample.openweathermap.utils.LiveDataCallAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [ApiModule::class])
class NetworkModule {

    @Singleton
    @Provides
    fun provideGithubService(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(AppConstants.ApiConfiguration.URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .build()
    }
}