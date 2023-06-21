package com.example.myapplication

import android.app.Application
import com.example.myapplication.koinDependency.appModule
import com.example.myapplication.koinDependency.data
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class KoinModule: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidLogger()
            androidContext(this@KoinModule)
            modules(appModule, data)
        }
    }
}