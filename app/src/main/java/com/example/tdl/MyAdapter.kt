package com.example.tdl
import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class MyAdapter : RecyclerView.Adapter<MyViewHolder> {

    var context: Context
    var items: MutableList<Data>

    constructor(context: Context, items: MutableList<Data>) : super() {
        this.context = context
        this.items = items
    }


    @SuppressLint("SetTextI18n", "NotifyDataSetChanged")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.nameView.text = items[position].name
        holder.data_type.text = items[position].date + "   " + items[position].type
        holder.imageView.setImageResource(items[position].image)

        holder.imageView.setOnClickListener{
            items.remove(items[position])
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_view,parent,false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

}