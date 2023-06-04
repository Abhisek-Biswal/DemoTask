package com.example.myapplication

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class CustomControlActivity : AppCompatActivity() {

    private lateinit var customButton: CustomBtnProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_control)

        customButton = findViewById(R.id.customvieww)

        setButton()
        if (customButton != null) {
            customButton.setOnClickListener {
                customButton.setBackgroundResource(R.drawable.btnempty)
                customButton.showingloader()
                Handler().postDelayed({
                    customButton.hideloader()
                    customButton.setBackgroundResource(R.drawable.btnempty)
                }, 2000)
            }
        }
    }
    private fun setButton() {
        customButton.isEnabled()
        customButton.setBackgroundResource(R.drawable.btnempty)
        customButton.hideloader()
    }
}



