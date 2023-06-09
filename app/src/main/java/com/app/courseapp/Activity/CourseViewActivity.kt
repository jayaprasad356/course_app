package com.app.courseapp.Activity

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.courseapp.Adapter.CourseViewAdapter
import com.app.courseapp.Model.CourseView
import com.app.courseapp.R
import com.app.courseapp.databinding.ActivityCourseViewBinding
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView


class CourseViewActivity : AppCompatActivity() {

    lateinit var binding: ActivityCourseViewBinding
    lateinit var activity: Activity
    private lateinit var youtubePlayerView: YouTubePlayerView
    private lateinit var youtubePlayer: YouTubePlayer


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCourseViewBinding.inflate(layoutInflater)



        activity = this

        val linearLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        binding.rvLecture.layoutManager = linearLayoutManager
        lectureList()


        youtubePlayerView = binding.youtubePlayerView
        lifecycle.addObserver(youtubePlayerView)

        youtubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer) {
                youtubePlayer = youTubePlayer
                youtubePlayer.loadVideo("dQw4w9WgXcQ", 0f)
            }

        })


        return setContentView(binding!!.root)


    }

    private fun lectureList() {
        var lectureList = ArrayList<CourseView>()

        lectureList.add(CourseView("1. ","Windows - Install Flutter","Video - 04:55 mins",""))
        lectureList.add(CourseView("2. ","Windows - Install Android  Studio","Video - 04:55 mins",""))
        lectureList.add(CourseView("3. ","Create new Flutter Project","Video - 04:55 mins",""))



        val adapter = CourseViewAdapter(activity, lectureList)
        binding.rvLecture.adapter = adapter

    }


    override fun onDestroy() {
        super.onDestroy()
        lifecycle.removeObserver(youtubePlayerView)
    }
}