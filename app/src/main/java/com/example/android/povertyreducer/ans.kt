package com.example.android.povertyreducer

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_ans.*

class ans : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ans)
        val ans=intent.getStringExtra("expenditure")
        val name=intent.getStringExtra("name")
        tvansexp.text=ans.subSequence(0,6)
        tvansname.text="Hello "+name
    }

    override fun onBackPressed() {
        super.onBackPressed()

        val actintent= Intent(this,MainActivity::class.java)
        startActivity(actintent)
    }
}
