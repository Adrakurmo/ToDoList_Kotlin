package com.example.tdl.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.lifecycle.LiveData
import androidx.room.Delete

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<User>>

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    suspend fun getUsers(): List<User>

    @Delete
    suspend fun deleteUser(user: User)
}