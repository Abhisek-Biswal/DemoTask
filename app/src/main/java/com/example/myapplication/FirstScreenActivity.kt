package com.example.myapplication


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts

class FirstScreenActivity : AppCompatActivity() {

    private var btn_first_screen: Button? = null
    private var tv_data_first_screen: TextView? = null
    var activityLauncher = registerForActivityResult<Intent, ActivityResult>(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->

        if (result.resultCode == 619) {
            val intent = result.data
            if (intent != null) {
                val data = intent.getStringExtra("result")
                tv_data_first_screen!!.text = data
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_screen)

        btn_first_screen = findViewById(R.id.btn_first_screen)
        tv_data_first_screen = findViewById(R.id.tv_data_first_screen)
        btn_first_screen?.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@FirstScreenActivity, SecondScreenActivity::class.java)
            activityLauncher.launch(intent)
        })
    }
}
