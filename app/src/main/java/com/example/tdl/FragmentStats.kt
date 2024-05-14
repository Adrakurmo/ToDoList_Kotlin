package com.example.tdl

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.tdl.data.TaskViewModel

class FragmentStats : Fragment() {
    private lateinit var mTaskViewModel: TaskViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_stats, container, false)
        mTaskViewModel = ViewModelProvider(this)[TaskViewModel::class.java]

        mTaskViewModel.readAllData.observe(viewLifecycleOwner, Observer { tasks ->
            val maaaapaa = tasks.groupBy { it.type }.mapValues { it.value.size }

            val taskTextView = view.findViewById<TextView>(R.id.Task)
            val sportTextView = view.findViewById<TextView>(R.id.Sport)
            val workTextView = view.findViewById<TextView>(R.id.Work)
            val schoolTextView = view.findViewById<TextView>(R.id.School)
            val doctorTextView = view.findViewById<TextView>(R.id.Doctor)

            taskTextView.text = "Task: ${maaaapaa["Task"] ?: 0}"
            sportTextView.text = "Sport: ${maaaapaa["Sport"] ?: 0}"
            workTextView.text = "Work: ${maaaapaa["Work"] ?: 0}"
            schoolTextView.text = "School: ${maaaapaa["School"] ?: 0}"
            doctorTextView.text = "Doctor: ${maaaapaa["Doctor"] ?: 0}"
        })


        return view
    }
}

