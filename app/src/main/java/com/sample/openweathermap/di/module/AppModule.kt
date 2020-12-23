package com.sample.openweathermap.di.module

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.sample.openweathermap.constants.AppConstants
import com.sample.openweathermap.data.local.dp.AppDatabase
import com.sample.openweathermap.di.module.network.NetworkModule
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [NetworkModule::class, ViewModelModule::class])
class AppModule {

    @Provides
    @Singleton
    internal fun provideContext(application: Application): Context {
        return application
    }

    @Provides
    @Singleton
    internal fun provideDb(application: Application): AppDatabase {
        return Room
            .databaseBuilder(application, AppDatabase::class.java, AppConstants.Database.NAME)
            .fallbackToDestructiveMigration()
            .build()
    }
}