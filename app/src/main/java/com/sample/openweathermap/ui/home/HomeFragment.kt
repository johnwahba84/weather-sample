package com.sample.openweathermap.ui.home

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.lifecycle.Observer
import com.android.example.github.vo.Status
import com.sample.openweathermap.BR
import com.sample.openweathermap.BuildConfig
import com.sample.openweathermap.R
import com.sample.openweathermap.databinding.HomeFragmentBinding
import com.sample.openweathermap.ui.base.BaseFragment
import com.sample.openweathermap.utils.ImageUtils
import java.io.File
import java.util.*

class HomeFragment : BaseFragment<HomeFragmentBinding, HomeViewModel>() {

    private var imageUri: Uri? = null

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.home_fragment

    override val viewModel: Class<HomeViewModel>
        get() = HomeViewModel::class.java

    override fun subscribeToNetworkLiveData() {
        super.subscribeToNetworkLiveData()

        injectedViewModel.authenticateResponse.observe(this, Observer { result ->

            injectedViewModel.showLoading.set(result?.status == Status.LOADING)

            if (result?.status == Status.SUCCESS) {
                injectedViewModel.processAuthenticateResponse(result.data)
            } else if (result?.status == Status.ERROR) {
                AlertDialog.Builder(requireActivity())
                    .setMessage(result.message)
                    .setPositiveButton(R.string._ok, null)
                    .show()
            }
        })

        injectedViewModel.uploadResponse.observe(this, Observer { result ->

            injectedViewModel.showLoading.set(result?.status == Status.LOADING)

            if (result?.status == Status.SUCCESS) {
                injectedViewModel.processUploadResponse(result.data)
            } else if (result?.status == Status.ERROR) {
                AlertDialog.Builder(requireActivity())
                    .setMessage(result.message)
                    .setPositiveButton(R.string._ok, null)
                    .show()
            }
        })
    }

    override fun subscribeToViewLiveData() {
        super.subscribeToViewLiveData()

        injectedViewModel.openCameraIntent.observe(this, Observer {

            if (ContextCompat.checkSelfPermission(
                    requireActivity(),
                    Manifest.permission.CAMERA
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // request camera permission first
                onRequestCameraClick(callback = takePicture)
            } else {
                takePicture.run()
            }
        })
    }

    private val takePicture: Runnable = Runnable {
        ImageUtils.createImageFile(requireContext())?.also {
            imageUri = FileProvider.getUriForFile(
                requireContext(),
                BuildConfig.APPLICATION_ID + ".fileprovider",
                it
            )
            takePictureRegistration.launch(imageUri)
        }
    }

    private val takePictureRegistration =
        registerForActivityResult(ActivityResultContracts.TakePicture()) { isSuccess ->
            if (isSuccess) {
                viewDataBinding.photoPreview.setImageURI(imageUri)
                injectedViewModel.uploadApi(File(ImageUtils.currentPhotoPath))
            }
        }

    private fun onRequestCameraClick(view: View? = null, callback: Runnable? = null) {
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->

            val message = if (isGranted) {
                "Camera permission has been granted!"
            } else {
                "Camera permission denied! :("
            }

            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()

            if (isGranted) {
                callback?.run()
            }
        }.launch(Manifest.permission.CAMERA)
    }
}
