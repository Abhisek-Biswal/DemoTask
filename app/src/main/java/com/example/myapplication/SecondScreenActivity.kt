package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText

class SecondScreenActivity : AppCompatActivity() {
    private var ed_data: EditText? = null
    private var second_screen_btn: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second_screen)

        ed_data = findViewById(R.id.ed_data)
        second_screen_btn = findViewById(R.id.second_screen_btn)
        second_screen_btn?.setOnClickListener(View.OnClickListener {
            val intent = Intent()
            intent.putExtra("result", ed_data?.text.toString())
            setResult(619, intent)
            super@SecondScreenActivity.onBackPressed()
        })




    }
}