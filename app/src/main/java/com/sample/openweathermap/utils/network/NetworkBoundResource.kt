package com.sample.openweathermap.utils.network

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import androidx.lifecycle.MediatorLiveData
import com.sample.openweathermap.vo.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*

/**
 * A generic class that can provide a resource backed by network.
 */

@FlowPreview
@ExperimentalCoroutinesApi
abstract class NetworkBoundResource<ResultType> {

    private val result = MediatorLiveData<Resource<ResultType>>()

    fun asFlow(): Flow<Resource<ResultType>> = flow {

        emit(Resource.loading(null))

        val dbValue = loadFromDb().first()

        if (shouldFetch(dbValue)) {

            emit(Resource.loading(null))

            when (val response = fetchFromNetwork()) {
                is ApiSuccessResponse -> {
                    saveNetworkResult(processResponse(response))
                    emitAll(loadFromDb().map { Resource.success(it) })
                }
                is ApiEmptyResponse -> {
                    emitAll(loadFromDb().map { Resource.success(null) })
                }
                is ApiErrorResponse -> {
                    onFetchFailed()
                    emitAll(loadFromDb().map { Resource.error(response.errorMessage, null) })
                }
            }
        } else {
            emitAll(loadFromDb().map { Resource.success(it) })
        }
    }

    protected open fun onFetchFailed() {
        //Implement in sub-classes to handle error
    }

    @WorkerThread
    protected open fun processResponse(response: ApiSuccessResponse<ResultType>) = response.body

    @WorkerThread
    protected abstract suspend fun saveNetworkResult(data: ResultType)

    @MainThread
    protected abstract fun shouldFetch(data: ResultType?): Boolean

    @MainThread
    protected abstract fun loadFromDb(): Flow<ResultType>

    @MainThread
    protected abstract suspend fun fetchFromNetwork(): ApiResponse<ResultType>

}
