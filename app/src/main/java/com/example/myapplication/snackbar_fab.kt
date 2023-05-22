package com.example.myapplication

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class snackbar_fab : AppCompatActivity() {
    lateinit var fab: FloatingActionButton
    lateinit var extendedfab: ExtendedFloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_snackbar_fab)

        fab= findViewById(R.id.fab)
        extendedfab= findViewById(R.id.xfab)

        fab.setOnClickListener {
            val snackbar = Snackbar.make(it,"Button Clicked",Snackbar.LENGTH_SHORT).show()
        }

        extendedfab.setOnClickListener {


            val snack = Snackbar.make(it,"This is a simple Snack Bar",Snackbar.LENGTH_LONG)
            snack.setAction("Dismiss", View.OnClickListener{
                Snackbar.make(it,"Snackbar Action Button is Clicked",Snackbar.LENGTH_LONG).show()

            })
            // change action button text color
            snack.setActionTextColor(Color.parseColor("blue"))
            // snackbar background color
            snack.view.setBackgroundColor(Color.parseColor("red"))
            snack.show()
        }
    }
}