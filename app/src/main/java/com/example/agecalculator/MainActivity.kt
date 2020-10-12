package com.example.agecalculator



import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnDatePicker.setOnClickListener { view ->
            datePickerFunction(view)
//            Toast.makeText(this,"hello there",Toast.LENGTH_SHORT).show()
        }
    }

    fun datePickerFunction(view: View){
        val myCalendar = Calendar.getInstance()
        var year = myCalendar.get(Calendar.YEAR)
        var month = myCalendar.get(Calendar.MONTH)
        var dayOfMonth = myCalendar.get(Calendar.DAY_OF_MONTH)
      val DPD =  DatePickerDialog(this,DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            var selectedDate = "$dayOfMonth/${month+1}/$year"
            tvSelectedDate.setText(selectedDate).toString()
            // simple date format object. format the date using locale
            val SDF = SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH)
            var theDate = SDF.parse(selectedDate)
            var dateInMilliSecond = theDate!!.time
            var currentDate = SDF.parse(SDF.format(System.currentTimeMillis()))
            var currentDateInMilliSecond = currentDate!!.time
            var dateDifference = currentDateInMilliSecond - dateInMilliSecond
            // millisecond to second convert
            var dateInSecond = dateDifference/1000
            //second to minute convert
            var dateInMinutes = dateInSecond/60
            //minutes to Hours convert
            var dateInHours = dateInMinutes/60
            // hours to day convert
            var dateInDay = dateInHours/24
            //day to month convert
            var dateInMonth = dateInDay/30
            //day to year convert
            var dateInYear = dateInDay/365

            tvAgeInYear.text = dateInYear.toString()
            tvAgeInMonth.text=dateInMonth.toString()
            tvAgeInDays.text=dateInDay.toString()
            tvAgeInHours.text = dateInHours.toString()
            tvAgeInMinutes.text=dateInMinutes.toString()
            tvAgeInSecond.text=dateInSecond.toString()
        },year,month,dayOfMonth)

        // limit of selected max date. max selected date less then current date
        DPD.datePicker.maxDate=Date().time - (1000 * 60 * 60 * 24)
        DPD.show()
    }
}