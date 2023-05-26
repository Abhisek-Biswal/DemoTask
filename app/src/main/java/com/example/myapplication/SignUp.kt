package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.UserHandle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class SignUp : AppCompatActivity() {
    lateinit var createAccountButton: Button
    lateinit var userName: EditText
    lateinit var userEmail: EditText
    lateinit var userMobileNumber: EditText
    lateinit var userPassword: EditText
    lateinit var userConfirmPassword:EditText
    lateinit var signIn: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        createAccountButton= findViewById(R.id.sign_up_button)
        createAccountButton.setOnClickListener {
            val intent= Intent(this,LoginActivity::class.java)
            startActivity(intent)

        }
        signIn= findViewById(R.id.sg_txt_view2)
        signIn.setOnClickListener {
            val intent= Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }
    }
}