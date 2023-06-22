package com.example.myapplication


import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductApi {

    @GET("products")
    fun getProducts(): Call<ProductList>

    @GET("products/{id}")
    fun getOneProduct(@Path("id") id : Int) : Call<Product>
}