package com.example.assignmentchapter3androidd.fragments

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.example.assignmentchapter3androidd.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class GalleryFragment : Fragment(R.layout.fragment_gallery) {

    private  var selectedImageView: ImageView? = null

    private val pickImage = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
        Log.e("Image Load",uri.toString())
        if (uri != null) {
            selectedImageView?.setImageURI(uri)
        }
    }

    private val openCamera = registerForActivityResult(ActivityResultContracts.TakePicture()) { success ->

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       selectedImageView= view.findViewById(R.id.iv_selected_image)

        val addImageFAB: FloatingActionButton = view.findViewById(R.id.fab_add_image)

        addImageFAB.setOnClickListener {
            pickImage.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }

        val takePhotoFAB: FloatingActionButton = view.findViewById(R.id.fab_take_photo)

        takePhotoFAB.setOnClickListener {

        }

    }

    override fun onDestroy() {
        super.onDestroy()

        selectedImageView = null
    }

}