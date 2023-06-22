package com.example.myapplication

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MaterialDailogAct : AppCompatActivity() {

    lateinit var fromDatetxt: EditText
    lateinit var toDatetxt: EditText
    lateinit var timetxt: EditText

    var year = 0
    var month = 0
    var day = 0





    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_material_dailog)

        findViewById<Button>(R.id.btnclickkme).setOnClickListener {
            val dialog = Dialog(this)
            val dialogView =  dialog.setContentView(R.layout.customdailog)
            fromDatetxt =dialog.findViewById(R.id.edfromdate)
            toDatetxt = dialog.findViewById(R.id.edtodate)
            timetxt = dialog.findViewById(R.id.edtime)
            val submitbtn = dialog.findViewById<Button>(R.id.submitbtn)
            val cancelbtn = dialog.findViewById<Button>(R.id.cancelbtn)
            dialog.show()



            fromDatetxt.setOnClickListener {
                val currentDate = Calendar.getInstance()
                val datePickerDialog = DatePickerDialog(this, { _, year, monthOfYear, dayOfMonth ->
                    val selectedDate = Calendar.getInstance()
                    selectedDate.set(year, monthOfYear, dayOfMonth)

                    if (true) {
                        val  selectedFromDate = selectedDate
                        fromDatetxt.setText(formatDate(selectedFromDate))
                    } else {
                        val  selectedToDate = selectedDate
                        toDatetxt.setText(formatDate(selectedToDate))
                    }
                    // Set the minimum date for the "to date" DatePickerDialog
                    val minDateCalendar = Calendar.getInstance()
                    minDateCalendar.set(year, monthOfYear, dayOfMonth)
                    minDateCalendar.add(Calendar.DAY_OF_MONTH, 1) // Add one day to the selected "from date"
                    val minDate = minDateCalendar.timeInMillis

                    val toDatePickerDialog = DatePickerDialog(this, { _, toYear, toMonthOfYear, toDayOfMonth ->
                        val selectedToDate = Calendar.getInstance()
                        selectedToDate.set(toYear, toMonthOfYear, toDayOfMonth)
                        toDatetxt.setText(formatDate(selectedToDate))
                    }, year, month, day)

                    toDatePickerDialog.datePicker.minDate = minDate
                    toDatePickerDialog.show()
                },

                    currentDate.get(Calendar.YEAR),
                    currentDate.get(Calendar.MONTH),
                    currentDate.get(Calendar.DAY_OF_MONTH)
                )

                if (toDatetxt.text.isEmpty()) {
                    datePickerDialog.datePicker.minDate = System.currentTimeMillis()
                    datePickerDialog.show()
                } else {
                    toDatetxt.text.clear()
                    datePickerDialog.datePicker.minDate = System.currentTimeMillis()
                    currentDate.set(year, month, day)
                    datePickerDialog.datePicker.minDate = currentDate.timeInMillis
                    datePickerDialog.show()

                }
            }


            toDatetxt.setOnClickListener {
                val currentDate = Calendar.getInstance()
                val datePickerDialog = DatePickerDialog(this, { _, year, monthOfYear, dayOfMonth ->
                    val selectedDate = Calendar.getInstance()
                    selectedDate.set(year, monthOfYear, dayOfMonth)

                    if (false) {
                        val  selectedFromDate = selectedDate
                        fromDatetxt.setText(formatDate(selectedFromDate))
                    } else {
                        val  selectedToDate = selectedDate
                        toDatetxt.setText(formatDate(selectedToDate))
                    }
                },
                    currentDate.get(Calendar.YEAR),
                    currentDate.get(Calendar.MONTH),
                    currentDate.get(Calendar.DAY_OF_MONTH)
                )
                datePickerDialog.datePicker.minDate=currentDate.timeInMillis
                datePickerDialog.show()
            }

            timetxt.setOnClickListener {
                val currentTime = Calendar.getInstance()
                val timePickerDialog = TimePickerDialog(
                    this,
                    { _, hourOfDay, minute ->
                        val selectedTime = Calendar.getInstance()
                        selectedTime.set(Calendar.HOUR_OF_DAY, hourOfDay)
                        selectedTime.set(Calendar.MINUTE, minute)

                        timetxt.setText(formatTime(selectedTime))
                    },
                    currentTime.get(Calendar.HOUR_OF_DAY),
                    currentTime.get(Calendar.MINUTE),
                    false
                )
                timePickerDialog.show()
            }
            submitbtn.setOnClickListener {
                if(isValid()) this.showConfirmationDialog(dialog)
            }
            cancelbtn.setOnClickListener { dialog.dismiss() }
        }

    }
    private fun isValid(): Boolean {
        val fromDateText = fromDatetxt.text.toString()
        val toDateText = toDatetxt.text.toString()
        val timeText = timetxt.text.toString()
        if (fromDateText.isEmpty() && toDateText.isEmpty() && timeText.isEmpty()) {
            Toast.makeText( applicationContext,"Please Enter all the fields", Toast.LENGTH_SHORT).show()

        }else if(fromDateText.isEmpty()) {
            errorMessage("please select the from date")
        }else if(toDateText.isEmpty()){
            errorMessage("please select the to date")
        }else if(timeText.isEmpty()){
            errorMessage("please select the time")
        }else if(toDateText < fromDateText){
            errorMessage("Please check the date")
        }
        else{
            return true
        }
        return false
    }

    private fun showConfirmationDialog(dialog1: Dialog) {

        val builder = AlertDialog.Builder(this)

        builder.setTitle("Confirmation")
        builder.setMessage("Are you sure to submit the details?")
        val data = "Selected From Date: ${fromDatetxt.text}\n"+
                "Selected To   Date: ${toDatetxt.text}\n"+
                "Selected Time: " + "${timetxt.text}"
        val showdata: TextView = findViewById(R.id.tv_dialog)
        builder.setPositiveButton("OK") { dialog,_ ->
            showdata.text = data
            dialog.dismiss()
            dialog1.dismiss()

        }

        builder.setNegativeButton("Cancel") { dialog, _ ->
            dialog.dismiss()
        }
        builder.show()
    }
    private fun formatDate(calendar: Calendar): String {
        val dateFormat = android.text.format.DateFormat.getDateFormat(this)
        return dateFormat.format(calendar.time)
    }
    private fun formatTime(calendar: Calendar): String {
        val timeFormat = android.text.format.DateFormat.getTimeFormat(this)
        return timeFormat.format(calendar.time)
    }

    private  fun errorMessage(msg : String){
      AlertDialog.Builder(this).setTitle("Error").setMessage(msg).setPositiveButton("Ok",null).show()
    }
}





