package com.app.courseapp.Activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.afollestad.materialdialogs.MaterialDialog
import com.app.courseapp.R
import com.app.courseapp.databinding.ActivityCreateAccountBinding
import com.app.courseapp.helper.ApiConfig
import com.app.courseapp.helper.Constant
import com.app.courseapp.helper.Session
import org.json.JSONException
import org.json.JSONObject

class CreateAccountActivity : AppCompatActivity() {


    lateinit var binding: ActivityCreateAccountBinding
    lateinit var activity: Activity
    lateinit var session: Session

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateAccountBinding.inflate(layoutInflater)

        activity = this
        session = Session(activity)




        binding.ibBack.setOnClickListener {

            onBackPressed()


        }
        binding.tvLogin.setOnClickListener {

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)

        }
        binding.btnSignUp.setOnClickListener {

            if (binding.etName.text.toString().isEmpty()) {
                binding.etName.error = "Please enter your name"
                return@setOnClickListener
            }

            else if (binding.etPhoneNumber.text.toString().isEmpty()) {
                binding.etPhoneNumber.error = "Please enter your phone number"
                return@setOnClickListener
            }

            // etphone number should be 10 digits

            else if (binding.etPhoneNumber.text.toString().length != 10) {
                binding.etPhoneNumber.error = "Please enter valid phone number"
                return@setOnClickListener
            }

            else if (binding.etEmail.text.toString().isEmpty()) {
                binding.etEmail.error = "Please enter your email"
                return@setOnClickListener
            }

           else  if (binding.etPassword.text.toString().isEmpty()) {
                binding.etPassword.error = "Please enter your password"
                return@setOnClickListener
            }


            else if (binding.etPassword.text.toString().length <= 5) {
                binding.etPassword.error = "please enter 5 digit password"
                return@setOnClickListener
            }


            else if (binding.etComfirmPassword.text.toString().equals(binding.etPassword)){

                binding.etComfirmPassword.error = "password does not match"
                return@setOnClickListener

            }





            else {

                Create()


            }






        }


        setContentView(binding.root)


    }


    private fun Create() {
        val params: MutableMap<String, String> = HashMap()
        params[Constant.EMAIL] = binding.etEmail.text.toString()
        params[Constant.PASSWORD] = binding.etPassword.text.toString()
        params[Constant.NAME] = binding.etName.text.toString()
        params[Constant.MOBILE] = binding.etPhoneNumber.text.toString()
        params[Constant.CONFIRM_PASSWORD] = binding.etComfirmPassword.text.toString()
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


    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }





}


