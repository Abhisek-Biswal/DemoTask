package com.example.myapplication

import android.annotation.SuppressLint
import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class NotificationAct : AppCompatActivity() {

    // Assigning variables to Notification Manager, Channel and Builder
    lateinit var notifManager : NotificationManager
    lateinit var notifChannel : NotificationChannel
    lateinit var notifBuilder : Notification.Builder

    // Evaluating ChannelID and Description for the Custom Notification
    private val description = "Some Description"
    private val channelID = "Some Channel ID"


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        // Declaring the button which onclick generates a notification
        val btn = findViewById<Button>(R.id.expandable_btn)

        // Notification Service for the Manager
        notifManager = getSystemService(Context.NOTIFICATION_SERVICE)
                as NotificationManager

        // Notifications are in the form of Intents
        val someintent = Intent(this, LauncherActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this,0,someintent,
            PendingIntent.FLAG_UPDATE_CURRENT)

        // Idea is to click the button and the notification appears
        btn.setOnClickListener {

            // Declaring a message (string) to be displayed
            // in the notification
            val message = "Some random text for testing purpose" +
                    "Some day you will be" +
                    "reading this" +
                    "and by the time, it'll be too late" +
                    "to confess everything."

            // If Min. API level of the phone is 26, then notification could be
            // made asthetic
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                notifChannel = NotificationChannel(channelID,description,
                    NotificationManager.IMPORTANCE_HIGH)
                notifChannel.enableLights(true)
                notifChannel.lightColor = Color.RED
                notifChannel.enableVibration(true)

                notifManager.createNotificationChannel(notifChannel)

                notifBuilder = Notification.Builder(this,channelID)
                    .setContentTitle("Title")
                    .setContentText("Text")
                    .setSmallIcon(R.mipmap.ic_launcher_round)

                    // Command to Insert a message in the Notification
                    .setStyle(Notification.BigTextStyle()// <-- Look here
                        .bigText(message))// <---- Look here


                    .setContentIntent(pendingIntent)
            }
            // Else the Android device would give out default UI attributes
            else{
                notifBuilder = Notification.Builder(this)
                    .setContentTitle("Title")
                    .setContentText("Text")
                    .setContentIntent(pendingIntent)
            }

            // Everything is done now and the Manager is to be notified about
            // the Builder which built a Notification for the application
            notifManager.notify(1234,notifBuilder.build())
        }


    }
}