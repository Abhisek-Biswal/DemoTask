package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class SecondScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second_screen)

        val name_et = findViewById<EditText>(R.id.ed_data)
        val back_btn = findViewById<Button>(R.id.second_screen_btn)

        back_btn.setOnClickListener {
            val intent = Intent()
            intent.putExtra("str", name_et.text.toString())
            setResult(0, intent)
            finish()
        }
    }
}