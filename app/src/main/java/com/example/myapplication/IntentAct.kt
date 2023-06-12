package com.example.myapplication

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class IntentAct : AppCompatActivity() {

    lateinit var editText: EditText
    lateinit var clickBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent)

        editText= findViewById(R.id.ed_link)
        clickBtn= findViewById(R.id.btn_link)

        clickBtn.setOnClickListener {
            search()
        }

    }
    fun search(){
        val url = editText.text.toString()
        val urlIntent= Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(urlIntent)
    }
}