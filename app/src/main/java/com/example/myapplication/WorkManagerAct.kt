package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.work.*
import java.util.jar.Pack200

class WorkManagerAct : AppCompatActivity() {

    private val mWorkManager = WorkManager.getInstance()
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {

        val workManager = WorkManager.getInstance()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work_manager)


        val progressBar = findViewById<ProgressBar>(R.id.ImgProgressBar)
        val progressBarTv = findViewById<TextView>(R.id.progressTv)
        var i = 0
        val handler = Handler()
        findViewById<Button>(R.id.cancelBtn).setOnClickListener {
            mWorkManager.cancelAllWorkByTag("demo")
        }


        val inputData = Data.Builder()
            .putString("imageUrl","https://upload.wikimedia.org/wikipedia/commons/thumb/f/ff/Pizigani_1367_Chart_10MB.jpg/8192px-Pizigani_1367_Chart_10MB.jpg")
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
                val progress = it.progress.getInt(Pack200.Packer.PROGRESS,0)
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