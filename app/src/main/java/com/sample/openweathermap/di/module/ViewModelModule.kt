package com.sample.openweathermap.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sample.openweathermap.di.keys.ViewModelKey
import com.sample.openweathermap.ui.MainViewModel
import com.sample.openweathermap.ui.weather.WeatherViewModel
import com.sample.openweathermap.ui.forcast.ForecastViewModel
import com.sample.openweathermap.ui.home.HomeViewModel
import com.sample.openweathermap.viewmodel.WeatherViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    fun bindHomeViewModel(homeViewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(WeatherViewModel::class)
    fun bindChooseCitiesViewModel(weatherViewModel: WeatherViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ForecastViewModel::class)
    fun bindCurrentCityViewModel(forecastViewModel: ForecastViewModel): ViewModel

    @Binds
    fun bindWeatherViewModelFactory(weatherViewModelFactory: WeatherViewModelFactory): ViewModelProvider.Factory
}