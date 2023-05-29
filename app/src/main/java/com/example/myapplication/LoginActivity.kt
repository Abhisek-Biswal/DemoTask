package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView



class LoginActivity : AppCompatActivity() {

    lateinit var email : EditText
    lateinit var password : EditText
    lateinit var  textsignup : TextView
    lateinit var btnlogin : Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        email = findViewById(R.id.edt_text)
        password = findViewById(R.id.edt_text1)
        textsignup = findViewById(R.id.txt_view2)
        btnlogin = findViewById(R.id.button)


        //If user is not register  then go to signup page!
        textsignup.setOnClickListener{
            startActivity(Intent(this,SignupActivity::class.java))
        }

        // if validatiologin button
        btnlogin.setOnClickListener {

            val etemail = email.text.toString()
            val etpassword = password.text.toString()

            if(etemail.isEmpty()){
                email.error = "Email is required"
            }else if (etpassword.isEmpty()){
                password.error= "Password is required"
            }else{
                startActivity(Intent(this,HomeScreen::class.java))
            }

        }
    }
}