package com.sample.openweathermap.ui.choosecities

import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import com.android.example.github.vo.Status
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

    override fun subscribeToNetworkLiveData() {
        super.subscribeToNetworkLiveData()

        injectedViewModel.weatherResponse.observe(this, Observer { result ->

            if (result?.status == Status.SUCCESS) {
                injectedViewModel.processResponse(result.data)
            } else if (result?.status == Status.ERROR) {
                AlertDialog.Builder(activity!!)
                    .setMessage(result.message)
                    .setPositiveButton(R.string._ok, null)
                    .show()
            }
        })
    }
}
