package com.app.courseapp.Adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.courseapp.Activity.CourseViewActivity
import com.app.courseapp.Model.CourseView
import com.app.courseapp.R

class CourseViewAdapter (
    val activity: Activity,
    courseView : ArrayList<CourseView>,
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val courseView : ArrayList<CourseView>
    val activitys: Activity

    init {
        this.courseView = courseView
        this.activitys = activity
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View =
            LayoutInflater.from(activity).inflate(R.layout.lecture_layout, parent, false)
        return ItemHolder(view)
    }

    override fun onBindViewHolder(holderParent: RecyclerView.ViewHolder, position: Int) {
        val holder:ItemHolder = holderParent as ItemHolder
        val report: CourseView = courseView[position]
        holder.tvNo.text=report.id
        holder.tvCourseName.text=report.title


    }



    override fun getItemCount(): Int {
        return courseView.size
    }

    internal class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNo: TextView
        val tvCourseName: TextView
        val tvDuration: TextView


        init {
            tvNo = itemView.findViewById(R.id.tvNo)
            tvCourseName = itemView.findViewById(R.id.tvCourseName)
            tvDuration = itemView.findViewById(R.id.tvDuration)

        }
    }
}