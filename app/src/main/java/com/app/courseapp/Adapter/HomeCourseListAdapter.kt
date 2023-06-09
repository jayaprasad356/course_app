package com.app.courseapp.Adapter

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.app.courseapp.Activity.CourseDetailActivity
import com.app.courseapp.Model.HomeCourseList
import com.app.courseapp.R

class HomeCourseListAdapter (
    val activity: Activity,
    homeCourseList: ArrayList<HomeCourseList>,
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val homeCourseList : ArrayList<HomeCourseList>
    val activitys: Activity

    init {
        this.homeCourseList = homeCourseList
        this.activitys = activity
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View =
            LayoutInflater.from(activity).inflate(R.layout.course_list_homelayout, parent, false)
        return ItemHolder(view)
    }

    override fun onBindViewHolder(holderParent: RecyclerView.ViewHolder, position: Int) {
        val holder:ItemHolder = holderParent as ItemHolder
        val report: HomeCourseList = homeCourseList[position]
        holder.tvTitle.text=report.title
        holder.tvAuthor.text=report.author


        holder.itemView.setOnClickListener {
            val intent = Intent(activity, CourseDetailActivity::class.java)
            activity.startActivity(intent)
        }


    }



    override fun getItemCount(): Int {
        return homeCourseList.size
    }

    internal class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle: TextView
        val tvAuthor: TextView
        val tvPrice: TextView


        init {
            tvTitle = itemView.findViewById(R.id.tvTitle)
            tvAuthor = itemView.findViewById(R.id.tvAuthor)
            tvPrice = itemView.findViewById(R.id.tvPrice)


        }
    }
}