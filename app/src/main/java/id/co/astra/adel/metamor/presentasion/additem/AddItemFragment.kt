package id.co.astra.adel.metamor.presentasion.additem

import android.Manifest.permission.CAMERA
import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.afollestad.materialdialogs.MaterialDialog
import dagger.hilt.android.AndroidEntryPoint
import id.co.astra.adel.metamor.R
import id.co.astra.adel.metamor.databinding.FragmentAddItemBinding
import id.co.astra.adel.metamor.domain.additem.model.AddItem
import id.co.astra.adel.metamor.presentasion.camera.CameraXActivity
import id.co.astra.adel.metamor.utils.Constants.CAMERA_X_RESULT
import id.co.astra.adel.metamor.utils.Constants.IS_BACK_CAMERA
import id.co.astra.adel.metamor.utils.Constants.PERMISSION_REQUEST_CODE
import id.co.astra.adel.metamor.utils.Constants.PICTURE
import id.co.astra.adel.metamor.utils.reduceFileImage
import id.co.astra.adel.metamor.utils.rotateBitmap
import id.co.astra.adel.metamor.utils.uriToFile
import kotlinx.coroutines.launch
import java.io.File

@AndroidEntryPoint
class AddItemFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentAddItemBinding? = null
    private val viewModel: AddItemViewModel by viewModels()
    private val binding get() = _binding
    private var image = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAddItemBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.btnAddImage?.setOnClickListener(this)
        binding?.btnAddCamera?.setOnClickListener(this)
        binding?.btnSubmit?.setOnClickListener(this)
    }

    private fun addImage() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        imgLauncher.launch(intent)
    }

    private suspend fun saveDataMetamor() {
        val name = binding?.edtName?.text.toString().trim()
        val price = binding?.edtPrice?.text.toString().toDouble()
        val addItem = AddItem(
            0,
            name,
            price,
            getBitmap()
        )
        viewModel.insertItem(addItem)
    }

    private fun openCamera() {
        val intent = Intent(context, CameraXActivity::class.java)
        cameraXLauncher.launch(intent)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            PERMISSION_REQUEST_CODE -> {

                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED
                ) {

                    openCamera()

                } else {
                    Toast.makeText(
                        requireActivity(),
                        getString(R.string.label_permission_denied),
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
                return
            }
        }
    }

    private fun checkPermission(): Boolean {
        return (ContextCompat.checkSelfPermission(requireActivity(), CAMERA) ==
                PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(
            requireContext(),
            READ_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED)
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(READ_EXTERNAL_STORAGE, CAMERA),
            PERMISSION_REQUEST_CODE
        )
    }


    private var cameraXLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == CAMERA_X_RESULT) {
                val myFile = it.data?.getSerializableExtra(PICTURE) as File
                val isBackCamera = it.data?.getBooleanExtra(IS_BACK_CAMERA, true) as Boolean

                val result = rotateBitmap(
                    BitmapFactory.decodeFile(myFile.path),
                    isBackCamera
                )
                binding?.imgPhoto?.setImageBitmap(result)
                val resizeImage = reduceFileImage(myFile)
                image = resizeImage.toString()
            }
        }


    private var imgLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val imageUri: Uri = result.data?.data as Uri
                val myFile = uriToFile(imageUri, requireActivity())
                val resizeImage = reduceFileImage(myFile)
                image = resizeImage.toString()
            }
        }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.btnAddImage -> addImage()
            R.id.btnAddCamera -> if (checkPermission()) openCamera() else requestPermission()
            R.id.btnSubmit -> {
                lifecycleScope.launch { saveDataMetamor() }
                val dialog = MaterialDialog(requireActivity())
                    .cancelable(false)
                    .cancelOnTouchOutside(false)
                    .title(text = getString(R.string.label_notif))
                    .message(text = getString(R.string.label_success_add_item))
                    .positiveButton(text = getString(R.string.label_ok)) {
                        it.dismiss()
                        with(binding) {
                            this?.edtName?.text?.clear()
                            this?.edtPrice?.text?.clear()
                            this?.imgPhoto?.setImageResource(0)
                        }
                    }
                dialog.show()
            }
        }
    }

    private suspend fun getBitmap(): Bitmap {
        val loading = context?.let { ImageLoader(it) }
        val request = context?.let {
            ImageRequest.Builder(it)
                .data(image)
                .build()
        }

        val result = (request?.let { loading?.execute(it) } as SuccessResult).drawable
        return (result as BitmapDrawable).bitmap
    }
}
