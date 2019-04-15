package com.example.android.povertyreducer

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.util.Log
import com.example.android.povertyreducer.`class`.job
import com.example.android.povertyreducer.`class`.location

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.firestore.*
import kotlinx.android.synthetic.main.activity_food.*

class FoodActivity : AppCompatActivity(), OnMapReadyCallback {
var latitude=-34.0
    var longitude=151.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food)

        addfood.setOnClickListener({
            val actintent=Intent(
                    this,AddFood::class.java

            )
            startActivity(actintent)
        })
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
         val mMap = googleMap
        var list=ArrayList<location>()
        val coarseperm = ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION)
        val fineperm = ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
        ActivityCompat.requestPermissions(
                this,
                arrayOf(
                        android.Manifest.permission.ACCESS_COARSE_LOCATION,
                        android.Manifest.permission.ACCESS_FINE_LOCATION

                ),
                212
        )
        mMap.isMyLocationEnabled=true
        val db= FirebaseFirestore.getInstance()
        val query=db.collection("povert").document("food").collection("location")
        query.addSnapshotListener(object: EventListener<QuerySnapshot> {
            override fun onEvent(p0: QuerySnapshot?, p1: FirebaseFirestoreException?) {
                if (p0 != null) {

                    for( dc in p0.documentChanges)
                    {
                        if(dc.type== DocumentChange.Type.ADDED)
                        {
                            val obj=dc.document.toObject(location::class.java)

                            mMap.addMarker(MarkerOptions().position(LatLng(obj.lat,obj.long)).title(obj.title))


                        }
                    }

                }
            }


        }

        )
    }
    @SuppressLint("MissingPermission")
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 212) {
            if (grantResults[1] == PackageManager.PERMISSION_GRANTED)
            {

            }
        }
    }
}
