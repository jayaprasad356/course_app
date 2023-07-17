package com.app.courseapp.Activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.SyncStateContract.Helpers.update
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.app.courseapp.databinding.ActivityEditProfileBinding
import com.app.courseapp.helper.ApiConfig
import com.app.courseapp.helper.Constant
import com.app.courseapp.helper.Session
import org.json.JSONException
import org.json.JSONObject

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


        binding.etPhoneNumber.setOnClickListener(View.OnClickListener {


            if (binding.etName.text.toString().isEmpty()) {
                binding.etName.error = "Please enter your name"
                return@OnClickListener
            }

            else if (binding.etPhoneNumber.text.toString().isEmpty()) {
                binding.etPhoneNumber.error = "Please enter your phone number"
                return@OnClickListener
            }

            // etphone number should be 10 digits

            else if (binding.etPhoneNumber.text.toString().length != 10) {
                binding.etPhoneNumber.error = "Please enter valid phone number"
                return@OnClickListener
            }

            else if (binding.etEmail.text.toString().isEmpty()) {
                binding.etEmail.error = "Please enter your email"
                return@OnClickListener
            }

            else  if (binding.etPassword.text.toString().isEmpty()) {
                binding.etPassword.error = "Please enter your password"
                return@OnClickListener
            }


            else if (binding.etPassword.text.toString().length <= 5) {
                binding.etPassword.error = "please enter 5 digit password"
                return@OnClickListener
            }








            else {

                update()


            }






        })

        setContentView(binding.root)


    }

    private fun update() {
        val params: MutableMap<String, String> = HashMap()
        params[Constant.EMAIL] = binding.etEmail.text.toString()
        params[Constant.PASSWORD] = binding.etPassword.text.toString()
        params[Constant.NAME] = binding.etName.text.toString()
        params[Constant.MOBILE] = binding.etPhoneNumber.text.toString()
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


                        Log.d("response",response)

                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)


                    } else {

                        Toast.makeText(activity, jsonObject.getString("message"), Toast.LENGTH_SHORT).show()

                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }
        }, activity, Constant.REGISTER, params, true, 1)
    }

    private fun userdetails() {
        val params: MutableMap<String, String> = HashMap()
        params[Constant.USER_ID] = session.getData(Constant.USER_ID)
        ApiConfig.RequestToVolley({ result, response ->
            if (result) {
                try {
                    val jsonObject = JSONObject(response)
                    if (jsonObject.getBoolean(Constant.SUCCESS)) {

                        Toast.makeText(
                            activity,
                            "" + jsonObject.getString(Constant.MESSAGE),
                            Toast.LENGTH_SHORT
                        ).show()

                        val jsonArray = jsonObject.getJSONArray(Constant.DATA)

                        binding.etName.setText(jsonArray.getJSONObject(0).getString(Constant.NAME))
                        binding.etEmail.setText(jsonArray.getJSONObject(0).getString(Constant.EMAIL))
                        binding.etPhoneNumber.setText(jsonArray.getJSONObject(0).getString(Constant.MOBILE))
                        binding.etPassword.setText(jsonArray.getJSONObject(0).getString(Constant.PASSWORD))





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