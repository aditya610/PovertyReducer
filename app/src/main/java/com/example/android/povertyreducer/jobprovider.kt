package com.example.android.povertyreducer

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.NonNull
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.Adapter
import android.widget.Toast
import com.example.android.povertyreducer.`class`.job
import com.example.android.povertyreducer.`class`.jobrv
import com.example.android.povertyreducer.`class`.random
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.*
import kotlinx.android.synthetic.main.activity_jobprovider.*

class jobprovider : AppCompatActivity() {
    var list=ArrayList<job>()
    val adapter=jobrv(list)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jobprovider)
  fab.setOnClickListener({
      val actintent=Intent(
              this,AddJob::class.java
      )
      startActivity(actintent)
  })
        fab1.setOnClickListener({
            val actintent=Intent(
                    this,ResumeJob::class.java
            )
            startActivity(actintent)
        })


        rvjob.layoutManager=LinearLayoutManager(this,RecyclerView.VERTICAL,false)
        rvjob.adapter=adapter
        data()


      /*  query.document().get().addOnCompleteListener(OnCompleteListener {

            fun onComplete(@NonNull task:Task<QuerySnapshot>) {
                if (task.isSuccessful()) {
                    for (document in task.getResult()!!) {
                        val taskItem = document.toObject(job::class.java)
                        list.add(taskItem)
                    }
                    adapter.notifyDataSetChanged()
                }
            }
        })*/
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val actintent=Intent(this,MainActivity::class.java)
        startActivity(actintent)

    }
    fun data()
    {
        val db=FirebaseFirestore.getInstance()
        val query=db.collection("povert").document("job").collection("company")
        query.addSnapshotListener(object:EventListener<QuerySnapshot>{
            override fun onEvent(p0: QuerySnapshot?, p1: FirebaseFirestoreException?) {
                if (p0 != null) {
                    list.clear()
                    for( dc in p0.documentChanges)
                    {
                        if(dc.type==DocumentChange.Type.ADDED)
                        {
                            val data=dc.document.toObject(job::class.java)
                            list.add(data)
                        }
                    }
                    adapter.notifyDataSetChanged()

                }
            }

        }

        )
    }



}
