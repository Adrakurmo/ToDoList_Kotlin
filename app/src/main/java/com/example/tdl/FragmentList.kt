package com.example.tdl

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import com.example.tdl.data.UserViewModel
import com.example.tdl.databinding.FragmentListBinding

class FragmentList : Fragment() {
    private lateinit var viewModel: MainActivityViewModel
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    private lateinit var mUserViewModel: UserViewModel
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
        _binding = FragmentListBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.recyclerview.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = MyAdapter(requireContext(), viewModel.items)
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
