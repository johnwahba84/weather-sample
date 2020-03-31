package com.sample.openweathermap.ui.choosecities

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.sample.openweathermap.R
import com.sample.openweathermap.data.repository.WeatherRepository
import com.sample.openweathermap.model.WeatherResponse
import com.sample.openweathermap.ui.base.BaseViewModel
import com.sample.openweathermap.utils.AbsentLiveData
import com.sample.openweathermap.vo.Resource
import javax.inject.Inject

class ChooseCitiesViewModel @Inject constructor(
    private val context: Application,
    repository: WeatherRepository
) : BaseViewModel(context) {

    private var cityList: List<String>? = null

    val citiesNames = ObservableField<String>()
    val citiesNamesError = ObservableField<String>()

    var weatherRequest = MutableLiveData<String>()
    var weatherResponse: LiveData<Resource<WeatherResponse>> = Transformations
        .switchMap(weatherRequest) { weatherRequest ->
            if (null == weatherRequest)
                AbsentLiveData.create()
            else
                repository.weatherByCityName(weatherRequest)
        }

    fun onFetchClicked() {

        if (formValidated()) {
            for (cityName in cityList!!)
                weatherRequest.value = cityName
        }
    }

    private fun formValidated(): Boolean {

        cityList = citiesNames.get()?.split(",")

        if (cityList?.size in 3..7) {
            citiesNamesError.set(null)
            return true
        }

        citiesNamesError.set(context.getString(R.string.choose_cities_error))
        return false
    }

    fun processResponse(data: WeatherResponse?) {

    }
}
