package com.example.tdl
import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tdl.data.User

class MyAdapter : RecyclerView.Adapter<MyViewHolder>() {

    private var userList = emptyList<User>()



    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = userList[position]

        holder.nameView.text = currentItem.name + "   [" + currentItem.id.toString() + "]"
        holder.data_type.text = currentItem.date + "   " + currentItem.type
        holder.imageView.setImageResource(R.drawable.x)

        holder.imageView.setOnClickListener{

        }
    }
    fun setData(user: List<User>){
        this.userList = user
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_view,parent,false))
    }
    override fun getItemCount(): Int {
        return userList.size
    }


}