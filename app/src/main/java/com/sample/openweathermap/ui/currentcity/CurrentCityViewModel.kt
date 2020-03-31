package com.sample.openweathermap.ui.currentcity

import com.sample.openweathermap.data.repository.WeatherRepository
import com.sample.openweathermap.ui.base.BaseViewModel
import javax.inject.Inject

class CurrentCityViewModel @Inject constructor(repository: WeatherRepository): BaseViewModel() {

}
