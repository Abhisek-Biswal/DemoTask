package com.example.myapplication
import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*

class CouroutineTask4 : AppCompatActivity() {
    private lateinit var textViewResult: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_couroutine_task4)

        textViewResult = findViewById(R.id.textViewResult)

        // Start the coroutine
        CoroutineScope(Dispatchers.Main).launch {
            val result = performComplexOperation()

            // Update the UI with the result
            textViewResult.text = "Result: $result"
        }
    }

    private suspend fun performComplexOperation(): String {
        // Simulating a complex operation
        delay(3000)

        return "Complex Result"
    }

    override fun onDestroy() {
        super.onDestroy()
        // Cancel the coroutine if the activity is destroyed
        CoroutineScope(Dispatchers.Main).coroutineContext.cancel()
    }
}
