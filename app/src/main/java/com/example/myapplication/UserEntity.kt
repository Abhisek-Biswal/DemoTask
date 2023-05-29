package com.example.myapplication

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "User_Register_table")
data class UserEntity(
@PrimaryKey(autoGenerate = true)
        val id: Int = 0,
        val name : String,
        val email : String,
        val mobile : String,
        val password:String

)