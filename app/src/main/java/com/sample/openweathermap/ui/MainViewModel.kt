package com.sample.openweathermap.ui

import android.app.Application
import com.sample.openweathermap.ui.base.BaseViewModel
import javax.inject.Inject

class MainViewModel @Inject constructor(private val context: Application) : BaseViewModel(context) {
}