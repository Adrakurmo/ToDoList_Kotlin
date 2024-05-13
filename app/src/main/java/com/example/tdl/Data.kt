package com.example.tdl

import android.os.Trace

class Data(
    var name: String,
    var date: String,
    var type: String,
    var image: Int
) {
    val id: Int

    init {
        id = ++counter
    }

    companion object {
        private var counter = 0
    }
}
