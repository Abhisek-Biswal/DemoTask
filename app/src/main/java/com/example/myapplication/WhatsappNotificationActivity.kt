package com.example.myapplication

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.messaging.FirebaseMessaging

class WhatsappNotificationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_whatsapp_notification)

        // Subscribe to the topic for specific device notifications
        FirebaseMessaging.getInstance().subscribeToTopic("specific_devicetopic")
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Topic subscription successful
                    Toast.makeText(this,"Whatsapp notify!!!",Toast.LENGTH_LONG).show()
                } else {
                    // Topic subscription failed
                    Toast.makeText(this,"Whatsapp not notify!!!",Toast.LENGTH_LONG).show()
                }
            }
    }
}