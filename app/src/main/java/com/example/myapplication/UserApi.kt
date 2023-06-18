package com.example.myapplication

import retrofit2.http.GET

interface UserApi {
    @GET("products/2")
    fun callApi()
}