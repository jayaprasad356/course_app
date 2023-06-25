package com.app.courseapp.Activity

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.app.courseapp.databinding.ActivityEditProfileBinding
import com.app.courseapp.helper.ApiConfig
import com.app.courseapp.helper.Constant
import com.app.courseapp.helper.Session
import org.json.JSONException
import org.json.JSONObject
import java.io.File

class EditProfileActivity : AppCompatActivity() {

    lateinit var binding: ActivityEditProfileBinding
    lateinit var activity: Activity
    lateinit var session: Session

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfileBinding.inflate(layoutInflater)

        activity = this
        session = Session(activity)

        userdetails()

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



    private fun userdetails() {
        val params: MutableMap<String, String> = HashMap()
        params[Constant.USER_ID] = session.getData(Constant.USER_ID)
        ApiConfig.RequestToVolley({ result, response ->
            if (result) {
                try {
                    val jsonObject = JSONObject(response)
                    if (jsonObject.getBoolean(Constant.SUCCESS)) {
                        val jsonObject1 = jsonObject.getJSONObject(Constant.DATA)
                        session.setData(Constant.USER_ID, jsonObject1.getString(Constant.ID))
                        session.setData(Constant.NAME, jsonObject1.getString(Constant.NAME))
                        session.setData(Constant.MOBILE, jsonObject1.getString(Constant.MOBILE))
                        session.setData(Constant.EMAIL, jsonObject1.getString(Constant.EMAIL))
                        session.setData(Constant.PASSWORD, jsonObject1.getString(Constant.PASSWORD))
                        session.setBoolean("is_logged_in", true)


                        binding.etName.setText(jsonObject1.getString(Constant.NAME))
                        binding.etEmail.setText(jsonObject1.getString(Constant.EMAIL))
                        binding.etPhoneNumber.setText(jsonObject1.getString(Constant.MOBILE))
                        binding.etPassword.setText(jsonObject1.getString(Constant.PASSWORD))


                    } else {
                        Toast.makeText(
                            activity,
                            "" + jsonObject.getString(Constant.MESSAGE),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }
        }, activity, Constant.USER_DETAILS, params, true, 1)
    }

}