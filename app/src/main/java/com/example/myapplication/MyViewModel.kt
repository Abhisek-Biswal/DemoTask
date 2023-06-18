package com.example.myapplication

import androidx.lifecycle.ViewModel

class MyViewModel (private var repository : UserKoinServices) : ViewModel(){

    fun doNetworkCall() : String {
        return "Call From view Model"
    }

}