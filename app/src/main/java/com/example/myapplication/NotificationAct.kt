package com.example.myapplication

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.RemoteViews
import androidx.appcompat.app.AppCompatActivity

class NotificationAct : AppCompatActivity() {

    // Assigning variables to Notification Manager, Channel and Builder
    lateinit var notifManager : NotificationManager
    lateinit var notifChannel : NotificationChannel
    lateinit var notifBuilder : Notification.Builder

    // Evaluating ChannelID and Description for the Custom Notification
    private val description = "Some Description"
    private val channelID = "Some Channel ID"

    private val channel_id = "Some Id"
    private val  description1 = "Some Description1"

    private val channel_id2 = "Some Id2"
    private val description2 = "Some Description2"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        // Declaring the button which onclick generates a notification
        val btn = findViewById<Button>(R.id.expandable_btn)

        val btn2 = findViewById<Button>(R.id.fullscreen_btn)

        val btn3 = findViewById<Button>(R.id.custom_notification_btn)

        // Notification Service for the Manager
        notifManager = getSystemService(Context.NOTIFICATION_SERVICE)
                as NotificationManager


        // Idea is to click the button and the notification appears
        btn.setOnClickListener {

            // Declaring a message (string) to be displayed
            // in the notification
            val message = "Good Morning" +
                    " Expandable" +
                    " Notification" +
                    " Completed." +
                    " Have a nice day."

            // If Min. API level of the phone is 26, then notification could be
            // made asthetic
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                notifChannel = NotificationChannel(
                    channelID, description,
                    NotificationManager.IMPORTANCE_HIGH
                )
                notifChannel.enableVibration(true)

                notifManager.createNotificationChannel(notifChannel)

                notifBuilder = Notification.Builder(this, channelID)
                    .setContentTitle("Title")
                    .setContentText("Text")
                    .setSmallIcon(R.mipmap.ic_launcher_round)

                    // Command to Insert a message in the Notification
                    .setStyle(
                        Notification.BigTextStyle()// <-- Look here
                            .bigText(message)
                    )// <---- Look here


            }
            // Else the Android device would give out default UI attributes
            else {
                notifBuilder = Notification.Builder(this)
                    .setContentTitle("Title")
                    .setContentText("Text")

            }

            // Everything is done now and the Manager is to be notified about
            // the Builder which built a Notification for the application
            notifManager.notify(1234, notifBuilder.build())
        }

        btn2.setOnClickListener {


//            val textMessage = "Click On the Message"

            // pendingIntent is an intent for future use i.e after
            // the notification is clicked, this intent will come into action

            val intent = Intent(this, AfterNotificationAct::class.java)

            val pendingIntent =
                PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

            // RemoteViews are used to use the content of
            // some different layout apart from the current activity layout
            val contentView = RemoteViews(packageName, R.layout.activity_after_notification)

            // checking if android version is greater than oreo(API 26) or not
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                notifChannel = NotificationChannel(
                    channel_id,
                    description1,
                    NotificationManager.IMPORTANCE_HIGH
                )
                notifChannel.enableVibration(false)
                notifManager.createNotificationChannel(notifChannel)

                notifBuilder = Notification.Builder(this, channel_id)
                    .setContentTitle("Title")
                    .setContentText("Click on this Message")
                    .setSmallIcon(R.mipmap.ic_launcher_round)
                    .setLargeIcon(
                        BitmapFactory.decodeResource(
                            this.resources,
                            R.drawable.ic_launcher_background
                        )
                    )
                    .setContentIntent(pendingIntent)


            } else {

                notifBuilder = Notification.Builder(this)
                    .setContentTitle("Title")
                    .setContentText("Click on this Message")
                    .setSmallIcon(R.mipmap.ic_launcher_round)
                    .setLargeIcon(
                        BitmapFactory.decodeResource(
                            this.resources,
                            R.drawable.ic_launcher_background
                        )
                    )
                    .setContentIntent(pendingIntent)
            }
            notifManager.notify(1234, notifBuilder.build())
        }
        btn3.setOnClickListener {



                val notificationLayout = RemoteViews(packageName,
                    R.layout.custom_notification_act
                )
                notifBuilder = Notification.Builder(this, "channel_id2")
                    .setContentTitle("Result")
                    .setSmallIcon(R.drawable.ic_image)
                    .setStyle(Notification.DecoratedCustomViewStyle())
                    .setCustomBigContentView(notificationLayout)
                    .setPriority(Notification.PRIORITY_DEFAULT)
                notifManager.notify(1234, notifBuilder.build())

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                    val name = "Notification Channel3"
//                    val descriptionText = "Notification Description"
//                    val importance = NotificationManager.IMPORTANCE_DEFAULT
                      notifChannel = NotificationChannel(channel_id2, description2, NotificationManager.IMPORTANCE_DEFAULT).apply {
                    }
                    notifManager.createNotificationChannel(notifChannel)
                }



        }

    }
}