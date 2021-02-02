package com.sample.openweathermap.ui.home

import android.app.Application
import android.widget.Toast
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import com.sample.openweathermap.domain.model.*
import com.sample.openweathermap.domain.usecase.Authenticate
import com.sample.openweathermap.domain.usecase.FileExtraction
import com.sample.openweathermap.domain.usecase.ImageQuality
import com.sample.openweathermap.domain.usecase.Upload
import com.sample.openweathermap.ui.base.BaseViewModel
import com.sample.openweathermap.utils.AbsentLiveData
import com.sample.openweathermap.utils.SingleLiveEvent
import com.sample.openweathermap.utils.network.CoroutinesLiveDataHelper
import com.sample.openweathermap.vo.Resource
import kotlinx.coroutines.flow.Flow
import java.io.File
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val context: Application,
    private val authenticate: Authenticate,
    private val upload: Upload,
    private val imageQuality: ImageQuality,
    private val fileExtraction: FileExtraction
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
                        return upload(authenticateResponse.value?.data?.id!!, uploadRequest)
                    }
                }.asLiveData()
        }

    private var imageQualityRequest = MutableLiveData<ImageQualityRequest>()
    var imageQualityResponse: LiveData<Resource<ImageQualityResponse>> = Transformations
        .switchMap(imageQualityRequest) { imageQualityRequest ->
            if (null == imageQualityRequest)
                AbsentLiveData.create()
            else
                object :
                    CoroutinesLiveDataHelper<ImageQualityResponse>(viewModelScope.coroutineContext) {
                    override suspend fun loadFromNetwork(): Flow<Resource<ImageQualityResponse>> {
                        return imageQuality(
                            authenticateResponse.value?.data?.id!!,
                            authenticateResponse.value?.data?.userId!!,
                            imageQualityRequest
                        )
                    }
                }.asLiveData()
        }

    private var fileExtractionRequest = MutableLiveData<FileExtractionRequest>()
    var fileExtractionResponse: LiveData<Resource<FileExtractionResponse>> = Transformations
        .switchMap(fileExtractionRequest) { fileExtractionRequest ->
            if (null == fileExtractionRequest)
                AbsentLiveData.create()
            else
                object :
                    CoroutinesLiveDataHelper<FileExtractionResponse>(viewModelScope.coroutineContext) {
                    override suspend fun loadFromNetwork(): Flow<Resource<FileExtractionResponse>> {
                        return fileExtraction(
                            authenticateResponse.value?.data?.id!!,
                            authenticateResponse.value?.data?.userId!!,
                            fileExtractionRequest
                        )
                    }
                }.asLiveData()
        }

    fun onAuthenticateClicked() {
        authenticateRequest.value = true
    }

    fun onEmiratesIDFrontClicked() {
        openCameraIntent.value = "emiratesId"
    }

    fun onEmiratesIDBackClicked() {
        openCameraIntent.value = "emiratesId"
    }

    fun onPassportClicked() {
        openCameraIntent.value = "genericPassport"
    }

    fun uploadApi(file: File) {
        uploadRequest.value = UploadRequest(
            file = file,
            optimize = "true",
            key = "1234",
            ttl = "10 mins"
        )
    }

    fun processAuthenticateResponse(data: AuthenticateResponse?) {
        Toast.makeText(context, "Authentication Success", Toast.LENGTH_LONG).show()
    }

    fun processUploadResponse(data: UploadResponse?) {
        Toast.makeText(context, "Upload Success", Toast.LENGTH_LONG).show()

//        imageQualityRequest.value = ImageQualityRequest(
//            ImageQualityRequest.Essentials(
//                acceptanceThreshold = "0.5",
//                qualityParameter = "all",
//                files = listOf(data?.file?.directURL)
//            )
//        )

        fileExtractionRequest.value = FileExtractionRequest(
            task = openCameraIntent.value!!,
            essentials = FileExtractionRequest.Essentials(
                files = listOf(data?.file?.directURL)
            )
        )

    }

    fun processImageQualityResponse(data: ImageQualityResponse?) {

    }

    fun processFileExtractionResponse(data: FileExtractionResponse?) {

    }
}
