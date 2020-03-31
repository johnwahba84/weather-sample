package com.sample.openweathermap.ui.home

import com.sample.openweathermap.ui.base.BaseViewModel
import com.sample.openweathermap.utils.NavigationCommand
import javax.inject.Inject

class HomeViewModel @Inject constructor() : BaseViewModel() {

    fun onChooseCitiesClicked() {
        navigationCommands.value = NavigationCommand.To(HomeFragmentDirections.actionMainToChooseCities())
    }

    fun onCurrentClicked() {
        navigationCommands.value = NavigationCommand.To(HomeFragmentDirections.actionMainToCurrentCity())
    }
}
