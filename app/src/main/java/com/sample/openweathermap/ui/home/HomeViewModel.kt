package com.sample.openweathermap.ui.home

import android.app.Application
import com.sample.openweathermap.ui.base.BaseViewModel
import com.sample.openweathermap.utils.NavigationCommand
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val context: Application
    ) : BaseViewModel(context) {

    fun onChooseCitiesClicked() {
        navigationCommands.value =
            NavigationCommand.To(HomeFragmentDirections.actionMainToChooseCities())
    }

    fun onCurrentClicked() {
        navigationCommands.value =
            NavigationCommand.To(HomeFragmentDirections.actionMainToCurrentCity())
    }
}
