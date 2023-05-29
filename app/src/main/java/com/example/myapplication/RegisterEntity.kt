package com.example.myapplication

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Register_task_table")
data class RegisterEntity(
           @PrimaryKey(autoGenerate = true)

           val id  : Int = 0,
           val title : String,
           val description : String
//           var userId: Int = 0,
//
//           @ColumnInfo(name = "task_title")
//           var title: String,
//
//           @ColumnInfo(name = "task_description")
//           var description: String

           )
