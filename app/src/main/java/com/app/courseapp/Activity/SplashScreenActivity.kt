package com.app.courseapp.Activity

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.transition.Slide
import android.util.Log
import android.view.Gravity
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.app.courseapp.R
import com.app.courseapp.helper.ApiConfig
import com.app.courseapp.helper.Constant
import com.app.courseapp.helper.Session
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class SplashScreenActivity : AppCompatActivity() {

    private var handler: Handler? = null
    private var session: Session? = null
    private var activity: Activity? = null
    private var currentVersion = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)
        setContentView(R.layout.activity_splash_screen)

        activity = this
        session = Session(activity)
        handler = Handler()

        setupAnimations()
        setupViews()
    }

    private fun setupViews() {
        try {
            val pInfo = packageManager.getPackageInfo(packageName, 0)
            currentVersion = pInfo.versionCode.toString()
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }

        appUpdate()
    }

    private fun setupAnimations() {
        val slide = Slide(Gravity.END)
        slide.duration = 500
        window.enterTransition = slide
        window.exitTransition = slide
    }

    private fun GotoActivity() {
        handler?.postDelayed({
            val intent = if (session!!.getBoolean("is_logged_in")) {
                Intent(this, MainActivity::class.java)
            } else {
                Intent(this, LoginActivity::class.java)
            }
            startActivity(intent)
            finish()
        }, 2000)
    }

    private fun appUpdate() {
        val params: Map<String, String> = HashMap()
        ApiConfig.RequestToVolley({ result, response ->
            Log.d("CAT_RES", response)
            if (result) {
                try {
                    val jsonObject = JSONObject(response)
                    if (jsonObject.getBoolean(Constant.SUCCESS)) {
                        Log.d("CAT_RES", response)
                        val `object` = JSONObject(response)
                        val jsonArray: JSONArray = `object`.getJSONArray(Constant.DATA)
                        val latestVersion = jsonArray.getJSONObject(0).getString(Constant.VERSION)
                        val link = jsonArray.getJSONObject(0).getString(Constant.LINK)
                        if (currentVersion.toInt() >= latestVersion.toInt()) {
                            GotoActivity()
                        } else {
                            showUpdateDialog(link)
                        }
                    } else {
                        Toast.makeText(
                            this,
                            "Hi" + jsonObject.getString(Constant.MESSAGE).toString(),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                    Toast.makeText(this, "hi$e", Toast.LENGTH_SHORT).show()
                }
            }
        }, this, Constant.APPUPDATE, params, true, 1)
    }

    private fun showUpdateDialog(link: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Update Available")
        builder.setMessage("A new version of the app is available. Please update to continue using the app.")
        builder.setCancelable(false)
        builder.setPositiveButton("Update") { dialog, which ->
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
            startActivity(intent)
        }
        val dialog = builder.create()
        dialog.show()
    }
}
