package com.example.myapplication
import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CouroutineTask2 : AppCompatActivity() {
    private lateinit var textViewResult: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_couroutine_task2)

        textViewResult = findViewById(R.id.textViewResult1)

        val inputNumber = 10

        val firstThread = Thread {
            val result1 = performOperation1(inputNumber)

            val secondThread = Thread {
                val finalResult = performOperation2(result1)

                val handler = Handler(Looper.getMainLooper())
                handler.post {
                    textViewResult.text = "Final Result: $finalResult"
                }
            }

            secondThread.start()
        }

        firstThread.start()
    }

    private fun performOperation1(number: Int): Int {
        // Perform operation 1
        return number * 2
    }

    private fun performOperation2(number: Int): Int {
        // Perform operation 2
        return number + 5
    }
}