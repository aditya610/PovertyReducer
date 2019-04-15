package com.example.android.povertyreducer

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.Toast
import com.example.android.povertyreducer.`class`.income
import com.example.android.povertyreducer.`class`.incomerv
import com.example.android.povertyreducer.`class`.randon
import kotlinx.android.synthetic.main.activity_rvincomecalc.*
import java.util.*
import com.google.common.io.Flushables.flush
import org.json.JSONObject
import org.json.JSONException
import android.os.AsyncTask
import java.net.HttpURLConnection
import java.net.URL
import android.R.attr.data
import com.google.gson.JsonArray
import java.io.*
import java.net.MalformedURLException
import org.json.JSONArray
import android.R.attr.data
import com.example.android.povertyreducer.`class`.job
import com.google.firebase.firestore.*
import com.google.firebase.firestore.EventListener
import com.google.gson.JsonObject
import kotlin.collections.ArrayList
import kotlin.math.log


class rvincomecalc : AppCompatActivity() {
var list=ArrayList<Int>()
var name= String()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //View view=getLayoutInflator.inflate(Layout_name,null)
        setContentView(R.layout.activity_rvincomecalc)
        rvmain.layoutManager = LinearLayoutManager(
                this,
                RecyclerView.VERTICAL, false)


        intent?.let {

            val no = it.getStringExtra("no")
             name=it.getStringExtra("name")

            if (no != null) {

                Log.e("nooffamilymember", no.toString())
               var list = randon(no.toInt())
                var con = this
                val incomeadapter = incomerv(list, con,name)
                rvmain.adapter = incomeadapter
            }
        }
        btnproceed.setOnClickListener({
        HTTPAsyncTask().execute("https://56889cf5.ngrok.io/extract")
            Log.d("hello","hello")
            FetchData().execute()
            Log.d("hello","hello")


    })

    }

    @Throws(IOException::class, JSONException::class)
    private fun httpPost(myUrl: String): String {
        val result = ""
        val url = URL(myUrl)
        val conn = url.openConnection() as HttpURLConnection
        conn.setRequestMethod("POST")
        conn.setRequestProperty("Content-Type", "application/json; charset=utf-8")
        val jsonObject = buidJsonObject()
        setPostRequestContent(conn, jsonObject)
        conn.connect()
        Log.d("hello","hello1")
        return conn.getResponseMessage() + ""

    }


    private inner class HTTPAsyncTask : AsyncTask<String, Void, String>() {
        override fun doInBackground(vararg urls: String): String {
            // params comes from the execute() call: params[0] is the url.
            try {
                try {
                    return httpPost(urls[0])
                } catch (e: JSONException) {
                    e.printStackTrace()
                    return "Error!"
                }

            } catch (e: IOException) { return "Unable to retrieve web page. URL may be invalid." }

        }
        // onPostExecute displays the results of the AsyncTask.

    }


    @Throws(JSONException::class)
    private fun buidJsonObject(): JSONObject {
        val db= FirebaseFirestore.getInstance()
        val studentsObj = JSONObject()
        val query=db.collection("povert").document("income").collection(name)
        query.addSnapshotListener(object: EventListener<QuerySnapshot> {
            override fun onEvent(p0: QuerySnapshot?, p1: FirebaseFirestoreException?) {
                if (p0 != null) {
                    list.clear()
                    for( dc in p0.documentChanges)
                    {
                        if(dc.type== DocumentChange.Type.ADDED)
                        {
                            val data=dc.document.toObject(income::class.java)
                            //Log.d("age11",data.age)
                            list.add(data.age.toInt())
                        }
                        for(i in list)
                        {
                          //  Log.d("age111",i.toString())
                        }
                    }
                    for(i in list)
                    {
                         Log.d("age111",i.toString())
                    }
                    val jsonObject = JSONObject()
                    val jsonArray = JSONArray()
                    var k=0
                    for(i in list)
                    {
                      //  jsonObject.put(k.toString(), i)
                        jsonArray.put(i)
                      //  k=k+1
                    }

                    studentsObj.accumulate("send", jsonArray)





                }
            }

        }

        )



        return studentsObj
    }

    @Throws(IOException::class)
    private fun setPostRequestContent(conn: HttpURLConnection, jsonObject: JSONObject) {

        val os = conn.getOutputStream()
        val writer = BufferedWriter(OutputStreamWriter(os, "UTF-8"))
        writer.write(jsonObject.toString())
        Log.i(MainActivity::class.java.toString(), jsonObject.toString())
        writer.flush()
        writer.close()
        os.close()
    }

    inner class FetchData : AsyncTask<String, Void, String>() {
        internal var data = ""
        internal var singleParsed = JSONArray()
        val list= ArrayList<String>()
        override fun doInBackground(vararg voids: String):String? {
            try {
                val url = URL("https://56889cf5.ngrok.io/predict")
                val httpURLConnection = url.openConnection() as HttpURLConnection
                val inputStream = httpURLConnection.inputStream
                val bufferedReader = BufferedReader(InputStreamReader(inputStream))
                var line: String? = ""
                while (line != null) {
                    line = bufferedReader.readLine()
                    data = data + line
                }

                val JO = JSONObject(data)
                singleParsed = JO.getJSONArray("prediction")
                var count=0;
                Log.d("hell03l",singleParsed.length().toString())

           while(count<=singleParsed.length())
           {
               list.add(singleParsed.getString(count))
               count=count+1
           }

            } catch (e: MalformedURLException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            } catch (e: JSONException) {
                e.printStackTrace()
            }
return ""
        }


        override fun onPostExecute(aVoid:String) {
            super.onPostExecute(aVoid)

            Log.d("hello1",aVoid)
            var sum=0.0
            for ( i in list) {
                sum=sum+i.toFloat()
                Log.d("hello3",i.toString())
            }
            Log.d("ans1",sum.toString())
            val actintent=Intent(this@rvincomecalc,ans::class.java)
            actintent.putExtra("expenditure",sum.toString())
            actintent.putExtra("name",name)
            startActivity(actintent)
        }
    }
}
