package com.example.tdl
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tdl.data.Task

class MyAdapter(private val fragment: FragmentList) : RecyclerView.Adapter<MyViewHolder>() {

    private var taskList = emptyList<Task>()

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = taskList[position]

        holder.nameView.text = currentItem.name //+ "   [" + currentItem.id.toString() + "]"
        holder.data_type.text = currentItem.date + "   " + currentItem.type
        holder.imageView.setImageResource(R.drawable.x)

        holder.imageView.setOnClickListener{
            fragment.delete(currentItem)
        }
    }



    fun setData(task: List<Task>){
        this.taskList = task
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_view,parent,false))
    }
    override fun getItemCount(): Int {
        return taskList.size
    }


}