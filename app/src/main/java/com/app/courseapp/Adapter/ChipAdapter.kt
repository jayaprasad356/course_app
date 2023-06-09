package com.app.courseapp.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.courseapp.R
import com.google.android.material.chip.Chip

class ChipAdapter(private val chipItems: List<String>) :
    RecyclerView.Adapter<ChipAdapter.ChipViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChipViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.itemchip, parent, false)
        return ChipViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChipViewHolder, position: Int) {
        holder.bind(chipItems[position])
    }

    override fun getItemCount(): Int {
        return chipItems.size
    }

    inner class ChipViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val chip: Chip = itemView as Chip

        fun bind(chipText: String) {
            chip.text = chipText
        }
    }
}
