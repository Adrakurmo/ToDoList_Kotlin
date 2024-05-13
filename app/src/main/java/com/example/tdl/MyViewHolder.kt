package com.example.tdl

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var imageView: ImageView = itemView.findViewById(R.id.imageview)
    var nameView: TextView = itemView.findViewById(R.id.name)
    var data_type: TextView = itemView.findViewById(R.id.date_type)
}
