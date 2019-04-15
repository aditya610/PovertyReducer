package com.example.android.povertyreducer.`class`

import android.location.Address
data class location(
    val lat:Double,
    val long:Double,
    val title:String
    ){
    constructor():this(-34.0,151.0,"")
}