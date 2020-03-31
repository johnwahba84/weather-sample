package com.sample.openweathermap.ui.choosecities

import com.sample.openweathermap.BR
import com.sample.openweathermap.R
import com.sample.openweathermap.databinding.ChooseCitiesFragmentBinding
import com.sample.openweathermap.ui.base.BaseFragment

class ChooseCitiesFragment : BaseFragment<ChooseCitiesFragmentBinding, ChooseCitiesViewModel>() {

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.choose_cities_fragment

    override val viewModel: Class<ChooseCitiesViewModel>
        get() = ChooseCitiesViewModel::class.java
}
