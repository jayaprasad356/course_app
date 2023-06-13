package com.app.courseapp.Activity

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.app.courseapp.databinding.ActivityEditProfileBinding
import java.io.File

class EditProfileActivity : AppCompatActivity() {

    lateinit var binding: ActivityEditProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfileBinding.inflate(layoutInflater)

        binding.ibBack.setOnClickListener {

            onBackPressed()
        }

        binding.tvAddPhoto.setOnClickListener {


            //add image from camera and gallery
            pickImageFromGallery()

        }

        setContentView(binding.root)


    }


    private fun pickImageFromGallery() {

            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, 1)

    }


override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK && requestCode == 1) {
            binding.ivProfile.visibility = View.VISIBLE
            binding.tvAddPhoto.visibility = View.GONE
            binding.ivProfile.setImageURI(data?.data)
        }
    }


}