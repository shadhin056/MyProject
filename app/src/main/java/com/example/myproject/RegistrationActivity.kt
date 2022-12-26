package com.example.myproject

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.examshadhin.view.util.Constant
import com.example.examshadhin.view.viewmodel.CustomerViewModel
import com.example.myproject.model.RegistrationModel
import kotlinx.android.synthetic.main.activity_registration.*
import java.util.*

class RegistrationActivity : AppCompatActivity() {

    private lateinit var customerViewModel: CustomerViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        customerViewModel = ViewModelProvider(this).get(CustomerViewModel::class.java)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        spinnerLoad()
        buttonAction()
        observeViewModel()
        edtDob.setOnClickListener {
            val c = Calendar.getInstance()

            // on below line we are getting
            // our day, month and year.
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            // on below line we are creating a
            // variable for date picker dialog.
            val datePickerDialog = DatePickerDialog(
                // on below line we are passing context.
                this,
                { view, year, monthOfYear, dayOfMonth ->
                    // on below line we are setting
                    // date to our text view.
                    edtDob.setText((dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year))
                },
                // on below line we are passing year, month
                // and day for the selected date in our date picker.
                year,
                month,
                day
            )
            // at last we are calling show
            // to display our date picker dialog.
            datePickerDialog.show()
        }
    }

    fun observeViewModel() {
        customerViewModel.error_response.observe(this) {
            it?.let {
                if (it) {
                    Toast.makeText(this, "error occurred", Toast.LENGTH_LONG).show()
                }
            }
        }
        customerViewModel.customerRegistrationResponse.observe(this) {

            it?.let {
                Toast.makeText(this, "User-Created and ID : " + it.id, Toast.LENGTH_LONG).show()
                if (Constant.logCheck) {
                    Log.d(RegistrationActivity::class.java.getSimpleName(), it.id.toString())
                }
            }
        }
    }

    private fun buttonAction() {
        btnSubmit.setOnClickListener {
            if (edtCustomerName.text.toString() == "") {
                Toast.makeText(this, "Enter Name", Toast.LENGTH_LONG).show()
            } else if (edtCustomerEmail.text.toString() == "") {
                Toast.makeText(this, "Enter Email", Toast.LENGTH_LONG).show()
            } else {
                reqForUserRegistration()
            }
        }
    }

    private fun reqForUserRegistration() {
        val model = RegistrationModel()
        model.gender = spGender.getSelectedItem().toString()
        model.email = edtCustomerEmail.text.toString()
        model.name = edtCustomerName.text.toString()
        model.status = spType.getSelectedItem().toString()
        this.let { it1 -> customerViewModel.reqForUserRegistration(model) }
    }

    private fun spinnerLoad() {
        //postal gender
        val adapterForGender = ArrayAdapter.createFromResource(
            this@RegistrationActivity,
            R.array.gender, android.R.layout.simple_spinner_item
        )
        adapterForGender.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spGender.setAdapter(adapterForGender)

        //post status
        val adapterForStatus = ArrayAdapter.createFromResource(
            this@RegistrationActivity,
            R.array.status, android.R.layout.simple_spinner_item
        )
        adapterForStatus.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spType.setAdapter(adapterForStatus)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                val intent: Intent = Intent(
                    this,
                    MainActivity::class.java
                )
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}