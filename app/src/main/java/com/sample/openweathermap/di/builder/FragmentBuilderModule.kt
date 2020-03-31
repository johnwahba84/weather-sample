package com.sample.openweathermap.di.builder

import com.sample.openweathermap.ui.weather.WeatherFragment
import com.sample.openweathermap.ui.forcast.ForecastFragment
import com.sample.openweathermap.ui.home.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilderModule {

    @ContributesAndroidInjector
    abstract fun contributeMainFragment(): HomeFragment

    @ContributesAndroidInjector
    abstract fun contributeChooseCitiesFragment(): WeatherFragment

    @ContributesAndroidInjector
    abstract fun contributeCurrentCityFragment(): ForecastFragment
}