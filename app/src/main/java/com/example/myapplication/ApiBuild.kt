package com.example.myapplication

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiBuild {
    companion object{
//        val baseURL = "https://newsapi.org/v2/"
        val baseURL =  "https://saurav.tech/NewsAPI/"

        var newsServices : NewsApi? = null
        fun createConnection(): NewsApi? {
            if(newsServices == null){
                val retroFit = Retrofit.Builder().baseUrl(baseURL)
                    .addConverterFactory(GsonConverterFactory.create()).build()
                newsServices = retroFit.create(NewsApi::class.java)
            }
            return newsServices
        }
    }
}
//curl --request GET 'https://saurav.tech/NewsAPI/top-headlines/category/health/in.json'