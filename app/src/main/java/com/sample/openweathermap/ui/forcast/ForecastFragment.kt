package com.sample.openweathermap.ui.forcast

import com.sample.openweathermap.BR
import com.sample.openweathermap.R
import com.sample.openweathermap.databinding.ForecastFragmentBinding
import com.sample.openweathermap.ui.base.BaseFragment


class ForecastFragment : BaseFragment<ForecastFragmentBinding, ForecastViewModel>() {

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.forecast_fragment

    override val viewModel: Class<ForecastViewModel>
        get() = ForecastViewModel::class.java


}
