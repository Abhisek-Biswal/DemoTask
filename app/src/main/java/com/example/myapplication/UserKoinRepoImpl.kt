package com.example.myapplication

class UserKoinRepoImpl(var api : UserApi) : UserKoinServices {

    override fun networkCall() {
        api.callApi()
    }

}