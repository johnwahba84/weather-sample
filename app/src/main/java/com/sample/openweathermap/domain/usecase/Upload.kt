package com.sample.openweathermap.domain.usecase

import com.sample.openweathermap.data.repository.SignZYRepository
import com.sample.openweathermap.domain.model.AuthenticateResponse
import com.sample.openweathermap.domain.model.UploadRequest
import com.sample.openweathermap.domain.model.UploadResponse
import com.sample.openweathermap.vo.Resource
import kotlinx.coroutines.flow.Flow
import okhttp3.RequestBody
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Upload @Inject constructor(private val repository: SignZYRepository) {

    suspend operator fun invoke(authorization: String, request: UploadRequest): Flow<Resource<UploadResponse>> {
        return repository.upload(authorization, request)
    }
}