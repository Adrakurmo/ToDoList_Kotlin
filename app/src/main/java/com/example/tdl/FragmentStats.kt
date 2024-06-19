package com.example.tdl

import MyViewModel
import android.annotation.SuppressLint
import android.net.http.HttpException
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresExtension
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.tdl.data.TaskViewModel
import com.example.tdl.databinding.FragmentStatsBinding
import java.io.IOException

class FragmentStats : Fragment() {
    private lateinit var mTaskViewModel: TaskViewModel
    private var _binding: FragmentStatsBinding? = null
    private lateinit var myViewModel: MyViewModel
    private val binding get() = _binding!!
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStatsBinding.inflate(inflater,container,false)
        mTaskViewModel = ViewModelProvider(this)[TaskViewModel::class.java]
        myViewModel = ViewModelProvider(requireActivity())[MyViewModel::class.java]

        mTaskViewModel.readAllData.observe(viewLifecycleOwner, Observer { tasks ->
            val mapa = tasks.groupBy { it.type }.mapValues { it.value.size }

            binding.Task.text = "${getString(R.string.Spinner_Task)}: ${mapa[getString(R.string.Spinner_Task)] ?: 0}"
            binding.Sport.text = "${getString(R.string.Spinner_Sport)}: ${mapa[getString(R.string.Spinner_Sport)] ?: 0}"
            binding.Work.text = "${getString(R.string.Spinner_Work)}: ${mapa[getString(R.string.Spinner_Work)] ?: 0}"
            binding.School.text = "${getString(R.string.Spinner_School)}: ${mapa[getString(R.string.Spinner_School)] ?: 0}"
            binding.Doctor.text = "${getString(R.string.Spinner_Doctor)}: ${mapa[getString(R.string.Spinner_Doctor)] ?: 0}"
        })


        lifecycleScope.launchWhenCreated {
            val response = try{
                RetrofitInstance.api.getCurrency("43fbc8a2f40714e3c6571ca2")
            } catch (e : IOException){
                Log.e("", "No int conn")
                return@launchWhenCreated
            }catch (e: HttpException){
                Log.e("", "unexpected response")
                return@launchWhenCreated
            }
            if(response.isSuccessful && response.body() != null){
                binding.KursPLN.text = getString(R.string.dv) + " " +
                    response.body()!!.conversion_rates["PLN"].toString()
            }
        }

        return binding.root
    }
}

