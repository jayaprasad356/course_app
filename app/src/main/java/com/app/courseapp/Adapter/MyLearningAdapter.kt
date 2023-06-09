package com.app.courseapp.Adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.courseapp.Activity.CourseViewActivity
import com.app.courseapp.Model.Mylearning
import com.app.courseapp.R

class MyLearningAdapter (
    val activity: Activity,
    mylearning : ArrayList<Mylearning>,
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val mylearning : ArrayList<Mylearning>
    val activitys: Activity

    init {
        this.mylearning = mylearning
        this.activitys = activity
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View =
            LayoutInflater.from(activity).inflate(R.layout.mylearning_layout, parent, false)
        return ItemHolder(view)
    }

    override fun onBindViewHolder(holderParent: RecyclerView.ViewHolder, position: Int) {
        val holder:ItemHolder = holderParent as ItemHolder
        val report: Mylearning = mylearning[position]
        holder.tvTitle.text=report.title
        holder.tvAuthor.text=report.author

        holder.itemView.setOnClickListener {

            Intent (activity, CourseViewActivity::class.java).also {
                activity.startActivity(it)
            }
        }

    }



    override fun getItemCount(): Int {
        return mylearning.size
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