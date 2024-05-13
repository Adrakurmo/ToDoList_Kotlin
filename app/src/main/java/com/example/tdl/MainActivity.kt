package com.example.tdl

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tdl.databinding.ActivityMainBinding
import java.time.LocalDate

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainActivityViewModel

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
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
