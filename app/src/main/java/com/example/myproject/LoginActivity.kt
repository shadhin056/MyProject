package com.example.myproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.examshadhin.view.util.Constant
import com.example.examshadhin.view.viewmodel.CustomerViewModel
import com.example.myproject.model.RegistrationModel
import kotlinx.android.synthetic.main.activity_registration.*

class LoginActivity : AppCompatActivity() {

    private lateinit var customerViewModel: CustomerViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        customerViewModel = ViewModelProvider(this).get(CustomerViewModel::class.java)
        buttonAction()
        observeViewModel()
    }

    fun observeViewModel() {
        customerViewModel.error_response.observe(this) {
            it?.let {
                if (it) {
                    Toast.makeText(this, "error occurred", Toast.LENGTH_LONG).show()
                }
            }
        }
        customerViewModel.customerLoginResponse.observe(this) {

            it?.let {
                if(it.status=="1"){
                    Toast.makeText(this, "Login Successful", Toast.LENGTH_LONG).show()
                    val intent = Intent(this, DashBoardActivity::class.java)
                    intent.putExtra("userType", it.userType)
                    intent.putExtra("user", edtPhone.text.toString())
                    intent.putExtra("balance", it.balance)
                    intent.putExtra("name", it.name)
                    startActivity(intent)
                }else{
                    Toast.makeText(this, "Login Failed", Toast.LENGTH_LONG).show()
                }
                if (Constant.logCheck) {
                    // Log.d(RegistrationActivity::class.java.getSimpleName(), it.id.toString())
                }
            }
        }
    }
    private fun buttonAction() {
        btnSubmit.setOnClickListener {

            if (edtPhone.text.toString() == "") {
                Toast.makeText(this, "Please Enter Mobile No", Toast.LENGTH_LONG).show()
            } else if (edtPin.text.toString() == "") {
                Toast.makeText(this, "Please Enter Pin", Toast.LENGTH_LONG).show()
            } else {
                loginForUser()
            }
        }
    }

    private fun loginForUser() {
        val model = RegistrationModel()
        model.accountNo = edtPhone.text.toString()
        model.pin = edtPin.text.toString()
        this.let { it1 -> customerViewModel.reqForUserLogin(model) }
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