package com.sample.openweathermap.data.remote

import com.google.gson.JsonObject
import com.sample.openweathermap.constants.AppConstants
import com.sample.openweathermap.domain.model.AuthenticateResponse
import com.sample.openweathermap.domain.model.UploadResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*


interface ApiSignZYService {

    @POST(AppConstants.ApiPath.AUTHENTICATE)
    suspend fun authenticate(@Body body: JsonObject): Response<AuthenticateResponse>

    @Multipart
    @POST
    suspend fun upload(
        @Url  url: String,
        @Header("Authorization") authorization: String?,
        @Part file: MultipartBody.Part?,
        @Part("ttl") ttl: RequestBody?,
        @Part("optimize") optimize: RequestBody?
    ): Response<UploadResponse>
}