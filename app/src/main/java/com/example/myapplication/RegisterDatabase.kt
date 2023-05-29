package com.example.myapplication

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [RegisterEntity::class,UserEntity::class], version = 1)
abstract class RegisterDatabase: RoomDatabase(){

    abstract fun RegisterDatabaseDao() : RegisterDatabaseDao
    abstract fun UserRegisterDao() : UserRegisterDao

}