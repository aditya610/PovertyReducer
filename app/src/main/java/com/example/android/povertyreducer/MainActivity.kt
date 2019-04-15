package com.example.android.povertyreducer

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        incomecalc.setOnClickListener({
            var actintent= Intent(this,IncomeCalcmain::class.java)
            startActivity(actintent)
        })
        jobprovider.setOnClickListener({
            var actintent=Intent(this,com.example.android.povertyreducer.jobprovider::class.java)
            startActivity(actintent)
        })
foodprovider.setOnClickListener({
    var actintent=Intent(this,FoodActivity::class.java)
    startActivity(actintent)
})


    }

}
