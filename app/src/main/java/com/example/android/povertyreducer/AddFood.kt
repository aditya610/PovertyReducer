package com.example.android.povertyreducer

import android.location.Address
import android.location.Geocoder
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.android.povertyreducer.`class`.location
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_add_food.*

class AddFood : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_food)
        btnaddloc.setOnClickListener({
            if (etLoc.text.toString()!="") {
                val db = FirebaseFirestore.getInstance()
                val geocoder = Geocoder(this)
                val loc = etLoc.text.toString()
                var list = null as? List<Address>
                list = geocoder.getFromLocationName(loc, 1)
                val address = list.get(0)
                val lat = address.latitude
                val long = address.longitude
                val obj = location(lat, long, etinfo.text.toString())
                db.collection("povert").document("food").collection("location").document(obj.title).set(obj)
                etLoc.setText("")
                etinfo.setText("")
                Toast.makeText(this, "Added", Toast.LENGTH_SHORT).show()
            }
            else{

                Toast.makeText(this, "incomplete information", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
