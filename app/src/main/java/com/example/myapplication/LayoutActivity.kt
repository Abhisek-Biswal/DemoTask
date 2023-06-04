package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class LayoutActivity : AppCompatActivity() {
    lateinit var linearLayoutButton: Button
    lateinit var relativeLayoutButton: Button
    lateinit var constraintLayoutButton: Button
    lateinit var frameLayoutButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layout)

        linearLayoutButton=findViewById(R.id.btn_linear_layout)
        relativeLayoutButton=findViewById(R.id.btn_relative_layout)
        constraintLayoutButton=findViewById(R.id.btn_constraint_layout)
        frameLayoutButton=findViewById(R.id.btn_frame_layout)

        linearLayoutButton.setOnClickListener{
            var intent= Intent(this,LinearLayoutActivity::class.java)
            startActivity(intent)
        }
        relativeLayoutButton.setOnClickListener{
            var intent = Intent(this,RelativeLayoutActivity::class.java)
            startActivity(intent)
        }
        constraintLayoutButton.setOnClickListener{
            var intent= Intent(this,ConstraintLayout::class.java)
            startActivity(intent)
        }
        frameLayoutButton.setOnClickListener{
            var intent= Intent(this,FrameLayout::class.java)
            startActivity(intent)
        }

    }
}