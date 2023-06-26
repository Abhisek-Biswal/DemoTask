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
import java.util.jar.Pack200.Packer.PROGRESS


class WorkManagerAct : AppCompatActivity() {



    private lateinit var progressBar: ProgressBar
    private lateinit var progressBarTv: TextView
    private lateinit var status: TextView
    private lateinit var urlEditText: EditText

    private val workManager = WorkManager.getInstance()
    @SuppressLint("SetTextI18n", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work_manager)

        progressBar = findViewById(R.id.ImgProgressBar)
        progressBarTv = findViewById(R.id.progressTv)
        status = findViewById(R.id.downLoadStatus)
        urlEditText = findViewById(R.id.urlDownload)

        val handler = Handler()

        findViewById<Button>(R.id.cancelBtn).setOnClickListener {
            workManager.cancelAllWorkByTag("demo")
        }

        findViewById<Button>(R.id.downLoadBtn).setOnClickListener {
            val imageUrl = urlEditText.text.toString()
            if (imageUrl.isNotEmpty()) {
                startImageDownload(imageUrl)
            }
        }
    }

    private fun startImageDownload(imageUrl: String) {
        val inputData = Data.Builder()
            .putString("imgUrl", imageUrl)
            .build()

        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val workRequest = OneTimeWorkRequestBuilder<FileDownloadWorker>()
            .setConstraints(constraints)
            .setInputData(inputData)
            .addTag("demo")
            .build()

        workManager.enqueue(workRequest)

        workManager.getWorkInfoByIdLiveData(workRequest.id).observe(this) { workInfo ->
            if (workInfo != null) {
                val state = workInfo.state
                status.text = state.toString()

                if (workInfo.state == WorkInfo.State.SUCCEEDED) {
                    val outputData = workInfo.outputData
                    val imagePath = outputData.getString("imageFile")

                    if (!imagePath.isNullOrEmpty()) {
                        // Use the image file path as needed
                        Log.d("ImagePath", imagePath)
                    }
                }

                val progress = workInfo.progress.getInt(PROGRESS, 0)
                Log.d("Progress", "State: $state Current Progress: $progress")
                progressBar.progress = progress
                progressBarTv.text = "$progress/${progressBar.max}"
            }
        }
    }
}

