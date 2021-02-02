package com.sample.openweathermap.domain.usecase

import com.sample.openweathermap.data.repository.SignZYRepository
import com.sample.openweathermap.domain.model.*
import com.sample.openweathermap.vo.Resource
import kotlinx.coroutines.flow.Flow
import okhttp3.RequestBody
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ImageQuality @Inject constructor(private val repository: SignZYRepository) {

    suspend operator fun invoke(
        authorization: String,
        userID: String,
        request: ImageQualityRequest
    ): Flow<Resource<ImageQualityResponse>> {
        return repository.imageQuality(authorization, userID, request)
    }
}