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
import androidx.recyclerview.widget.RecyclerView
import com.example.tdl.databinding.FragmentListBinding
import java.time.LocalDate

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentList.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentList : Fragment() {
    private lateinit var viewModel: MainActivityViewModel
    private var _binding: FragmentListBinding? = null
    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        _binding = FragmentListBinding.inflate(inflater, container, false)
        val view = binding.root

        var items: MutableList<Data> = mutableListOf()
        viewModel.items.add(Data("imie 1", LocalDate.now().toString(),"sport", R.drawable.x))
        viewModel.items.add(Data("imie 2", LocalDate.now().toString(),"sport", R.drawable.x))
        viewModel.items.add(Data("imie 3", LocalDate.now().toString(),"sport", R.drawable.x))
        viewModel.items.add(Data("imie 4", LocalDate.now().toString(),"sport", R.drawable.x))

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
