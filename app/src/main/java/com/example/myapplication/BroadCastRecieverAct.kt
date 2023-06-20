package com.example.myapplication

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.TextView


class BroadCastRecieverAct : AppCompatActivity() {

    lateinit var batteryLevelText : TextView
    lateinit var batteryLevelProgress: ProgressBar
    lateinit var mReciever: BroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_broad_cast_reciever)

        batteryLevelText= findViewById(R.id.tv_battery_status)
        batteryLevelProgress = findViewById(R.id.pb_battery_status)
        mReciever = BatteryBroadCastReciever()
    }

    override fun onStart() {
        registerReceiver(mReciever, IntentFilter(Intent.ACTION_BATTERY_CHANGED))
        super.onStart()
    }

    override fun onStop() {
        unregisterReceiver(mReciever)
        super.onStop()
    }
    private inner class BatteryBroadCastReciever: BroadcastReceiver() {
        @SuppressLint("SetTextI18n")
        override fun onReceive(p0: Context?, p1: Intent?) {
            val level = p1?.getIntExtra(BatteryManager.EXTRA_LEVEL,0)
            batteryLevelText.text = "${getString(R.string.battery_status)} $level"
            if (level != null) {
                batteryLevelProgress.progress = level
            }

            if (intent?.action == Intent.ACTION_BATTERY_CHANGED) {
                val level = intent.getIntExtra("level", -1)
                val scale = intent.getIntExtra("scale", -1)
                val batteryLevel = (level * 100 / scale.toFloat()).toInt()

                val notificationManager =
                    getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

                val channelId = "BatteryLevelChannel"
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    val channel = NotificationChannel(
                        channelId,
                        "Battery Level Channel",
                        NotificationManager.IMPORTANCE_DEFAULT
                    )
                    notificationManager.createNotificationChannel(channel)
                }
                val notificationBuilder = Notification.Builder(this@BroadCastRecieverAct, channelId)
                    .setSmallIcon(android.R.drawable.ic_dialog_info)
                    .setContentTitle("Battery Level")
                    .setContentText("Current battery level: $batteryLevel%")
                    .setPriority(Notification.PRIORITY_DEFAULT)
                    .setAutoCancel(true)

                notificationManager.notify(1, notificationBuilder.build())
            }

        }
    }
}