package com.example.myapplication


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var registerButton: Button = findViewById<Button>(R.id.registerScreenBtn)
        registerButton.setOnClickListener {
            val intent= Intent(this,RegisterScreen::class.java)
            startActivity(intent)
        }
        var profileScreenButton: Button= findViewById<Button>(R.id.profileScreenBtn)
        profileScreenButton.setOnClickListener {
            val intent= Intent(this,ProfileScreen::class.java)
            startActivity(intent)
        }
        var viewScreenButton: Button= findViewById<Button>(R.id.viewBtn)
        viewScreenButton.setOnClickListener {
            val intent= Intent(this,ViewActivity::class.java)
            startActivity(intent)
        }
    }
}