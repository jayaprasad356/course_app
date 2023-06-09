package com.app.courseapp.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import com.app.courseapp.R

class CreateAccountActivity : AppCompatActivity() {

    lateinit var  btnSignUp : Button
    lateinit var  tvLogin : TextView
    lateinit var  ibBack : ImageButton



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)






        btnSignUp = findViewById(R.id.btnSignUp)
        tvLogin = findViewById(R.id.tvLogin)
        ibBack = findViewById(R.id.ibBack)

        ibBack.setOnClickListener {

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)

        }

        tvLogin.setOnClickListener {

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)

        }

        btnSignUp.setOnClickListener {

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }




    }
}