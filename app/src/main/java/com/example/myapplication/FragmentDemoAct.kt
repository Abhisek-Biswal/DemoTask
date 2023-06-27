package com.example.myapplication

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class FragmentDemoAct : AppCompatActivity(),CommunicateBetweenTwoFragment {

    private lateinit var bottomNavigationBar : BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_demo)

        bottomNavigationBar = findViewById(R.id.bottom_navigation_view)
        bottomNavigationBar.setOnItemSelectedListener {

            loadFragment(HomFrag())

            when (it.itemId) {
                R.id.btm_nav_home -> {
                    loadFragment(HomFrag())
                    true
                }
                R.id.btm_nav_person -> {
                    loadFragment(PersonFrag())
                    true
                }
                R.id.btm_nav_settings -> {
                    loadFragment(SettingFrag())
                    true
                }

                else -> {
                    loadFragment(Fragment())
                    true
                }
            }
        }
    }
    private  fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame_layout,fragment)
        transaction.addToBackStack(null)
        transaction.commit()

    }

    override fun sendText(msg: String) {
        Toast.makeText(this, "Toast Message from Activity", Toast.LENGTH_SHORT).show()
    }

}