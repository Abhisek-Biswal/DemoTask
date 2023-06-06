package com.example.myapplication

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.messaging.FirebaseMessaging

class MessengerNotificationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_messenger_notification)

        // Subscribe to the topic for specific device notifications
        FirebaseMessaging.getInstance().subscribeToTopic("specificdevicetopic")
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Topic subscription successful
                    Toast.makeText(this,"Messenger notify!!!", Toast.LENGTH_LONG).show()
                } else {
                    // Topic subscription failed
                    Toast.makeText(this,"Messenger not notify!!!",Toast.LENGTH_LONG).show()
                }
            }
    }
}