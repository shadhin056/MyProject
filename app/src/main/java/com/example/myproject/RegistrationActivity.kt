package com.example.myproject

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
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
                Toast.makeText(this, "Registration Successful from Mobile No : " + it.phoneNo, Toast.LENGTH_LONG).show()
            }
            val intent: Intent = Intent(
                this,
                LoginActivity::class.java
            )
            startActivity(intent)
        }
    }

    private fun buttonAction() {
        btnSubmit.setOnClickListener {
            if (edtCustomerName.text.toString() == "") {
                Toast.makeText(this, "Enter Name", Toast.LENGTH_LONG).show()
            } else if (edtPin.text.toString() == "") {
                Toast.makeText(this, "Enter Pin", Toast.LENGTH_LONG).show()
            } else if (spType.selectedItem.toString() == "") {
                Toast.makeText(this, "Enter User Type", Toast.LENGTH_LONG).show()
            } else if (edtDob.text.toString() == "") {
                Toast.makeText(this, "Enter Date of Birth", Toast.LENGTH_LONG).show()
            } else {
                reqForUserRegistration()
            }
        }
    }

    private fun reqForUserRegistration() {
        val model = RegistrationModel()
        model.name = edtCustomerName.text.toString()
        model.accountNo = edtPhone.text.toString()
        model.email = edtEmail.text.toString()
        if(spGender.selectedItem.toString()=="male"){
            model.gender  = "1"
        }else if(spGender.selectedItem.toString()=="female"){
            model.gender  = "2"
        }
        model.pin = edtPin.text.toString()
        model.dob = edtDob.text.toString()
        model.address = edtAddress.text.toString()

        if(spType.selectedItem.toString()=="Customer"){
            model.userType = "4"
        }else if(spType.selectedItem.toString()=="Agent"){
            model.userType = "3"
        }else if(spType.selectedItem.toString()=="Distributor"){
            model.userType = "2"
        }
        model.parentId = edtDistributeNo.text.toString()
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

        spType.onItemSelectedListener =  object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
if(position==2){
    edtDistributeNo.visibility=View.VISIBLE
}else{
    edtDistributeNo.visibility=View.GONE
}
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }

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