package com.example.myapplication.container

import android.app.Application
//import com.example.myapplication.koinDependency.demoModule

class BaseApplication: Application(){

    override fun onCreate() {
        super.onCreate()

//        startKoin{
//
//            modules(demoModule)
//        }
    }


}