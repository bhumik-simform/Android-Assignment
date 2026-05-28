package com.example.assignmentchapter3androidd.fragments

import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import com.example.assignmentchapter3androidd.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.io.File

class GalleryFragment : Fragment(R.layout.fragment_gallery) {

    private var selectedImageView: ImageView? = null
    private var takenImageUri: Uri? = null

    private val pickImage =
        registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            if (uri != null) {
                selectedImageView?.setImageURI(uri)
            }
        }

    private val openCamera =
        registerForActivityResult(ActivityResultContracts.TakePicture()) { success ->
            if (success && (takenImageUri != null)) {
                selectedImageView?.setImageURI(takenImageUri)
            }
        }

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                startCamera()
            } else {
                Toast.makeText(requireActivity(), "Permission denied", Toast.LENGTH_SHORT)
                    .show()
            }
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        selectedImageView = view.findViewById(R.id.iv_selected_image)

        val addImageFAB: FloatingActionButton = view.findViewById(R.id.fab_add_image)

        addImageFAB.setOnClickListener {
            pickImage.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }

        val takePhotoFAB: FloatingActionButton = view.findViewById(R.id.fab_take_photo)

        takePhotoFAB.setOnClickListener {
            val noCameraPermission = ContextCompat.checkSelfPermission(
                requireContext(),
                android.Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED

            if (noCameraPermission) {
                requestPermissionLauncher.launch(android.Manifest.permission.CAMERA)
            } else {
                startCamera()
            }
        }
    }

    private fun createImageUri(): Uri {
        val image = File(requireContext().filesDir, "camera_photo.png")

        return FileProvider.getUriForFile(
            requireContext(),
            "${requireContext().packageName}.provider",
            image
        )
    }

    private fun startCamera() {
        takenImageUri = createImageUri()

        takenImageUri?.let {
            openCamera.launch(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        selectedImageView = null
    }

}