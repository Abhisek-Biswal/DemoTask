package com.example.myapplication

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService: FirebaseMessagingService() {
    lateinit var title: String
    lateinit var message: String
    lateinit var manager: NotificationManager


    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        title= remoteMessage.notification?.title!!
        message=  remoteMessage.notification?.body!!



        remoteMessage.data["onclick"]?.let { Log.d("TAG_data", it) }
        Log.d(TAG,"Message $remoteMessage")

        if (remoteMessage.notification != null) {

            showPushNotification(title, message)

            when (remoteMessage.data["onclick"]) {
                "Facebook" -> {
                    val intent = Intent(this, WhatsappNotificationActivity::class.java)
                    intent.addCategory(Intent.CATEGORY_LAUNCHER)
                    intent.setAction(Intent.ACTION_MAIN)
                    PendingIntent.getActivity(
                        getApplicationContext(),
                        0,
                        intent,
                        PendingIntent.FLAG_IMMUTABLE
                    )
                }
                "Instagram" -> {
                    val intent = Intent(this, MessengerNotificationActivity::class.java)
                    intent.addCategory(Intent.CATEGORY_LAUNCHER)
                    intent.setAction(Intent.ACTION_MAIN)
                    PendingIntent.getActivity(
                        getApplicationContext(),
                        0,
                        intent,
                        PendingIntent.FLAG_IMMUTABLE
                    )
                }
                "Notification" -> {
                    val intent = Intent(this, ChatNotificationActivity::class.java)
                    intent.addCategory(Intent.CATEGORY_LAUNCHER)
                    intent.setAction(Intent.ACTION_MAIN)
                    PendingIntent.getActivity(
                        getApplicationContext(),
                        0,
                        intent,
                        PendingIntent.FLAG_IMMUTABLE
                    )
                }
            }
        }

    }
    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d("Token Refreshed",token)
    }
    @SuppressLint("UnspecifiedImmutableFlag", "ServiceCast")
    private fun showPushNotification(title: String?,message: String?) {

        val channel_id = "notification_channel"

        val builder = NotificationCompat.Builder(this,channel_id)

            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle(title)
            .setContentText(message)
            .setAutoCancel(true)


        manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){

            val channel = NotificationChannel(channel_id,"push_notification",NotificationManager.IMPORTANCE_HIGH)

            manager.createNotificationChannel(channel)
        }
        manager.notify(0,builder.build())


    }
}


