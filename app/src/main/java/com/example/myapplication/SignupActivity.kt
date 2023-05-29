package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.room.Room

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class SignupActivity : AppCompatActivity() {

    lateinit var username : EditText
    lateinit var email : EditText
    lateinit var mobile : EditText
    lateinit var pasword : EditText
    lateinit var confrimpassword : EditText
    lateinit var btnsignup : Button
    lateinit var  database : RegisterDatabase


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup_activity)

        username = findViewById(R.id.editname)
        email = findViewById(R.id.edemail)
        mobile = findViewById(R.id.editmobile)
        pasword = findViewById(R.id.edpassword)
        confrimpassword = findViewById(R.id.editconfrimpassword)
        btnsignup = findViewById(R.id.signupbtn)

        findViewById<TextView>(R.id.textlogin).setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java))
        }
        btnsignup.setOnClickListener {
            val uname = username.text.toString()
            val uemail = email.text.toString()
            val umobile = mobile.text.toString()
            val pass = pasword.text.toString()
            val cpass = confrimpassword.text.toString()

            if(username.text.isEmpty()|| uname.length>3){
                username.error = "Enter the name"
                return@setOnClickListener
            }else if(email.text.isEmpty() || !uemail.contains("@gmail.com")){
                email.error ="Enter valid email"
                return@setOnClickListener
            }else if(mobile.text.isEmpty() || umobile.length!=10){
                mobile.error ="Enter  valid number"
                return@setOnClickListener
            }else if(pasword.text.isEmpty() || pass.length>8){
                pasword.error ="Enter atleast 8 digit"
                return@setOnClickListener
            }else if(TextUtils.isEmpty(cpass) || cpass!=pass){
                confrimpassword.error ="Password is not match"
                return@setOnClickListener
            }else{

                database = Room.databaseBuilder(this,RegisterDatabase::class.java,"User_Register_table").build()
                GlobalScope.launch {
                    database.UserRegisterDao().insertEntity(UserEntity(0,username.text.toString(),
                        email.text.toString(),mobile.text.toString(),pasword.text.toString()))
                }
                Toast.makeText(this,"User added successsfully",Toast.LENGTH_LONG).show()
                startActivity(Intent(this,LoginActivity::class.java))
            }
        }
    }
}