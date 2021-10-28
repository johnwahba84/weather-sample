package com.sample.openweathermap.utils.network

import androidx.annotation.MainThread
import com.sample.openweathermap.vo.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


/**
 * A generic class that can provide a resource backed by network.
 */
@FlowPreview
@ExperimentalCoroutinesApi
abstract class NetworkBoundNoCacheResource<ResultType> {

    fun asFlow(): Flow<Resource<ResultType>> = flow {

        try {
            emit(Resource.loading(null))

            when (val response = fetchFromNetwork()) {
                is ApiSuccessResponse -> {
                    emit(Resource.success(response.body))
                }
                is ApiEmptyResponse -> {
                    emit(Resource.success(null))
                }
                is ApiErrorResponse -> {
                    onFetchFailed()
                    emit(Resource.error(response.errorMessage, null))
                }
            }
        } catch (e: Exception) {
            emit(Resource.error("Something went wrong", null))
        }
    }

    @MainThread
    protected abstract suspend fun fetchFromNetwork(): ApiResponse<ResultType>

    protected open fun onFetchFailed() {}
}
