package com.example.myapplication

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserRegisterDao {


    @Query("SELECT * FROM User_Register_table")
    suspend fun getAllTask(): List<UserEntity>

    @Insert
    suspend fun  insertEntity(entity: UserEntity)
    @Update
    suspend fun  updateentity(entity: UserEntity)
    @Delete
    suspend fun  deleteentity(entity: UserEntity)




}