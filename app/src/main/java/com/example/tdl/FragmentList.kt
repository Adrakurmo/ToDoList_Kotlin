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
import com.example.tdl.data.User
import com.example.tdl.data.UserViewModel

class FragmentList : Fragment() {
    private lateinit var mUserViewModel: UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        val adapter = MyAdapter(this)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerview)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        mUserViewModel = ViewModelProvider(this)[UserViewModel::class.java]
        mUserViewModel.readAllData.observe(viewLifecycleOwner, Observer { user ->
            adapter.setData(user)
        })


        return view
    }

    fun delete(user: User) {

        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("YES") { _, _ ->
            mUserViewModel.delteUser(user)
        }
        builder.setNegativeButton("NO") { _, _ ->

        }
        builder.setMessage("Ya sure you want to delete: ${user.name}")
        builder.create().show()

    }
}
