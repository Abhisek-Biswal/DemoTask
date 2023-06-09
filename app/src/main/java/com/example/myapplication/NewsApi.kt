package com.example.myapplication

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

   // @GET("top-headlines")
   @GET("everything?apiKey=cde69aaaeecf4811a12a89b4ed09129f")
    suspend fun getNews(@Query("q")topicName : String) : Response<NewsTopic>
}