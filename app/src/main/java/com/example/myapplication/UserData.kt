package com.example.myapplication

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "UserData")
data class UserData(

    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String,
    val emailAddress: String,
    val mobileNumber: Int

)

