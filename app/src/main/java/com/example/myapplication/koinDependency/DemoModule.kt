package com.example.myapplication.koinDependency

import com.example.myapplication.*
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

var appModule = module {
    single {
        Retrofit.Builder()
            .baseUrl(RetrofritDemo.PRODUCT_LIST_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(UserApi::class.java)
    }
    single<UserKoinServices> {
        UserKoinRepoImpl(get())
    }
    viewModel {
        MyViewModel(get())
    }
}

var data = module{
    single {
        MyUtil(androidContext())
    }
}






