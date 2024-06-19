package com.example.tdl

import MyViewModel
import dagger.Component

@Component
interface MyComponent {
    fun inject(viewModel: MyViewModel)
}