package com.example.android.povertyreducer

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.android.povertyreducer.`class`.jobapply
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_resume_job.*

class ResumeJob : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resume_job)
        btnapplyjob.setOnClickListener({
            if (etcomp.text.toString()!="")
            {  val db=FirebaseFirestore.getInstance()
            val obj=jobapply(
                    etname.text.toString(),
                    etcomp.text.toString(),
                    etinfo.text.toString(),
                    etcontactno.text.toString(),
                    etincomecalculated.text.toString()

            )
            db.collection("povert").document("job").collection("apply").document(obj.company).collection(obj.expenditure).document(obj.name).set(obj)
            Toast.makeText(this,"Application Submitted", Toast.LENGTH_SHORT).show()
            etname.setText("")
            etcomp.setText("")
            etcontactno.setText("")
            etinfo.setText("")
            etincomecalculated.setText("")}
            else{
                Toast.makeText(this,"Incomplete information", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
