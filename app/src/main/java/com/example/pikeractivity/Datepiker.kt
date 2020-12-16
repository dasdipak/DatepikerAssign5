package com.example.pikeractivity

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.lang.Math.abs
import java.text.SimpleDateFormat
import java.util.*

class Datepiker : AppCompatActivity() {

    private lateinit var etjoining: EditText
    private lateinit var etDob: EditText
    private lateinit var tvworked: TextView
    private lateinit var tvage: TextView
    private lateinit var btncalculate: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_datepiker)

        //biding
        etjoining = findViewById(R.id.etjoining)
        etDob = findViewById(R.id.etDob)
        tvworked = findViewById(R.id.tvworked)
        tvage = findViewById(R.id.tvage)
        btncalculate = findViewById(R.id.btncalculate)

        //set onclicklister on edit text
        etjoining.setOnClickListener {
           joinload(etjoining)
        }
        etDob.setOnClickListener {
            dobload(etDob)
        }


        //onclicklistner on button calculate
        btncalculate.setOnClickListener {
            var c = Calendar.getInstance()
            var year = c.get(Calendar.YEAR)
            var month = c.get(Calendar.MONTH)+1
            var day = c.get(Calendar.DAY_OF_MONTH)

            val final = "$month/$day/$year"


            val dFormat = SimpleDateFormat("MM/dd/yyyy");

            // Calculate Dob
            calculatejoin(dFormat = dFormat, final = final);

            //Calculate join date
            calculateDob(dFormat = dFormat, dobfinal = final);

        }
    }
    fun joinload(view: View) {
        val datePickerDialog = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth  ->
                var date = "${month+1}/${dayOfMonth}/${year}"
                etjoining.setText(date)
            },
            Calendar.getInstance().get(Calendar.YEAR),
            Calendar.getInstance().get(Calendar.MONTH),
            Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()
    }

    fun dobload(view: View) {
        val datePickerDialog = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener {_, year, month, dayOfMonth ->
                var ddate = "${month+1}/${dayOfMonth}/${year}"
                etDob.setText(ddate)
            },
            Calendar.getInstance().get(Calendar.YEAR),
            Calendar.getInstance().get(Calendar.MONTH),
            Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()
    }


    fun calculatejoin(dFormat: SimpleDateFormat, final: String) {

        val Joindate = etjoining.text.toString();
        var dfinal: Date = Date()
        var dinitial: Date = Date()
        try {
            dfinal = dFormat.parse(final);
            dinitial = dFormat.parse(Joindate);
        } catch (e: Exception) {
            Toast.makeText(this, "Date must be in selected", Toast.LENGTH_SHORT).show()
            if (Joindate.equals("")) {

            } else if (final.equals("")) {

            }
            return;
        }

        val diff: Long = abs(dfinal.time - dinitial.time);
        val diffDate = diff / (24 * 60 * 60 * 1000);
        var year = diffDate / 365;
        val r = diffDate % 365;
        val month = r / 30
        val d = r%30


        val res = "You spent ${year} years ${month} months and ${d} days in this company.";
        tvworked.setText(res);

    }
    //function for dob
    fun calculateDob(dFormat: SimpleDateFormat, dobfinal: String) {
        val etDOB = etDob.text.toString();
        var dfinal: Date = Date()
        var dinitial: Date = Date()
        try {
            dfinal = dFormat.parse(dobfinal);
            dinitial = dFormat.parse(etDOB);
        } catch (e: Exception) {
            Toast.makeText(this, "Date must be in selected", Toast.LENGTH_SHORT).show()
            if (dobfinal.equals("")) {

            } else if (etDOB.equals("")) {
            }
            return;
        }
        val diff: Long = abs(dfinal.time - dinitial.time);
        val diffDate = diff / (24 * 60 * 60 * 1000);
        var year = diffDate / 365;
        val r = diffDate % 365;
        val month = r / 30;
        val d = r%30


        val res = "You are ${year} years ${month} months - ${d} days  old";
        tvage.setText(res);
    }
}
