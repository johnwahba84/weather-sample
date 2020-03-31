package com.sample.openweathermap.ui.base

import androidx.lifecycle.ViewModel
import com.sample.openweathermap.utils.NavigationCommand
import com.sample.openweathermap.utils.SingleLiveEvent

open class BaseViewModel: ViewModel() {

    val navigationCommands = SingleLiveEvent<NavigationCommand>()
}