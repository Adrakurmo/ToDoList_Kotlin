package com.example.tdl

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tdl.data.Task
import com.example.tdl.data.TaskViewModel
import com.example.tdl.databinding.FragmentListBinding

class FragmentList : Fragment(R.layout.activity_main) {
    private lateinit var mTaskViewModel: TaskViewModel
private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListBinding.inflate(inflater,container,false)

        val adapter = MyAdapter(this)

        binding.recyclerview.adapter = adapter
        binding.recyclerview.layoutManager = LinearLayoutManager(requireContext())

        mTaskViewModel = ViewModelProvider(this)[TaskViewModel::class.java]
        mTaskViewModel.readAllData.observe(viewLifecycleOwner, Observer { user ->
            adapter.setData(user)
        })


        return binding.root
    }

    fun delete(task: Task) {

        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton(R.string.Yes) { _, _ ->
            mTaskViewModel.delteUser(task)
        }
        builder.setNegativeButton(R.string.No) { _, _ ->

        }
        builder.setMessage("${R.string.YaSure} ${task.name}")
        builder.create().show()

    }


}
