package com.app.courseapp.Activity

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.courseapp.Adapter.CourseViewAdapter
import com.app.courseapp.Adapter.HomeCourseListAdapter
import com.app.courseapp.Model.CourseView
import com.app.courseapp.Model.HomeCourseList
import com.app.courseapp.R
import com.app.courseapp.databinding.ActivityCourseViewBinding
import com.app.courseapp.helper.ApiConfig
import com.app.courseapp.helper.Constant
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import org.json.JSONException
import org.json.JSONObject


class CourseViewActivity : AppCompatActivity() {

    lateinit var binding: ActivityCourseViewBinding
    lateinit var activity: Activity



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCourseViewBinding.inflate(layoutInflater)



        activity = this

        val linearLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        binding.rvLecture.layoutManager = linearLayoutManager
        lectureList()


        binding.tvCourseName.text = intent.getStringExtra("title")
        binding.tvCourseAuthor.text = intent.getStringExtra("author")
        Glide.with(activity).load(intent.getStringExtra("image")).into(binding.ivCourse)



        return setContentView(binding!!.root)


    }

    private fun lectureList() {
//        var lectureList = ArrayList<CourseView>()
//
//        lectureList.add(CourseView("1. ","Windows - Install Flutter","Video - 04:55 mins",""))
//        lectureList.add(CourseView("2. ","Windows - Install Android  Studio","Video - 04:55 mins",""))
//        lectureList.add(CourseView("3. ","Create new Flutter Project","Video - 04:55 mins",""))
//
//
//
//
//        val adapter = CourseViewAdapter(activity, lectureList)
//        binding.rvLecture.adapter = adapter



        val params: MutableMap<String, String> = HashMap()
        params[Constant.COURSE_ID] = "1"
        ApiConfig.RequestToVolley({ result, response ->
            if (result) {
                try {
                    val jsonObject = JSONObject(response)
                    if (jsonObject.getBoolean(Constant.SUCCESS)) {
                        val `object` = JSONObject(response)
                        val jsonArray = `object`.getJSONArray(Constant.DATA)
                        val g = Gson()
                        val lectureList: java.util.ArrayList<CourseView> =
                            java.util.ArrayList<CourseView>()
                        for (i in 0 until jsonArray.length()) {
                            val jsonObject1 = jsonArray.getJSONObject(i)
                            if (jsonObject1 != null) {
                                val group: CourseView =
                                    g.fromJson(jsonObject1.toString(), CourseView::class.java)
                                lectureList.add(group)
                            } else {
                                break
                            }
                        }
                        val adapter = CourseViewAdapter(activity, lectureList)
                        binding.rvLecture.adapter = adapter
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
        }, activity, Constant.SESSION_LIST, params, true, 1)


    }


}