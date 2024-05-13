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

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)


        var items: MutableList<Data> = mutableListOf()
        items.add(Data("imie 1", LocalDate.now().toString(),"sport", R.drawable.x))
        items.add(Data("imie 2", LocalDate.now().toString(),"sport", R.drawable.x))
        items.add(Data("imie 3", LocalDate.now().toString(),"sport", R.drawable.x))
        items.add(Data("imie 4", LocalDate.now().toString(),"sport", R.drawable.x))



        val recyclerView: RecyclerView = findViewById(R.id.recyclerview)
        recyclerView.setLayoutManager(LinearLayoutManager(this))
        recyclerView.setAdapter(MyAdapter(applicationContext, items))
    }
}
