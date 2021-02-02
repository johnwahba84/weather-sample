package com.sample.openweathermap.di.module.network

import com.sample.openweathermap.data.repository.SignZYRepository
import com.sample.openweathermap.data.repository.SignZYRepositoryImp
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {
    @Binds
    fun bindWeatherAppRepository(signZYRepositoryImp: SignZYRepositoryImp): SignZYRepository
}