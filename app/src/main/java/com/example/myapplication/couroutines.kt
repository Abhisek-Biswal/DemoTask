package com.example.myapplication
import androidx.appcompat.app.AppCompatActivity
import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.TextView



class couroutines : AppCompatActivity() {
    private lateinit var textViewResult: TextView

    @SuppressLint("MissingInflatedId", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_couroutines)

        textViewResult = findViewById(R.id.textViewResult)

        val number1 = 5
        val number2 = 3

        val thread = Thread {
            val result = addNumbers(number1, number2)

            // Update the UI with the result
            val handler = Handler(Looper.getMainLooper())
            handler.post {
                textViewResult.text = "Result: $result"
            }
        }

        thread.start()
    }

    private fun addNumbers(num1: Int, num2: Int): Int {
        return num1 + num2
    }
}