package com.example.myapplication

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.koin.android.ext.android.inject

class KoinAct : AppCompatActivity() {

    val mData : MyUtil by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_koin2)




            mData.saveName(this,"Dev")
            findViewById<TextView>(R.id.koin_tv).text = mData.getName(this)
        }

}