package com.example.myapplication


import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope

import kotlinx.coroutines.launch


class HomeScreen : AppCompatActivity() {
    lateinit var  floatbtn: FloatingActionButton
    lateinit var  database : RegisterDatabase
    lateinit var  recyclerview : RecyclerView
    private  lateinit  var  myadapter : taskadapter


    @SuppressLint("SuspiciousIndentation")
    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_screen)
        floatbtn = findViewById(R.id.btnFloat)


        database = Room.databaseBuilder(this,RegisterDatabase::class.java,"Register_task_table").build()

        GlobalScope.launch {
       val  tasklist = database.RegisterDatabaseDao().getAllTask()
            recyclerview = findViewById(R.id.recycview)
            myadapter = taskadapter(  tasklist as MutableList<RegisterEntity>)
            recyclerview.adapter = myadapter
        }

//        recyclerview = findViewById(R.id.recycview)
//      //  val dao = database.RegisterDatabaseDao()
//      //  val enties = dao.getAllTask()
//          myadapter = taskadapter(tasklist)
//        recyclerview.adapter = myadapter


        // Logout click event
         findViewById<Button>(R.id.btnlogout).setOnClickListener{
             val builder = AlertDialog.Builder(this)
             builder.setTitle("Confirmation Logout")
             builder.setMessage("Are you sure you want to logout")
             builder.setPositiveButton("OK") { dialog,_ ->
                 startActivity(Intent(this,LoginActivity::class.java))
             }
             builder.setNegativeButton("Cancel") { dialog, _ ->
                 dialog.dismiss()
             }
             builder.show()
         }
        //floating button event
        floatbtn.setOnClickListener{
            val dialog = Dialog(this)
            dialog.setContentView(R.layout.custom_dialog_box)
            val title = dialog.findViewById<EditText>(R.id.eddiatitle)
            val description = dialog.findViewById<EditText>(R.id.eddiades)
            val cancelbnt = dialog.findViewById<Button>(R.id.cancelbtn)
            val addbtn = dialog.findViewById<Button>(R.id.submitbtn)
            dialog.show()
            //add button action
            addbtn.setOnClickListener {
                if(title.text.isEmpty()){
                 title.error = "Tittle is required"
                    return@setOnClickListener
                }else if(description.text.isEmpty()){
                    description.error = "Description is required"
                    return@setOnClickListener
                }else{

                    database = Room.databaseBuilder(this,RegisterDatabase::class.java,"Register_task_table").build()
                    GlobalScope.launch {
                            database.RegisterDatabaseDao().insert(RegisterEntity(0,title.text.toString(), description.text.toString()))
                    }
                    Toast.makeText(this,"Task Added successfully!!",Toast.LENGTH_LONG).show()
                    dialog.dismiss()
                }
            }
            //cancel button action
            cancelbnt.setOnClickListener {
                dialog.dismiss()
            }
        }

    }
}


