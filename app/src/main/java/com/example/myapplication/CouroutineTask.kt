package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class CouroutineTask : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_couroutine_task)

        var couroutineButton: Button = findViewById(R.id.couroutines)
        couroutineButton.setOnClickListener {
            val intent= Intent(this,couroutines::class.java)
            startActivity(intent)
        }
        var couroutineButton2: Button = findViewById(R.id.couroutines1)
        couroutineButton2.setOnClickListener {
            val intent= Intent(this,CouroutineTask2::class.java)
            startActivity(intent)
        }
        var couroutineButton3: Button = findViewById(R.id.couroutines2)
        couroutineButton3.setOnClickListener {
            val intent= Intent(this,CouroutinesTask3::class.java)
            startActivity(intent)
        }
        var couroutineButton4: Button = findViewById(R.id.couroutines3)
        couroutineButton4.setOnClickListener {
            val intent= Intent(this,CouroutineTask4::class.java)
            startActivity(intent)
        }
    }
}