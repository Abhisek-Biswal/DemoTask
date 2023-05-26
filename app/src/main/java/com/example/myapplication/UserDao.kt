package com.example.myapplication

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {

    @Insert
    suspend fun insertUserData(data: UserData)

    @Update
    suspend fun updateUserData(data: UserData)

    @Delete
    suspend fun deleteUserData(data: UserData)

    @Query("SELECT * FROM UserData order by id ASC")
    fun getUserData() : LiveData<List<UserData>>

}