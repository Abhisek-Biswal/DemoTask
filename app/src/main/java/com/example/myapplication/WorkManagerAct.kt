package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.work.*



class WorkManagerAct : AppCompatActivity() {

    lateinit var imageUrlEditText : EditText


    private val mWorkManager = WorkManager.getInstance()
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {

        val workManager = WorkManager.getInstance()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work_manager)

        imageUrlEditText= findViewById(R.id.urlDownload)
        val imageUrl = imageUrlEditText.text.toString()

        val progressBar = findViewById<ProgressBar>(R.id.ImgProgressBar)
        val progressBarTv = findViewById<TextView>(R.id.progressTv)
        var i = 0
        val handler = Handler()
        findViewById<Button>(R.id.cancelBtn).setOnClickListener {
            mWorkManager.cancelAllWorkByTag("demo")
        }


        val inputData = Data.Builder()
            .putString("imageUrl", imageUrl)
            .build()

        val constrains = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)

        // Created a Work Request
        val workRequest = OneTimeWorkRequestBuilder<FileDownloadWorker>()
            .setConstraints(constrains.build())
            .setInputData(inputData)
            .addTag("demo")
            .build()


        findViewById<Button>(R.id.DownLoadBtn).setOnClickListener {
            // Submit the WorkRequest to the system

            WorkManager.getInstance(this).enqueue(workRequest)
        }

        val status = findViewById<TextView>(R.id.downLoadStatus)
        i = progressBar.progress
        workManager.getWorkInfoByIdLiveData(workRequest.id).observe(this){
            if (it != null){
                val state = it.state
                status.text = state.toString()
                val progress = it.progress.getInt("progress",0)
                Log.d("Progress",  "State: $state Current Progress: $progress")
                Thread {
                    i = progress
                    handler.post {
                        progressBar.progress = progress
                        progressBarTv.text = progress.toString() + "/" + progressBar.max
                    }

                }.start()
            }

        }

    }
}