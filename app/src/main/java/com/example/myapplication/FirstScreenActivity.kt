package com.example.myapplication


import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat

class FirstScreenActivity : AppCompatActivity() {

    private var btn_first_screen: Button? = null
    private var tv_data_first_screen: TextView? = null
    private var imgView: ImageView? = null
    private var gallerybtn: Button? = null

    private var activityLauncher = registerForActivityResult<Intent, ActivityResult>(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->

        if (result.resultCode == Activity.RESULT_OK) {
            val intent = result.data
            if (intent != null) {
                val data = intent.getStringExtra("result")
                tv_data_first_screen!!.text = data
            }
        }
    }

//    private var requestPermissionLauncher= registerForActivityResult(ActivityResultContracts.RequestPermission()
//    ){isGranted: Boolean ->
//        if(isGranted){
//
//        }
//        else{
//
//        }
//    }

    private var galleryActivityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->

        if(result.resultCode == Activity.RESULT_OK){
            val imageUri: Uri = result.data?.data!!
            imgView?.setImageURI(imageUri)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_screen)

        btn_first_screen = findViewById(R.id.btn_first_screen)
        tv_data_first_screen = findViewById(R.id.tv_data_first_screen)

        imgView = findViewById(R.id.img_view)
        gallerybtn = findViewById(R.id.img_btn)




        btn_first_screen?.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@FirstScreenActivity, SecondScreenActivity::class.java)
            activityLauncher.launch(intent)
        })

        gallerybtn?.setOnClickListener(View.OnClickListener {

            val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            galleryActivityResultLauncher.launch(galleryIntent)
        })

    }
//    private fun requestPermission(){
//
//        when{
//            ContextCompat.checkSelfPermission(this,Manifest.permission.CAMERA)
//        }
//    }
}
