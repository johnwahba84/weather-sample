package com.sample.openweathermap.ui.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.sample.openweathermap.utils.NavigationCommand
import com.sample.openweathermap.utils.SingleLiveEvent

open class BaseViewModel(context: Application) : AndroidViewModel(context) {

    val navigationCommands = SingleLiveEvent<NavigationCommand>()
}