package com.example.android.povertyreducer

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_income_calcmain.*
import kotlinx.android.synthetic.main.activity_main.*

class IncomeCalcmain : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_income_calcmain)

            btnproceed.setOnClickListener({
                if(etnooffamily.text.toString()!="") {
                    val actintent = Intent(
                            this,
                            rvincomecalc::class.java
                    )
                    actintent.putExtra("name", etmainname.text.toString())
                    val no = etnooffamily.text.toString()
                    Log.e("no", no.toString())
                    actintent.putExtra("no", no)
                    startActivity(actintent)
                }
                else{
                    Toast.makeText(this,"incomplete information",Toast.LENGTH_SHORT).show()
                }

            })


    }
}
