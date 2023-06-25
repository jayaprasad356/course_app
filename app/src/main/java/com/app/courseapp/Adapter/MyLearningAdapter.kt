package com.app.courseapp.Adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.app.courseapp.Activity.CourseViewActivity
import com.app.courseapp.Model.Mylearning
import com.app.courseapp.R
import com.bumptech.glide.Glide

class MyLearningAdapter(
    val activity: Activity,
    mylearning: ArrayList<Mylearning>,
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val mylearning: ArrayList<Mylearning>
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
        val holder: ItemHolder = holderParent as ItemHolder
        val report: Mylearning = mylearning[position]
        holder.tvTitle.text = report.course_title
        holder.tvAuthor.text = report.author
        Glide.with(activity)
            .load(report.image)
            .placeholder(R.drawable.ms_img)
            .into(holder.ivImg)
        holder.cardView.startAnimation(
            AnimationUtils.loadAnimation(
                holder.itemView.context,
                R.anim.recycler_anim
            )
        )

        holder.itemView.setOnClickListener {

            Intent(activity, CourseViewActivity::class.java).also {
                it.putExtra("id", report.id)
                it.putExtra("title", report.course_title)
                it.putExtra("author", report.author)
                it.putExtra("image", report.image)
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
        val cardView: CardView
        val  ivImg : ImageView


        init {
            tvTitle = itemView.findViewById(R.id.tvTitle)
            tvAuthor = itemView.findViewById(R.id.tvAuthor)
            tvPrice = itemView.findViewById(R.id.tvPrice)
            cardView = itemView.findViewById(R.id.cardView)
            ivImg = itemView.findViewById(R.id.ivImg)


        }
    }
}