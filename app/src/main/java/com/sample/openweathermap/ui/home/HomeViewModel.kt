package com.sample.openweathermap.ui.home

import android.app.Application
import android.widget.Toast
import androidx.databinding.Observable
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import com.sample.openweathermap.domain.model.AuthenticateResponse
import com.sample.openweathermap.domain.model.UploadRequest
import com.sample.openweathermap.domain.model.UploadResponse
import com.sample.openweathermap.domain.usecase.Authenticate
import com.sample.openweathermap.domain.usecase.Upload
import com.sample.openweathermap.ui.base.BaseViewModel
import com.sample.openweathermap.utils.AbsentLiveData
import com.sample.openweathermap.utils.NavigationCommand
import com.sample.openweathermap.utils.SingleLiveEvent
import com.sample.openweathermap.utils.network.CoroutinesLiveDataHelper
import com.sample.openweathermap.vo.Resource
import kotlinx.coroutines.flow.Flow
import java.io.File
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val context: Application,
    private val authenticate: Authenticate,
    private val upload: Upload
) : BaseViewModel(context) {

    val showLoading = ObservableBoolean()

    val openCameraIntent = SingleLiveEvent<String>()

    private var authenticateRequest = MutableLiveData<Boolean>()
    var authenticateResponse: LiveData<Resource<AuthenticateResponse>> = Transformations
        .switchMap(authenticateRequest) { authenticateRequest ->
            if (null == authenticateRequest)
                AbsentLiveData.create()
            else
                object :
                    CoroutinesLiveDataHelper<AuthenticateResponse>(viewModelScope.coroutineContext) {
                    override suspend fun loadFromNetwork(): Flow<Resource<AuthenticateResponse>> {
                        return authenticate()
                    }
                }.asLiveData()
        }

    private var uploadRequest = MutableLiveData<UploadRequest>()
    var uploadResponse: LiveData<Resource<UploadResponse>> = Transformations
        .switchMap(uploadRequest) { uploadRequest ->
            if (null == uploadRequest)
                AbsentLiveData.create()
            else
                object : CoroutinesLiveDataHelper<UploadResponse>(viewModelScope.coroutineContext) {
                    override suspend fun loadFromNetwork(): Flow<Resource<UploadResponse>> {
                        return upload(uploadRequest)
                    }
                }.asLiveData()
        }

    fun onAuthenticateClicked() {
        authenticateRequest.value = true
    }

    fun onEmiratesIDClicked() {
        openCameraIntent.value = "EmiratesID"
    }

    fun onPassportClicked() {
        openCameraIntent.value = "Passport"
    }

    fun processAuthenticateResponse(data: AuthenticateResponse?) {
        Toast.makeText(context, "Authentication Success", Toast.LENGTH_LONG).show()
    }

    fun processUploadResponse(data: UploadResponse?) {
        Toast.makeText(context, "Upload Success", Toast.LENGTH_LONG).show()
    }

    fun uploadApi(file: File) {
        uploadRequest.value = UploadRequest(
            file = file,
            optimize = "true",
            key = "1234",
            ttl = "10 mins",
            authorization = authenticateResponse.value?.data?.id!!
        )
    }
}
