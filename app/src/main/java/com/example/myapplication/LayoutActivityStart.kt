package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class LayoutActivityStart : AppCompatActivity() {
    lateinit var layoutBtn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layout_start)

        layoutBtn=findViewById(R.id.btn_layout)
        layoutBtn.setOnClickListener{
            val intent= Intent(this,LayoutActivity::class.java)
            startActivity(intent)
        }

    }
}