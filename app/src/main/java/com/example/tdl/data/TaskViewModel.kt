package com.example.tdl.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: TaskRepository
    val readAllData: LiveData<List<Task>>

    init {
        val userDao = TaskDatabase.getDatabase(application).userDao()
        repository = TaskRepository(userDao)
        readAllData = repository.readAllData
    }

    fun addUser(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(task)
        }
    }

    suspend fun getUsers(): List<Task> {
        return repository.getUsers()
    }

    fun delteUser(task: Task){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteUser(task)
        }
    }
}
