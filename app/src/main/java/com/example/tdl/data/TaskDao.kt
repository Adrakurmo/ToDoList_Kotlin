package com.example.tdl.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.lifecycle.LiveData
import androidx.room.Delete

@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(task: Task)

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<Task>>

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    suspend fun getUsers(): List<Task>

    @Delete
    suspend fun deleteUser(task: Task)
}