package com.sample.openweathermap.di.module.network

import com.sample.openweathermap.data.local.dp.AppDatabase
import com.sample.openweathermap.data.local.dp.dao.WeatherDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideForecastDao(db: AppDatabase): WeatherDao {
        return db.weatherDao()
    }
}