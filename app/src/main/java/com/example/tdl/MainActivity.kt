package com.example.tdl

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import com.example.tdl.data.TaskViewModel
import com.example.tdl.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainActivityViewModel
    private lateinit var mTaskViewModel : TaskViewModel
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        mTaskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        val fragmentList = FragmentList()
        val fragmentAdd = FragmentAdd()

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment, fragmentList)
            commit()
        }

        binding.ButtonList.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.flFragment, fragmentList)
                addToBackStack(null)
                commit()
            }
        }

        binding.ButtonAdd.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.flFragment, fragmentAdd)
                addToBackStack(null)
                commit()
            }


        }



    }
}
