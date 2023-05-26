package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EdgeEffect
import android.widget.EditText
import android.widget.TextView

class LoginActivity : AppCompatActivity() {
    lateinit var  signUpTxtView: TextView
    lateinit var mail:EdgeEffect
    lateinit var loginPassword: EditText
    lateinit var loginBtn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        signUpTxtView= findViewById(R.id.txt_view2)
        signUpTxtView.setOnClickListener {
            val intent= Intent(this,SignUp::class.java)
            startActivity(intent)
        }
        loginBtn=findViewById(R.id.button)
        loginBtn.setOnClickListener {
            val intent= Intent(this,Home::class.java)
            startActivity(intent)
        }
    }
}