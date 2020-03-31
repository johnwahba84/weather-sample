package com.sample.openweathermap.utils

import com.sample.openweathermap.BuildConfig
import timber.log.Timber

object AppLogger {

    fun init() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    fun d(message: String?, vararg objects: Any?) {
        Timber.d(message, *objects)
    }

    fun e(message: String?, vararg objects: Any?) {
        Timber.e(message, *objects)
    }

    fun i(message: String?, vararg objects: Any?) {
        Timber.i(message, *objects)
    }

    fun w(message: String?, vararg objects: Any?) {
        Timber.w(message, *objects)
    }

    fun d(throwable: Throwable?, message: String?, vararg objects: Any?) {
        Timber.d(message, *objects)
    }

    fun e(throwable: Throwable?, message: String?, vararg objects: Any?) {
        Timber.e(message, *objects)
    }

    fun i(throwable: Throwable?, message: String?, vararg objects: Any?) {
        Timber.i(throwable, message, *objects)
    }

    fun w(throwable: Throwable?, message: String?, vararg objects: Any?) {
        Timber.w(throwable, message, *objects)
    }
}