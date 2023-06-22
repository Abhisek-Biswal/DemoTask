package com.example.myapplication

import android.content.Context
import android.os.Environment
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream
import java.net.HttpURLConnection
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*

class FileDownloadWorker(context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {


    override fun doWork(): Result {


        val imageUrl = inputData.getString("imageUrl")
        Log.d("Img", imageUrl.toString())
        if (imageUrl == null) return Result.failure()
        downloadImg(imageUrl)
        return Result.success()

    }

    private fun downloadImg(imgUri: String?) {

        var input: InputStream?
        var count = 0
        var outPut: OutputStream?

        try {
            val url = URL(imgUri)
            val connection = url.openConnection() as HttpURLConnection
            connection.doInput = true
            val dataSize = connection.contentLength

            Log.d("Size", connection.contentLength.toString())
            connection.connect()
            input = connection.inputStream

            val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
            val fileName = "image_$timeStamp.jpg"
            val storageDir = applicationContext.getExternalFilesDir(Environment.DIRECTORY_DCIM)
            val outFile = File(storageDir, fileName)
            outPut = FileOutputStream(outFile)



            val data = ByteArray(2048)
            var total = 0
            count = input.read(data)
            Log.d("Count", count.toString())
            while (count != -1) {
                total += count
                setProgressAsync(workDataOf("progress" to (total * 100) / dataSize))
                outPut.write(data, 0, count)
                Log.d("Count", data.toString())
                count = input.read(data)
            }
            outPut.run {
                flush()
                close()
            }
            input.close()
        } catch (e: Exception) {
            e.printStackTrace()

        }
    }

}



