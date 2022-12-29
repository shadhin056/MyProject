package com.example.myproject

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_dash_board.*

class DashBoardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dash_board)

        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        initView()
    }

    private fun initView() {
        val userType = intent.getStringExtra("userType")
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