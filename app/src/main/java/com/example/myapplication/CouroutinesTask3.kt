package com.example.myapplication
import android.annotation.SuppressLint
import android.os.AsyncTask
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class CouroutinesTask3 : AppCompatActivity() {
    private lateinit var textViewCounter: TextView
    private var count = 0
    private var asyncTask: CountAsyncTask? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_couroutines_task3)

        textViewCounter = findViewById(R.id.textViewCounter)

        asyncTask = CountAsyncTask()
        asyncTask?.execute()
    }

    override fun onDestroy() {
        super.onDestroy()
        asyncTask?.cancel(true)
    }

    @SuppressLint("StaticFieldLeak")
    inner class CountAsyncTask : AsyncTask<Void, Void, Void>() {
        override fun doInBackground(vararg params: Void?): Void? {
            while (count < 10) {
                if (isCancelled) return null

                count++

                publishProgress()

                try {
                    Thread.sleep(1000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }

            return null
        }

        override fun onProgressUpdate(vararg values: Void?) {
            textViewCounter.text = "Count: $count"
        }

        override fun onPostExecute(result: Void?) {
            val handler = Handler(Looper.getMainLooper())
            handler.postDelayed({
                Toast.makeText(applicationContext, "AsyncTask completed", Toast.LENGTH_SHORT).show()
            }, 1000)
        }
    }
}