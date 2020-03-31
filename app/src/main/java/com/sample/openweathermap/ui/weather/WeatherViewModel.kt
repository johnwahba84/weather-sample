package com.sample.openweathermap.ui.weather

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.sample.openweathermap.R
import com.sample.openweathermap.data.repository.WeatherAppRepository
import com.sample.openweathermap.model.weather.WeatherResponse
import com.sample.openweathermap.ui.base.BaseViewModel
import com.sample.openweathermap.utils.AbsentLiveData
import com.sample.openweathermap.vo.Resource
import javax.inject.Inject

class WeatherViewModel @Inject constructor(
    private val context: Application,
    repository: WeatherAppRepository
) : BaseViewModel(context) {

    private var cityList = ArrayList<String>()
    private var weatherResponseList = ArrayList<WeatherResponse>()

    val citiesNames = ObservableField<String>()
    val citiesNamesError = ObservableField<String>()

    val showLoading = ObservableField<Boolean>()

    var callAdapter = MutableLiveData<List<WeatherResponse>>()

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
            hideKeyboard.value = true
            weatherResponseList.clear()
            weatherRequest.value = cityList[0]
        }
    }

    private fun formValidated(): Boolean {

        val names = citiesNames.get()?.split(",")

        cityList.clear()
        if (!names.isNullOrEmpty())
            for (name in names)
                cityList.add(name)


        if (cityList.size in 3..7) {
            citiesNamesError.set(null)
            return true
        }

        citiesNamesError.set(context.getString(R.string.weather_error))
        return false
    }

    fun processResponse(data: WeatherResponse?) {

        data?.let { weatherResponseList.add(it) }

        cityList.removeAt(0)

        if (cityList.isNullOrEmpty())
            callAdapter.value = weatherResponseList
        else
            weatherRequest.value = cityList[0]
    }
}
