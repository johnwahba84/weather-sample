package com.sample.openweathermap.ui.forcast

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.sample.openweathermap.data.repository.WeatherAppRepository
import com.sample.openweathermap.model.forecast.ForecastRequest
import com.sample.openweathermap.model.forecast.ForecastResponse
import com.sample.openweathermap.ui.base.BaseViewModel
import com.sample.openweathermap.utils.AbsentLiveData
import com.sample.openweathermap.vo.Resource
import javax.inject.Inject

class ForecastViewModel @Inject constructor(
    private val context: Application,
    repository: WeatherAppRepository
) : BaseViewModel(context) {

    val showLoading = ObservableField<Boolean>()

    var callAdapter = MutableLiveData<List<ForecastResponse.Forecast>>()

    var forecastRequest = MutableLiveData<ForecastRequest>()
    var forecastResponse: LiveData<Resource<ForecastResponse>> = Transformations
        .switchMap(forecastRequest) { forecastRequest ->
            if (null == forecastRequest)
                AbsentLiveData.create()
            else
                repository.forecastByLocation(
                    lat = forecastRequest.lat,
                    lon = forecastRequest.lon
                )
        }

    fun processResponse(data: ForecastResponse?) {
        callAdapter.value = data?.list
    }

    fun callForecastApi(lat: String, lon: String) {
        forecastRequest.value = ForecastRequest(lat = lat, lon = lon)
    }
}
