package com.sample.openweathermap.ui.currentcity

import com.sample.openweathermap.BR
import com.sample.openweathermap.R
import com.sample.openweathermap.databinding.ChooseCitiesFragmentBinding
import com.sample.openweathermap.ui.base.BaseFragment

class CurrentCityFragment : BaseFragment<ChooseCitiesFragmentBinding, CurrentCityViewModel>() {

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.current_city_fragment

    override val viewModel: Class<CurrentCityViewModel>
        get() = CurrentCityViewModel::class.java
}
