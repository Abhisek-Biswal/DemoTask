package com.example.myapplication

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.RemoteViews
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat

class ActionOnBroadCastReceiver : AppCompatActivity() {

    private lateinit var statusTv: TextView
    private lateinit var liveBatteryData: TextView
    private var isRegistered = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_action_on_broad_cast_reciever)

        statusTv = findViewById(R.id.status_battery_tv)
        liveBatteryData = findViewById(R.id.liveStatusOfBattery_tv)

        updateStatus("Start")

        val startButton = findViewById<Button>(R.id.broadCast_start)
        val stopButton = findViewById<Button>(R.id.broadCast_stop)

        startButton.setOnClickListener {
            register()
        }

        stopButton.setOnClickListener {
            unregister()
        }

        val startIntent = Intent(this, ActionOnBroadCastReceiver::class.java)
        startIntent.action = "Start"
        val startPendingIntent = PendingIntent.getBroadcast(
            this,
            0,
            startIntent,
            PendingIntent.FLAG_IMMUTABLE
        )

        val stopIntent = Intent(this, ActionOnBroadCastReceiver::class.java)
        stopIntent.action = "Stop"
        val stopPendingIntent = PendingIntent.getBroadcast(
            this,
            0,
            stopIntent,
            PendingIntent.FLAG_IMMUTABLE
        )

        val layoutRemote = RemoteViews(packageName, R.layout.custom_expanded_notification)
        layoutRemote.setOnClickPendingIntent(R.id.broadCast_start, startPendingIntent)
        layoutRemote.setOnClickPendingIntent(R.id.broadCast_stop, stopPendingIntent)

        val notificationBuilder = NotificationCompat.Builder(this, "BatteryAction")
            .setSmallIcon(R.drawable.ic_battery_full)
            .setContentTitle("Activity Second")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .setCustomBigContentView(layoutRemote)

        createNotificationChannel()

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(0, notificationBuilder.build())
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "BatteryAction",
                "BatteryChannel",
                NotificationManager.IMPORTANCE_HIGH
            )
            channel.description = "This is Battery Broadcast"
            val notificationManager =
                getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun register() {
        if (!isRegistered) {
            val batteryStatusTextView = findViewById<TextView>(R.id.liveStatusOfBattery_tv)

            // Create an instance of the BatteryReceiver and register it
            val batteryReceiver = BatteryReceiver(batteryStatusTextView)
            registerReceiver(batteryReceiver, IntentFilter(Intent.ACTION_BATTERY_CHANGED))

            updateStatus("Registered")
            isRegistered = true
        }
    }


    private fun unregister() {
        if (isRegistered) {
            updateStatus("Unregistered")
            liveBatteryData.text = ""
            isRegistered = false
        }
    }

    private fun updateStatus(status: String) {
        statusTv.text = status
        Toast.makeText(this, status, Toast.LENGTH_SHORT).show()
    }
}
