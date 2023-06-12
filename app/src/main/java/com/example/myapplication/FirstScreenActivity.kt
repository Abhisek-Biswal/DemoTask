package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager.OnActivityResultListener
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts

class FirstScreenActivity : AppCompatActivity() {
    lateinit var txtResult: TextView
    lateinit var btnFirstScreen: Button

    var activityLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult(),
        ActivityResultCallback  <ActivityResult>(){
            @Override
            fun OnActivityResult(result: ActivityResult){

                Log.d("TAG","On Activity Result: ")

            }
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_screen)

        txtResult= findViewById(R.id.tv_data_first_screen)
        btnFirstScreen= findViewById(R.id.btn_first_screen)

        btnFirstScreen.setOnClickListener(View.OnClickListener() {

            @Override
            fun onClick(view: View) {


            }
        })


    }
}