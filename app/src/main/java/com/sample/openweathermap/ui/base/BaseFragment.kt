package com.sample.openweathermap.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.sample.openweathermap.utils.NavigationCommand
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

abstract class BaseFragment<T : ViewDataBinding, V : BaseViewModel> : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var viewDataBinding: T
        private set

    lateinit var injectedViewModel: V
        private set

    @get:LayoutRes
    abstract val bindingVariable: Int

    @get:LayoutRes
    abstract val layoutId: Int

    abstract val viewModel: Class<V>

    override fun onCreate(savedInstanceState: Bundle?) {
        initializeDependencyInjection()
        super.onCreate(savedInstanceState)
        initializeViewModel()

        subscribeToNetworkLiveData()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewDataBinding.setVariable(bindingVariable, injectedViewModel)
        viewDataBinding.lifecycleOwner = viewLifecycleOwner
        viewDataBinding.executePendingBindings()

        subscribeToViewLiveData()
        subscribeToNavigationLiveData()
    }

    private fun initializeViewModel() {
        injectedViewModel =
            ViewModelProvider(this, viewModelFactory).get(viewModel)
    }

    private fun initializeDependencyInjection() {
        AndroidSupportInjection.inject(this)
    }

    open fun subscribeToViewLiveData() {
        //All Views Tasks
    }

    open fun subscribeToNetworkLiveData() {
        //All Network Tasks
    }

    open fun subscribeToNavigationLiveData() {

        injectedViewModel.navigationCommands.observe(viewLifecycleOwner, Observer {

            when (it) {
                is NavigationCommand.To -> findNavController().navigate(it.directions)
                is NavigationCommand.Back -> findNavController().popBackStack()
            }
        })
    }
}