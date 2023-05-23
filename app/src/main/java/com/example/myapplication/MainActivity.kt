package com.example.myapplication
import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton

class MainActivity : AppCompatActivity() {



    @SuppressLint("MissingInflatedId")
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
        var extendedfabbtn: Button = findViewById<Button>(R.id.extendedbtn)
        extendedfabbtn.setOnClickListener {
            val intent = Intent(this,snackbar_fab::class.java)
            startActivity(intent)
        }
        var couroutineButton: Button = findViewById(R.id.couroutineTask)
        couroutineButton.setOnClickListener {
            val intent= Intent(this,CouroutineTask::class.java)
            startActivity(intent)
        }
    }
}