package com.example.myapplication

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel(){

    val message :MutableLiveData<Home> = MutableLiveData()


    fun sendMessage(name: String,emailId: String,address: String,phone: String,hobby: String) {
        message.value = Home(name,emailId,address,phone,hobby)
    }


}