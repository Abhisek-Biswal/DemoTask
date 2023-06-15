package com.example.myapplication

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.android.material.button.MaterialButton
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.textview.MaterialTextView
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import kotlin.time.Duration.Companion.minutes

class MaterialDailogAct : AppCompatActivity() {

    lateinit var mtv: MaterialTextView
    lateinit var mbtn: MaterialButton

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_material_dailog)

        mtv = findViewById(R.id.tv_show_data)
        mbtn = findViewById(R.id.btn_click)
        mbtn.setOnClickListener(View.OnClickListener {
            //showDatePickerDailogBox()
            showTimePickerDailogBox()
        })
    }
    @SuppressLint("SetTextI18n")
    private fun showDatePickerDailogBox() {
            val materialDatePicker=
                MaterialDatePicker.Builder.datePicker().setTitleText("Select Date").build()
            materialDatePicker.addOnPositiveButtonClickListener {

                mtv.text = "Selected Date: " + materialDatePicker.headerText
            }
            materialDatePicker.show(supportFragmentManager, "TAG")

    }
    @SuppressLint("SetTextI18n")
    private fun showTimePickerDailogBox(){
        val timePickerDailog =
            MaterialTimePicker.Builder().setTitleText("Select Time").build()
        timePickerDailog.addOnPositiveButtonClickListener{

            mtv.text = "Selected Time: " + timePickerDailog.hour +":"+ timePickerDailog.minute
        }
        timePickerDailog.show(supportFragmentManager,"TAG2")


    }
}