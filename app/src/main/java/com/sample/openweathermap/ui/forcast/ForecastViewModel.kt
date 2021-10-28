package com.sample.openweathermap.ui.forcast

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import com.sample.openweathermap.data.model.forecast.ForecastRequest
import com.sample.openweathermap.data.model.forecast.ForecastResponse
import com.sample.openweathermap.domain.model.forecast.ForecastModel
import com.sample.openweathermap.domain.usecase.GetForecastByLocation
import com.sample.openweathermap.ui.base.BaseViewModel
import com.sample.openweathermap.utils.AbsentLiveData
import com.sample.openweathermap.utils.network.CoroutinesLiveDataHelper
import com.sample.openweathermap.vo.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ForecastViewModel @Inject constructor(
    val context: Application,
    val getForecastByLocation: GetForecastByLocation
) : BaseViewModel(context) {

    val showLoading = ObservableField<Boolean>()

    var callAdapter = MutableLiveData<List<ForecastModel.Forecast>>()

    var forecastRequest = MutableLiveData<ForecastRequest>()
    var forecastResponse: LiveData<Resource<ForecastModel>> = Transformations
        .switchMap(forecastRequest) { forecastRequest ->
            if (null == forecastRequest)
                AbsentLiveData.create()
            else
                object :
                    CoroutinesLiveDataHelper<ForecastModel>(viewModelScope.coroutineContext) {
                    override suspend fun loadFromNetwork(): Flow<Resource<ForecastModel>> {
                        return getForecastByLocation(forecastRequest)
                    }
                }.asLiveData()
        }

    fun processResponse(data: ForecastModel?) {
        callAdapter.value = data?.list
    }

    fun callForecastApi(lat: String, lon: String) {
        forecastRequest.value =
            ForecastRequest(
                lat = lat,
                lon = lon
            )
    }
}
