package com.sample.openweathermap.utils.network

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.sample.openweathermap.vo.Resource

/**
 * A generic class that can provide a resource backed by network.
 */
abstract class NetworkBoundResource<ResultType> {

    private val result = MediatorLiveData<Resource<ResultType>>()

    init {
        result.value = Resource.loading(null)
        fetchFromNetwork()
    }

    private fun fetchFromNetwork() {

        val apiResponse = loadFromNetwork()

        result.addSource(apiResponse) { response ->
            result.removeSource(apiResponse)
            when (response) {
                is ApiSuccessResponse -> {
                    result.value = Resource.success(processResponse(response))
                }
                is ApiErrorResponse -> {
                    onFetchFailed()
                    result.value = Resource.error(response.errorMessage, null)
                }
            }
        }
    }

    @MainThread
    abstract fun loadFromNetwork(): LiveData<ApiResponse<ResultType>>

    protected open fun onFetchFailed() {}

    fun asLiveData() = result as LiveData<Resource<ResultType>>

    @WorkerThread
    protected open fun processResponse(response: ApiSuccessResponse<ResultType>) = response.body
}
