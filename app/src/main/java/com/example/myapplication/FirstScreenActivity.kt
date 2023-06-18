package com.example.myapplication



import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity


class FirstScreenActivity : AppCompatActivity() {



    private val registerActivity = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if (it.resultCode == 0){
            val result = it.data
            findViewById<TextView>(R.id.tv_data_first_screen).text = result?.getStringExtra("str").toString()
        }
        else{
            val imageView = findViewById<ImageView>(R.id.img_view)
            imageView.setImageURI(it.data?.data)
            imageView.visibility = View.VISIBLE
            Toast.makeText(this, "Img", Toast.LENGTH_SHORT).show()
        }
    }

    private var count = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_screen)



        findViewById<Button>(R.id.btn_first_screen).setOnClickListener {
            val intent = Intent(this,SecondScreenActivity::class.java)
            registerActivity.launch(intent)
        }

        findViewById<Button>(R.id.img_btn).setOnClickListener {
            askPermission()
        }

    }
    private fun askPermission(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            if (checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
                val permissions = arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                requestPermissions(permissions,2)

            }else{
                pickImageFromGallery()
            }
        }else{
            pickImageFromGallery()
        }
    }

    private fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        registerActivity.launch(intent)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            pickImageFromGallery()
        }else{
            if(count > 2){
                val alertDialog = AlertDialog.Builder(this)
                val dialog = alertDialog.create()
                alertDialog.apply {
                    setTitle("Permission Denied")
                    setMessage("You have to give permission")
                    setPositiveButton("Ok"){
                            _,_ ->  val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                        val uri = Uri.fromParts("package",packageName,null)
                        intent.data = uri
                        startActivity(intent)
                    }
                    setNegativeButton("Cancel"){
                            _,_ -> dialog.dismiss()
                    }
                }.show()
            }
            count++


        }
    }
}




