package com.sample.openweathermap.data.repository

import com.sample.openweathermap.domain.model.AuthenticateResponse
import com.sample.openweathermap.domain.model.UploadRequest
import com.sample.openweathermap.domain.model.UploadResponse
import com.sample.openweathermap.vo.Resource
import kotlinx.coroutines.flow.Flow
import okhttp3.RequestBody

interface SignZYRepository {
    suspend fun authenticate(): Flow<Resource<AuthenticateResponse>>
    suspend fun upload(request: UploadRequest): Flow<Resource<UploadResponse>>
}