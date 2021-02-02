package com.sample.openweathermap.di.builder

import com.sample.openweathermap.ui.home.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilderModule {

    @ContributesAndroidInjector
    abstract fun contributeMainFragment(): HomeFragment
}