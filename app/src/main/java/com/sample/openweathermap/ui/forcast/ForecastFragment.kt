package com.sample.openweathermap.ui.forcast

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import android.provider.Settings
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import com.android.example.github.vo.Status
import com.google.android.gms.location.*
import com.sample.openweathermap.BR
import com.sample.openweathermap.R
import com.sample.openweathermap.constants.AppConstants
import com.sample.openweathermap.databinding.ForecastFragmentBinding
import com.sample.openweathermap.ui.base.BaseFragment


class ForecastFragment : BaseFragment<ForecastFragmentBinding, ForecastViewModel>() {

    var mFusedLocationClient: FusedLocationProviderClient? = null

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.forecast_fragment

    override val viewModel: Class<ForecastViewModel>
        get() = ForecastViewModel::class.java

    private val mLocationCallback: LocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            val mLastLocation: Location = locationResult.lastLocation
            injectedViewModel.callForecastApi(mLastLocation.latitude.toString(), mLastLocation.longitude.toString())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
        getLastLocation()
    }

    override fun subscribeToViewLiveData() {
        super.subscribeToViewLiveData()

        injectedViewModel.callAdapter.observe(this, Observer {
            viewDataBinding.recyclerView.adapter = ForecastAdapter(it)
        })
    }

    override fun subscribeToNetworkLiveData() {
        super.subscribeToNetworkLiveData()

        injectedViewModel.forecastResponse.observe(this, Observer { result ->

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

    private fun getLastLocation() {
        if (checkPermissions()) {
            if (isLocationEnabled()) {
                mFusedLocationClient?.lastLocation?.addOnCompleteListener { task ->
                    val location = task.result
                    if (location == null) {
                        requestNewLocationData()
                    } else {
                        injectedViewModel.callForecastApi(location.latitude.toString(), location.longitude.toString())
                    }
                }
            } else {
                AlertDialog.Builder(requireActivity())
                    .setMessage(getString(R.string._location_permission))
                    .setPositiveButton(R.string._ok
                    ) { _, _ ->
                        val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                        startActivity(intent)
                    }
                    .show()
            }
        } else {
            requestPermissions()
        }
    }


    private fun requestNewLocationData() {
        val mLocationRequest = LocationRequest()
        mLocationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        mLocationRequest.interval = 0
        mLocationRequest.fastestInterval = 0
        mLocationRequest.numUpdates = 1
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
        mFusedLocationClient?.requestLocationUpdates(
            mLocationRequest, mLocationCallback,
            Looper.myLooper()
        )
    }

    private fun isLocationEnabled(): Boolean {
        val locationManager =
            activity?.getSystemService(Context.LOCATION_SERVICE) as LocationManager?
        return locationManager!!.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }

    private fun checkPermissions(): Boolean {
        return ActivityCompat.checkSelfPermission(
            requireActivity(),
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(
                    requireActivity(),
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermissions() {
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            ),
            AppConstants.Permission.PERMISSION_ID
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == AppConstants.Permission.PERMISSION_ID
        ) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLastLocation()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if (checkPermissions()) {
            getLastLocation()
        }
    }
}
