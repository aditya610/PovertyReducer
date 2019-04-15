package com.example.android.povertyreducer.`class`

data class job(
        val heading:String,
        val company:String,
        val place:String,
        val contact:String
){
    constructor():this("","","","")
}
data class jobapply(
        val name:String,
        val company:String,
        val info:String,
        val contact:String,
        val expenditure:String
)

fun random(size:Int):ArrayList<job>
{
    val list=ArrayList<job>()
    list.add(job("require a cs graduate", "infosis", "delhi", "8800336849"))

    list.add(job("require a cs graduate", "infosis", "delhi", "8800336849"))

    list.add(job("require a cs graduate", "infosis", "delhi", "8800336849"))

    list.add(job("require a cs graduate", "infosis", "delhi", "8800336849"))

    list.add(job("require a cs graduate", "infosis", "delhi", "8800336849"))

    list.add(job("require a cs graduate", "infosis", "delhi", "8800336849"))

    list.add(job("require a cs graduate", "infosis", "delhi", "8800336849"))

    list.add(job("require a cs graduate", "infosis", "delhi", "8800336849"))

    return list
}