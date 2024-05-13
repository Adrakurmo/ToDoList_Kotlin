package com.example.tdl.data

import androidx.lifecycle.LiveData

class TaskRepository(private val taskDao: TaskDao) {

    val readAllData: LiveData<List<Task>> = taskDao.readAllData()

    suspend fun addUser(task: Task){
        taskDao.addUser(task)
    }

    suspend fun getUsers(): List<Task> {
        return taskDao.getUsers()
    }

    suspend fun deleteUser(task: Task){
        taskDao.deleteUser(task)
    }
}