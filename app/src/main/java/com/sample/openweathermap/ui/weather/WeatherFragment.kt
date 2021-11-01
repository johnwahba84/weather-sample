package com.sample.openweathermap.ui.weather

import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import com.android.example.github.vo.Status
import com.sample.openweathermap.BR
import com.sample.openweathermap.R
import com.sample.openweathermap.databinding.WeatherFragmentBinding
import com.sample.openweathermap.ui.base.BaseFragment

class WeatherFragment : BaseFragment<WeatherFragmentBinding, WeatherViewModel>() {

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.weather_fragment

    override val viewModel: Class<WeatherViewModel>
        get() = WeatherViewModel::class.java

    override fun subscribeToViewLiveData() {
        super.subscribeToViewLiveData()

        injectedViewModel.callAdapter.observe(viewLifecycleOwner, {
            viewDataBinding.recyclerView.adapter = WeatherAdapter(it)
        })
    }

    override fun subscribeToNetworkLiveData() {
        super.subscribeToNetworkLiveData()

        injectedViewModel.weatherResponse.observe(this, { result ->

            injectedViewModel.showLoading.set(result?.status == Status.LOADING)

            if (result?.status == Status.SUCCESS) {
                injectedViewModel.processResponse(result.data)
            } else if (result?.status == Status.ERROR) {
                AlertDialog.Builder(requireActivity())
                    .setMessage(result.message)
                    .setPositiveButton(R.string._ok, null)
                    .show()
            }
        })
    }
}
