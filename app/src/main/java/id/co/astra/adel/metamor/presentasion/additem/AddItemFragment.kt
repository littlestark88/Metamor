package id.co.astra.adel.metamor.presentasion.additem

import android.Manifest.permission.CAMERA
import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.afollestad.materialdialogs.MaterialDialog
import com.facebook.stetho.Stetho
import id.co.astra.adel.metamor.BuildConfig
import id.co.astra.adel.metamor.R
import id.co.astra.adel.metamor.databinding.FragmentAddItemBinding
import id.co.astra.adel.metamor.domain.additem.model.AddItem
import id.co.astra.adel.metamor.utils.Constants.PERMISSION_REQUEST_CODE
import id.co.astra.adel.metamor.utils.compressImage
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.File
import java.io.FileOutputStream

class AddItemFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentAddItemBinding? = null
    private val viewModel: AddItemViewModel by viewModel()
    private var tempImageFilePath = ""
    private var tempImageUri: Uri? = null
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
        Stetho.initializeWithDefaults(context);
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
        tempImageUri = FileProvider.getUriForFile(
            requireActivity(),
            BuildConfig.APPLICATION_ID + ".fileprovider",
            createImageFile().also {
                tempImageFilePath = it.absolutePath
            })
        cameraLauncher.launch(tempImageUri)
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

    private var cameraLauncher =
        registerForActivityResult(ActivityResultContracts.TakePicture()) { isSuccess ->
            if (isSuccess) {
            compressImage(tempImageFilePath, 0.5)
//            binding?.sizeAfter?.text = sizeString
                binding?.imgPhoto?.setImageURI(tempImageUri)
            }
        }
    private var imgLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                val imageUri: Uri = data?.data!!
                binding?.imgPhoto?.setImageURI(imageUri)
                image = imageUri.toString()
//                uriToFile(requireActivity(), imageUri)?.let { file ->
//                    compressImage(file.absolutePath, 0.5)
//                    binding?.sizeBefore?.text = sizeString
//                }
            }
        }

    private fun createImageFile(): File {
        val storageDir = context?.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile("temp_image", ".jpg", storageDir)
    }

    private fun uriToFile(context: Context, uri: Uri): File? {
        context.contentResolver.openInputStream(uri)?.let { inputStream ->
            val tempFile: File = createImageFile()
            val fileOutputSteam = FileOutputStream(tempFile)

            inputStream.copyTo(fileOutputSteam)
            inputStream.close()
            fileOutputSteam.close()
            return tempFile
        }
        return null
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
