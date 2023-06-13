package com.app.courseapp.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import com.app.courseapp.R
import com.app.courseapp.databinding.ActivityCourseDetailBinding
import com.app.courseapp.databinding.ActivityCreateAccountBinding

class CreateAccountActivity : AppCompatActivity() {


    lateinit var binding: ActivityCreateAccountBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateAccountBinding.inflate(layoutInflater)




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


            else if (binding.etPassword.text.toString().length != 5) {
                binding.etPassword.error = "please enter 5 digit password"
                return@setOnClickListener
            }



            else {

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)

            }






        }


        setContentView(binding.root)


    }

}


