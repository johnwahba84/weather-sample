package com.sample.openweathermap.ui.forcast

import android.app.Application
import com.sample.openweathermap.data.repository.WeatherRepository
import com.sample.openweathermap.ui.base.BaseViewModel
import javax.inject.Inject

class ForecastViewModel @Inject constructor(
    private val context: Application,
    repository: WeatherRepository
) : BaseViewModel(context) {


}
