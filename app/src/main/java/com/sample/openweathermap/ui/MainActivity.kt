package com.sample.openweathermap.ui

import android.os.Bundle
import androidx.navigation.Navigation
import com.sample.openweathermap.BR
import com.sample.openweathermap.R
import com.sample.openweathermap.databinding.MainActivityBinding
import com.sample.openweathermap.ui.base.BaseActivity

class MainActivity : BaseActivity<MainActivityBinding, MainViewModel>() {

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.main_activity

    override val viewModel: Class<MainViewModel>
        get() = MainViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initializeToolBar()
    }

    override fun onSupportNavigateUp(): Boolean {
        return Navigation.findNavController(this, R.id.fragment_container_view).navigateUp()
    }

    private fun initializeToolBar() {
        setSupportActionBar(viewDataBinding.toolBar)
        Navigation.findNavController(this, R.id.fragment_container_view)
            .addOnDestinationChangedListener { _, destination, _ ->
                title = destination.label
            }
    }
}
