package com.app.courseapp.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.app.courseapp.R
import com.app.courseapp.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    lateinit var btnLogin: Button
    lateinit var tvCreateAccount : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)



        btnLogin = findViewById(R.id.btnLogin)
        tvCreateAccount = findViewById(R.id.tvCreateAccount)


       btnLogin.setOnClickListener {

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        tvCreateAccount.setOnClickListener {

            val intent = Intent(this, CreateAccountActivity::class.java)
            startActivity(intent)
        }



    }
}