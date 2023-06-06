package com.example.myapplication

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.messaging.FirebaseMessaging

class ChatNotificationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_notification)

        FirebaseMessaging.getInstance().subscribeToTopic("specific_device")
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Topic subscription successful

                    Toast.makeText(this,"chat notify", Toast.LENGTH_SHORT).show()
                } else {
                    // Topic subscription failed
                    Toast.makeText(this,"chat not notify", Toast.LENGTH_SHORT).show()
                }
            }
    }
}