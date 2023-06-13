package com.app.courseapp.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.app.courseapp.R
import com.app.courseapp.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {



    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)



        binding.btnLogin.setOnClickListener {


            if (binding.etPhoneNumber.text.toString().isEmpty()) {
                binding.etPhoneNumber.error = "Please enter your phone number"
                return@setOnClickListener
            }

            else if (binding.etPhoneNumber.text.toString().length != 10) {
                binding.etPhoneNumber.error = "Please enter valid phone number"
                return@setOnClickListener
            }

            else  if (binding.etPassword.text.toString().isEmpty()) {
                binding.etPassword.error = "Please enter your password"
                return@setOnClickListener
            }

            //  binding.etPassword is should 5 digit


            else {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }


        }

        binding. tvCreateAccount.setOnClickListener {

            val intent = Intent(this, CreateAccountActivity::class.java)
            startActivity(intent)
        }

        setContentView(binding.root)








    }
}