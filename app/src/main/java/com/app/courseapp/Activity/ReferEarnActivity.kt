package com.app.courseapp.Activity

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.app.courseapp.databinding.ActivityReferEarnBinding
import com.app.courseapp.helper.Session


class ReferEarnActivity : AppCompatActivity() {

    lateinit var binding: ActivityReferEarnBinding
    lateinit var activity : Activity
    lateinit var session: Session

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReferEarnBinding.inflate(layoutInflater)
        activity = this
        session = Session(activity)


        binding.ivBack.setOnClickListener {
            onBackPressed()
        }

        binding.btnShare.setOnClickListener{


            val message = "Check out this amazing app!"

            // Create an intent to share via WhatsApp

            // Create an intent to share via WhatsApp
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.setPackage("com.whatsapp")
            intent.putExtra(Intent.EXTRA_TEXT, message)

            try {
                startActivity(intent)
            } catch (e: ActivityNotFoundException) {
                // Handle the case when WhatsApp is not installed on the device
                Toast.makeText(applicationContext, "WhatsApp is not installed", Toast.LENGTH_SHORT)
                    .show()
            }



        }

        binding.btnCopyCode.setOnClickListener{

            val text = binding.tvCode.text.toString()
            val clipboard = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("label", text)
            clipboard.setPrimaryClip(clip)
            Toast.makeText(applicationContext, "Copied", Toast.LENGTH_SHORT).show()

        }

        setContentView(binding.root)


    }
}