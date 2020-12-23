package com.sample.openweathermap.di.module

import android.app.Application
import android.content.Context
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
}