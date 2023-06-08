package com.example.myapplication

import androidx.lifecycle.MutableLiveData

class NewsRepository() {

    val topicData = MutableLiveData<List<Articles>>()
    val loadError = MutableLiveData<String>()
    var loading = MutableLiveData<Boolean>()

    suspend fun fetchData(topicName: String): List<Articles>? {
        loading.value = true

        val response = ApiBuild.createConnection()?.getNews(topicName)
        return if (response?.isSuccessful == true) {
            response.body()?.articles
        } else {
            onError("Error : ${response?.message()}")
            null
        }
    }

    private fun onError(resMsg: String) {
        loadError.value = resMsg
        loading.value = false

    }
}