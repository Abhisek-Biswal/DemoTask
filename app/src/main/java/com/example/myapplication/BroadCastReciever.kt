package com.example.myapplication

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Handler
import android.util.Log
import android.widget.RemoteViews
import android.widget.Toast
import androidx.core.app.NotificationCompat

class BroadCastReciever : BroadcastReceiver() {


    var isRegister = false
    var unregister = false
    override fun onReceive(context: Context?, intent: Intent) {
        Log.d("Action", intent.toString())
        Log.d("Context", context.toString())
        val percentageData = intent.getIntExtra("level", 0)
        if(isRegister) actionOnBroadCast(percentageData, MyApp.context!!)
        try {
            when (intent.action) {
                "Start" -> {
                    Thread{
                        MyApp.context!!.registerReceiver(this,
                            IntentFilter(Intent.ACTION_BATTERY_CHANGED)
                        )
                    }.start()
//                    startRegister(intent,true)
                    isRegister = true
                    unregister = true
                    Toast.makeText(context, "Start", Toast.LENGTH_SHORT).show()
                }
                "Stop" -> {
                    isRegister = false
                    if(unregister) {
                        try {

                            Handler().postDelayed({
                                MyApp.context!!.unregisterReceiver(this)
                            }, 10000)
                            Toast.makeText(MyApp.context!!, "Stop", Toast.LENGTH_SHORT).show()
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                        unregister = false
                    }
                }
            }
        } catch (e: Exception) {
            Toast.makeText(context, "$e", Toast.LENGTH_SHORT).show()
            e.printStackTrace()
        }
    }
    fun actionOnBroadCast(percentageData: Int,context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "BatteryAction", "BatteryChannel", NotificationManager.IMPORTANCE_HIGH
            )
            channel.description = "This Is Battery BroadCast"

//               val intent = Intent(this,ActionOnBroadCastReciever::class.java)
//             val pendingIntent = PendingIntent.getBroadcast(context,0,intent,PendingIntent.FLAG_IMMUTABLE)

            val startIntent =  Intent(context,BroadCastReciever::class.java).setAction("Start")

            val stopIntent =  Intent(context,BroadCastReciever::class.java).setAction("Stop")

            val layoutRemote = RemoteViews(context.packageName, R.layout.custom_expanded_notification)
            layoutRemote.setOnClickPendingIntent(
                R.id.broadCast_start, PendingIntent.getBroadcast(
                    context,
                    0,
                    startIntent,
                    PendingIntent.FLAG_IMMUTABLE
                )
            )
            layoutRemote.setOnClickPendingIntent(
                R.id.broadCast_stop, PendingIntent.getBroadcast(
                    context,
                    0,
                    stopIntent,
                    PendingIntent.FLAG_IMMUTABLE
                )
            )

            val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
            val builder = NotificationCompat.Builder(context, "BatteryAction")
            builder.apply {
                setSmallIcon(R.drawable.ic_battery_full)
                setContentTitle("Activity Second")
                priority = NotificationCompat.PRIORITY_HIGH
//                  setContentIntent(pendingIntent)
                setAutoCancel(true)
                layoutRemote.setTextViewText(
                    R.id.statusCheck_tv,
                    context.getString(R.string.battery_percentage) + " " + percentageData.toString()+"%"
                )
                setCustomBigContentView(layoutRemote)
            }
            manager.notify(0, builder.build())
        }
    }
}