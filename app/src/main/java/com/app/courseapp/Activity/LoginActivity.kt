package com.app.courseapp.Activity

import android.Manifest
import android.Manifest.permission.READ_PHONE_STATE
import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.telephony.SubscriptionManager
import android.telephony.TelephonyManager
import android.transition.Slide
import android.view.Gravity
import android.view.Window
import android.view.animation.AnimationUtils
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.widget.doAfterTextChanged

import com.app.courseapp.R
import com.app.courseapp.databinding.ActivityLoginBinding
import com.app.courseapp.helper.ApiConfig
import com.app.courseapp.helper.Constant
import com.app.courseapp.helper.Session
import org.json.JSONException
import org.json.JSONObject

class LoginActivity : AppCompatActivity() {

    private lateinit var activity: Activity
    private lateinit var session: Session
    private lateinit var binding: ActivityLoginBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        activity = this
        session = Session(activity)


        val fadeAnimation = AnimationUtils.loadAnimation(activity, R.anim.fade_in_animation)
        binding.tvLogin.startAnimation(fadeAnimation)
        binding.tvLoginSubTitle.startAnimation(fadeAnimation)


        setContentView(binding.root)
        //setupAnimations()
        setupViews()
    }

    private fun setupAnimations() {
        val slide = Slide(Gravity.END)
        slide.duration = 1000
        window.enterTransition = slide
        window.exitTransition = slide
    }

    private fun setupViews() {
        binding.btnLogin.setOnClickListener {
            if (binding.etPhoneNumber.text.toString().isEmpty()) {
                binding.etPhoneNumber.error = "Please enter your phone number"
                return@setOnClickListener
            } else if (binding.etPhoneNumber.text.toString().length != 10) {
                binding.etPhoneNumber.error = "Please enter a valid phone number"
                return@setOnClickListener
            } else if (binding.etPassword.text.toString().isEmpty()) {
                binding.etPassword.error = "Please enter your password"
                return@setOnClickListener
            } else {
                login()
            }
        }

        binding.etPhoneNumber.doAfterTextChanged {
            binding.etPhoneNumber.error = null
        }

        binding.etPassword.doAfterTextChanged {
            binding.etPassword.error = null
        }

        binding.tvCreateAccount.setOnClickListener {
            val intent = Intent(this, CreateAccountActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_right_to_left_animation, R.anim.slide_left_to_right_animation)

        }
    }

    private fun login() {
        val params: MutableMap<String, String> = HashMap()
        params[Constant.PASSWORD] = binding.etPassword.text.toString()
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
                        Toast.makeText(
                            activity,
                            "" + jsonObject.getString(Constant.MESSAGE),
                            Toast.LENGTH_SHORT
                        ).show()
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
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
        }, activity, Constant.LOGIN, params, true, 1)
    }






}