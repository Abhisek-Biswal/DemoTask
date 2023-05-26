package com.example.myapplication

import androidx.lifecycle.LiveData

class DataRepository(private val dao: UserDao) {

   val dataOfUser: LiveData<List<UserData>> = dao.getUserData()

    suspend fun insertUserData(data: UserData){
        dao.insertUserData(data)
    }
    suspend fun deleteUserData(data: UserData){
        dao.deleteUserData(data)
    }
    suspend fun updateUserData(data: UserData){
        dao.updateUserData(data)
    }
}