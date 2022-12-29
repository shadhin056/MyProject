package com.example.myproject

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_dash_board.*

class DashBoardActivity : AppCompatActivity() {
    var userType = ""
    var user = ""
    var name = ""
    var balance = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dash_board)
        //supportActionBar!!.setDisplayShowHomeEnabled(true)
        //supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        initView()
        onCLick()
    }

    private fun onCLick() {
        val intent = Intent(this, FormActivity::class.java)

        btnCashIn.setOnClickListener {
            intent.putExtra("userType", userType)
            intent.putExtra("user", user)
            intent.putExtra("fromRoleCode", "3")
            intent.putExtra("toRoleCode", "4")
            intent.putExtra("txCode", "5234")
            intent.putExtra("btnCode", "cash in")
            intent.putExtra("balance", balance)
            intent.putExtra("name", name)
            startActivity(intent)
        }
        btnCashOut.setOnClickListener {
            intent.putExtra("userType", userType)
            intent.putExtra("user", user)
            intent.putExtra("fromRoleCode", "4")
            intent.putExtra("toRoleCode", "3")
            intent.putExtra("txCode", "5343")
            intent.putExtra("btnCode", "cash out")
            intent.putExtra("balance", balance)
            intent.putExtra("name", name)
            startActivity(intent)
        }
        btnFT.setOnClickListener {
            intent.putExtra("userType", userType)
            intent.putExtra("user", user)
            intent.putExtra("fromRoleCode", "4")
            intent.putExtra("toRoleCode", "4")
            intent.putExtra("txCode", "4044")
            intent.putExtra("btnCode", "ft")
            intent.putExtra("balance", balance)
            intent.putExtra("name", name)
            startActivity(intent)
        }
        btnAgWithDraw.setOnClickListener {
            intent.putExtra("userType", userType)
            intent.putExtra("user", user)
            intent.putExtra("fromRoleCode", "3")
            intent.putExtra("toRoleCode", "2")
            intent.putExtra("txCode", "4032")
            intent.putExtra("btnCode", "agent withdraw")
            intent.putExtra("balance", balance)
            intent.putExtra("name", name)
            startActivity(intent)
        }
        btnAgRecharge.setOnClickListener {
            intent.putExtra("userType", userType)
            intent.putExtra("user", user)
            intent.putExtra("fromRoleCode", "2")
            intent.putExtra("toRoleCode", "3")
            intent.putExtra("txCode", "4023")
            intent.putExtra("btnCode", "Agent Recharge")
            intent.putExtra("balance", balance)
            intent.putExtra("name", name)
            startActivity(intent)
        }
        btnLogOut.setOnClickListener {
            val intent: Intent = Intent(
                this,
                LoginActivity::class.java
            )
            startActivity(intent)
        }

    }

    private fun initView() {
         userType = intent.getStringExtra("userType").toString()
         user = intent.getStringExtra("user").toString()
         balance = intent.getStringExtra("balance").toString()
         name = intent.getStringExtra("name").toString()

        txtBalance.text = balance
        txtName.text = name

        if(userType=="AG"){
            btnCashIn.visibility= View.VISIBLE
            btnCashOut.visibility= View.GONE
            btnFT.visibility= View.GONE
            btnAgWithDraw.visibility= View.VISIBLE
            btnAgRecharge.visibility= View.GONE

        }else if(userType=="DH"){
            btnCashIn.visibility= View.GONE
            btnCashOut.visibility= View.GONE
            btnFT.visibility= View.GONE
            btnAgWithDraw.visibility= View.GONE
            btnAgRecharge.visibility= View.VISIBLE

        }else if(userType=="CU"){
            btnCashIn.visibility= View.GONE
            btnCashOut.visibility= View.VISIBLE
            btnFT.visibility= View.VISIBLE
            btnAgWithDraw.visibility= View.GONE
            btnAgRecharge.visibility= View.GONE
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                val intent: Intent = Intent(
                    this,
                    LoginActivity::class.java
                )
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}