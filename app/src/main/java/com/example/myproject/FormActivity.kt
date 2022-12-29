package com.example.myproject

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.examshadhin.view.viewmodel.CustomerViewModel
import com.example.myproject.model.RegistrationModel
import kotlinx.android.synthetic.main.activity_form.*
import kotlinx.android.synthetic.main.activity_form.btnSubmit
import kotlinx.android.synthetic.main.activity_registration.*

class FormActivity : AppCompatActivity() {
    var userType = ""
    var user = ""
    var fromRoleCode = ""
    var toRoleCode = ""
    var txCode = ""
    var btnCode = ""
    private lateinit var customerViewModel: CustomerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)
        customerViewModel = ViewModelProvider(this).get(CustomerViewModel::class.java)
        initView()
        observeViewModel()
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        btnSubmit.setOnClickListener {

            if (edtAc.text.toString() == "") {
                Toast.makeText(this, "Enter Account No", Toast.LENGTH_LONG).show()
            }else if (edtAmount.text.toString() == "") {
                Toast.makeText(this, "Enter Amount", Toast.LENGTH_LONG).show()
            }else {
                submitForUser()
            }
        }
    }
    private fun initView() {
        userType = intent.getStringExtra("userType").toString()
        user = intent.getStringExtra("user").toString()
        fromRoleCode = intent.getStringExtra("fromRoleCode").toString()
        toRoleCode = intent.getStringExtra("toRoleCode").toString()
        txCode = intent.getStringExtra("txCode").toString()
        btnCode = intent.getStringExtra("btnCode").toString()
        txtTitle.setText(btnCode)

    }
    private fun submitForUser() {
        val model = RegistrationModel()
        model.userType = userType
        model.fromRoleCode = fromRoleCode
        model.toRoleCode = toRoleCode
        model.fromAccountNo = user
        model.toAccountNo = edtAc.text.toString()
        model.txAmount = edtAmount.text.toString()
        model.transactionCode = txCode
        this.let { it1 -> customerViewModel.reqForForm(model) }
    }
    fun observeViewModel() {
        customerViewModel.error_response.observe(this) {
            it?.let {
                if (it) {
                    Toast.makeText(this, "error occurred", Toast.LENGTH_LONG).show()
                }
            }
        }
        customerViewModel.customerFormResponse.observe(this) {

            it?.let {
                Log.e("check ",it.status)
                if(it.status=="SUCCESS"){
                    Toast.makeText(this, "Transaction Successful", Toast.LENGTH_LONG).show()
                }else{
                    Toast.makeText(this, "Failed", Toast.LENGTH_LONG).show()
                }
            }
        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                val intent: Intent = Intent(
                    this,
                    DashBoardActivity::class.java
                )
                intent.putExtra("userType", userType)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}