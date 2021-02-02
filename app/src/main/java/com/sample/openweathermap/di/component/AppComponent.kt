package com.sample.openweathermap.di.component

import android.app.Application
import com.sample.openweathermap.SignZYApplication
import com.sample.openweathermap.di.builder.ActivityBuilderModule
import com.sample.openweathermap.di.module.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, AppModule::class, ActivityBuilderModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(signZYApplication: SignZYApplication)
}