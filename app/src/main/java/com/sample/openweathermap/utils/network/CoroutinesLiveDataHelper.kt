package com.sample.openweathermap.utils.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.sample.openweathermap.vo.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlin.coroutines.CoroutineContext

abstract class CoroutinesLiveDataHelper<ResultType>(val coroutineContext: CoroutineContext) {

    private val result: LiveData<Resource<ResultType>>

    init {
        result = liveData(context = coroutineContext + Dispatchers.IO ) {
            loadFromNetwork().collect { emit(it) }
        }
    }

    abstract suspend fun loadFromNetwork(): Flow<Resource<ResultType>>

    fun asLiveData() = result
}