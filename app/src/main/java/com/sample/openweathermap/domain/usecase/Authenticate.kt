package com.sample.openweathermap.domain.usecase

import com.sample.openweathermap.data.repository.SignZYRepository
import com.sample.openweathermap.domain.model.AuthenticateResponse
import com.sample.openweathermap.vo.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Authenticate @Inject constructor(private val repository: SignZYRepository) {

    suspend operator fun invoke(): Flow<Resource<AuthenticateResponse>> {
        return repository.authenticate()
    }
}