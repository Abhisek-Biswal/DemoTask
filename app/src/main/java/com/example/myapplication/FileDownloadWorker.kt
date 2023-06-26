package com.example.myapplication

import android.content.Context
import android.os.Environment
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream
import java.net.HttpURLConnection
import java.net.URL

class FileDownloadWorker(context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {

    override fun doWork(): Result {
        val imageUrl = inputData.getString("imgUrl")
        if (imageUrl.isNullOrEmpty()) {
            Log.e("FileDownloadWorker", "Image URL is empty or null")
            return Result.failure()
        }

        return try {
            downloadImg(imageUrl)
            Result.success()
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("FileDownloadWorker", "Image download failed: ${e.message}")
            Result.failure()
        }
    }



    private fun downloadImg(imgUri: String?) {
        var input: InputStream?
        var output: OutputStream?

        try {
            val url = URL(imgUri)
            val connection = url.openConnection() as HttpURLConnection
            connection.doInput = true
            connection.connect()
            input = connection.inputStream

            val fileName = "${System.currentTimeMillis()}.jpg"
            val imagesDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
            val image = File(imagesDirectory, fileName)
            output = FileOutputStream(image)

            val data = ByteArray(2048)
            var count: Int
            while (input.read(data).also { count = it } != -1) {
                output.write(data, 0, count)
            }

            output.flush()
            output.close()
            input.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
