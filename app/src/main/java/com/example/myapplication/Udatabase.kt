package com.example.myapplication

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(UserData::class), version = 1)
abstract class Udatabase: RoomDatabase() {

    abstract fun UserDao()

    companion object{

        @Volatile
        private var Instance: Udatabase ?= null

        fun getDatabase(context: Context): Udatabase{

            return Instance ?: synchronized(this){
                val instance= Room.databaseBuilder(
                    context.applicationContext,
                    Udatabase::class.java,
                    "User_Database"
                ).build()
                Instance= instance
                instance
            }
        }
    }

}