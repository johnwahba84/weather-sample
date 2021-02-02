package com.sample.openweathermap.data.repository

import com.sample.openweathermap.domain.model.*
import com.sample.openweathermap.vo.Resource
import kotlinx.coroutines.flow.Flow
import okhttp3.RequestBody

interface SignZYRepository {

    suspend fun authenticate(): Flow<Resource<AuthenticateResponse>>

    suspend fun upload(
        authorization: String,
        request: UploadRequest
    ): Flow<Resource<UploadResponse>>

    suspend fun imageQuality(
        authorization: String,
        userID: String,
        request: ImageQualityRequest
    ): Flow<Resource<ImageQualityResponse>>

    suspend fun fileExtraction(
        authorization: String,
        userID: String,
        request: FileExtractionRequest
    ): Flow<Resource<FileExtractionResponse>>
}