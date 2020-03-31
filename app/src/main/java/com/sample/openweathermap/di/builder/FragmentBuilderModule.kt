package com.sample.openweathermap.di.builder

import com.sample.openweathermap.ui.choosecities.ChooseCitiesFragment
import com.sample.openweathermap.ui.currentcity.CurrentCityFragment
import com.sample.openweathermap.ui.home.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilderModule {

    @ContributesAndroidInjector
    abstract fun contributeMainFragment(): HomeFragment

    @ContributesAndroidInjector
    abstract fun contributeChooseCitiesFragment(): ChooseCitiesFragment

    @ContributesAndroidInjector
    abstract fun contributeCurrentCityFragment(): CurrentCityFragment
}