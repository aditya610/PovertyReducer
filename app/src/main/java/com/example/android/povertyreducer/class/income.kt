package com.example.android.povertyreducer.`class`

data class income(
       var name:String,
       var age:String,
       var gender:String
){
    constructor():this("","","")
}
fun randon(n:Int):ArrayList<income >
{ val list=ArrayList<income>()
    for(i:Int in 1..n)
    {
        list.add(income("","",""))
    }
    return list
}
