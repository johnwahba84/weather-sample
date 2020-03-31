package com.sample.openweathermap.ui.home

import com.sample.openweathermap.BR
import com.sample.openweathermap.R
import com.sample.openweathermap.databinding.HomeFragmentBinding
import com.sample.openweathermap.ui.base.BaseFragment

class HomeFragment : BaseFragment<HomeFragmentBinding, HomeViewModel>() {

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.home_fragment

    override val viewModel: Class<HomeViewModel>
        get() = HomeViewModel::class.java
}
