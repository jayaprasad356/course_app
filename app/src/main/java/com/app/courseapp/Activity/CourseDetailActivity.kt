package com.app.courseapp.Activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.app.courseapp.R
import com.app.courseapp.databinding.ActivityCourseDetailBinding
import com.app.courseapp.databinding.ActivityCourseViewBinding

class CourseDetailActivity : AppCompatActivity() {

    lateinit var binding: ActivityCourseDetailBinding
    lateinit var activity: Activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course_detail)


        binding = ActivityCourseDetailBinding.inflate(layoutInflater)
        activity = this

        binding.ibBack.setOnClickListener {

            onBackPressed()
            finish()

        }

        return setContentView(binding!!.root)

    }
}