package com.example.tdl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tdl.data.UserViewModel

class MainActivityViewModel : ViewModel() {
    var items: MutableList<Data> = mutableListOf()
    init {
        items.add(Data("","","",R.drawable.x))
    }

}