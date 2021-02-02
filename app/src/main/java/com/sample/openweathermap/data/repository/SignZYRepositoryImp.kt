package com.sample.openweathermap.data.repository

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import com.sample.openweathermap.constants.AppConstants
import com.sample.openweathermap.data.remote.ApiSignZYService
import com.sample.openweathermap.domain.model.AuthenticateRequest
import com.sample.openweathermap.domain.model.AuthenticateResponse
import com.sample.openweathermap.domain.model.UploadRequest
import com.sample.openweathermap.domain.model.UploadResponse
import com.sample.openweathermap.utils.network.ApiResponse
import com.sample.openweathermap.utils.network.NetworkBoundNoCacheResource
import com.sample.openweathermap.vo.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject


class SignZYRepositoryImp @Inject constructor(
    private val apiSignZYService: ApiSignZYService
): SignZYRepository {

    @FlowPreview
    @ExperimentalCoroutinesApi
    override suspend fun authenticate(): Flow<Resource<AuthenticateResponse>> {
        return object : NetworkBoundNoCacheResource<AuthenticateResponse>() {
            override suspend fun fetchFromNetwork(): ApiResponse<AuthenticateResponse> {

                val request = AuthenticateRequest(
                    username = AppConstants.ApiConfiguration.API_ID,
                    password = AppConstants.ApiConfiguration.API_PASSWORD
                )
                val params= Gson().fromJson(
                    GsonBuilder().create().toJson(request),
                    JsonObject::class.java
                )

                return ApiResponse.create(apiSignZYService.authenticate(params))
            }
        }.asFlow()
    }

    @FlowPreview
    @ExperimentalCoroutinesApi
    override suspend fun upload(request: UploadRequest): Flow<Resource<UploadResponse>> {
        return object : NetworkBoundNoCacheResource<UploadResponse>() {
            override suspend fun fetchFromNetwork(): ApiResponse<UploadResponse> {

                val file = MultipartBody.Part.createFormData(
                    "file", request.file.name, RequestBody.create(
                        MediaType.parse("image/*"), request.file
                    )
                )

                val ttl: RequestBody = RequestBody.create(
                    MediaType.parse("text/plain"),
                    request.ttl
                )

                val optimize: RequestBody = RequestBody.create(
                    MediaType.parse("text/plain"),
                    request.optimize
                )

                return ApiResponse.create(
                    apiSignZYService.upload(
                        AppConstants.ApiConfiguration.URL_UPLOAD + AppConstants.ApiPath.UPLOAD,
                        request.authorization,
                        file,
                        ttl,
                        optimize
                    )
                )
            }
        }.asFlow()
    }
}