package com.example.myapplication

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class IntentAct : AppCompatActivity() {

    lateinit var editText: EditText
    lateinit var clickBtn: Button
    //val image = findViewById<ImageView>(R.id.iv)
    lateinit var image : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent)

        editText = findViewById(R.id.ed_link)
        clickBtn = findViewById(R.id.btn_link)
        image = findViewById<ImageView>(R.id.iv)

        if (intent.hasExtra("android.intent.extra.STREAM")) {
            val bundle = intent?.extras?.get("android.intent.extra.STREAM")
            val uri = Uri.parse(bundle.toString())
            image.setImageURI(uri)
        }

        clickBtn.setOnClickListener {

            val uri = Uri.parse("https://www.google.com")
            val browsers = arrayOf(
                Intent(Intent.ACTION_VIEW, uri).setPackage("com.android.chrome"),
                Intent(Intent.ACTION_VIEW, uri).setPackage("org.mozilla.firefox"),
                Intent(Intent.ACTION_VIEW, uri).setPackage("com.opera.browser")
            )

            val chooser = Intent.createChooser(browsers[0], "Open with")
            chooser.putExtra(
                Intent.EXTRA_INITIAL_INTENTS,
                browsers.sliceArray(1 until browsers.size)
            )
            startActivity(chooser)
        }
    }
}