package com.example.myapplication

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.core.content.getSystemService
import androidx.test.core.app.ApplicationProvider
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.google.firebase.messaging.ktx.remoteMessage
import java.util.*

class MyFirebaseMessagingService: FirebaseMessagingService() {
    lateinit var title: String
    lateinit var message: String
    lateinit var manager: NotificationManager
    // Assign channel ID
    var channel_id = "notification_channel"

    override fun onMessageReceived(remotemessage: RemoteMessage) {
        super.onMessageReceived(remotemessage)

        title= remotemessage.notification!!.title!!
        message=  remotemessage.notification!!.body!!


        manager = this.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        showPushNotification()

    }
    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d("Token Refreshed",token)
    }
    @SuppressLint("UnspecifiedImmutableFlag", "ServiceCast")
    private fun showPushNotification() {

        // Pass the intent to switch to the MainActivity
        val intent = Intent(applicationContext,FCMTask::class.java)

        intent.putExtra("title",title)
        intent.putExtra("message",message)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        intent.flags= Intent.FLAG_ACTIVITY_NEW_TASK

        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){

            var channel = NotificationChannel(channel_id,"push_notification",NotificationManager.IMPORTANCE_HIGH)

            manager.createNotificationChannel(channel)
        }

        var builder = NotificationCompat.Builder(this,channel_id)

            .setContentTitle(title)
            .setContentText(message)
            .setAutoCancel(true)



        // Pass the intent to PendingIntent to start the
        // next Activity
        val pendingIntent = PendingIntent.getActivity(
            applicationContext, 0, intent,
            PendingIntent.FLAG_ONE_SHOT)

        builder.setContentIntent(pendingIntent)
        manager.notify(0,builder.build())
    }
}


