package com.example.myapplication
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {



    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val registerButton = findViewById<Button>(R.id.registerScreenBtn)
        registerButton.setOnClickListener {
            val intent= Intent(this,RegisterScreen::class.java)
            startActivity(intent)
        }
        val profileScreenButton: Button= findViewById<Button>(R.id.profileScreenBtn)
        profileScreenButton.setOnClickListener {
            val intent= Intent(this,ProfileScreen::class.java)
            startActivity(intent)
        }
        val viewScreenButton: Button= findViewById<Button>(R.id.viewBtn)
        viewScreenButton.setOnClickListener {
            val intent= Intent(this,ViewActivity::class.java)
            startActivity(intent)
        }
        val extendedfabbtn: Button = findViewById<Button>(R.id.extendedbtn)
        extendedfabbtn.setOnClickListener {
            val intent = Intent(this,snackbar_fab::class.java)
            startActivity(intent)
        }
        val couroutineButton: Button = findViewById(R.id.couroutineTask)
        couroutineButton.setOnClickListener {
            val intent= Intent(this,CouroutineTask::class.java)
            startActivity(intent)
        }
        val drawableTask: Button = findViewById(R.id.drawable_task)
        drawableTask.setOnClickListener {
            val intent= Intent(this,drawable::class.java)
            startActivity(intent)
        }
        val roomDatabaseBtn: Button = findViewById(R.id.room_database_task)
        roomDatabaseBtn.setOnClickListener {
            val intent= Intent(this,SplashScreen::class.java)
            startActivity(intent)
        }
        val jsonParsingBtn: Button = findViewById(R.id.json_parsing_task)
        jsonParsingBtn.setOnClickListener {
            val intent= Intent(this,JsonParsingPractice::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.map_demo).setOnClickListener {
            startActivity(Intent(this,MapActivity::class.java))
        }
        findViewById<Button>(R.id.retrofit_demo).setOnClickListener {
            startActivity(Intent(this,Retrofrit_Demo::class.java))
        }
        findViewById<Button>(R.id.fcm_task).setOnClickListener {
            startActivity(Intent(this,FCMTask::class.java))
        }
        findViewById<Button>(R.id.custom_drawable_task).setOnClickListener {
            startActivity(Intent(this,CustomControlActivity::class.java))
        }
        findViewById<Button>(R.id.layout_task).setOnClickListener {
            startActivity(Intent(this,LayoutActivityStart::class.java))
        }
        findViewById<Button>(R.id.view_pager_tablayout_task).setOnClickListener {
            startActivity(Intent(this,ViewPagerAndTabLayout::class.java))
        }
        findViewById<Button>(R.id.my_drawable).setOnClickListener {
            startActivity(Intent(this,MyDrawable::class.java))
        }
    }
}