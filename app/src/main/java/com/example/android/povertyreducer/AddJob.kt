package com.example.android.povertyreducer

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.android.povertyreducer.R.id.btnaddjob
import com.example.android.povertyreducer.R.id.etcomp
import com.example.android.povertyreducer.`class`.job
import com.firebase.ui.auth.AuthUI
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_add_job.*
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*


class AddJob : AppCompatActivity() {
    val firebaseUser: FirebaseUser? = FirebaseAuth.getInstance().currentUser
    val RC_SIGN_IN=1000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_job)

        val db = FirebaseFirestore.getInstance()
        if(firebaseUser!=null) {
            btnlogout.setOnClickListener({
                AuthUI.getInstance().signOut(this).addOnCompleteListener(object : OnCompleteListener<Void> {
                    override fun onComplete(p0: Task<Void>) {
                        val intent = Intent(this@AddJob, jobprovider::class.java)
                        startActivity(intent)
                    }
                })
            })
            btnaddjob.setOnClickListener({

                if (etcomp.text.toString() != "") {
                    val obj = job(
                            etheading.text.toString(), etcomp.text.toString(), etplace.text.toString(), etcontactno.text.toString()
                    )
                    db.collection("povert").document("job").collection("company").document(obj.company).set(obj)
                    Toast.makeText(this, "Job Added", Toast.LENGTH_SHORT).show()
                    etheading.setText("")
                    etcomp.setText("")
                    etcontactno.setText("")
                    etplace.setText("")
                    val actintent = Intent(this, jobprovider::class.java)
                    startActivity(actintent)
                } else {
                    Toast.makeText(this, "Incomplete information", Toast.LENGTH_SHORT).show()
                }
            })
        }
        else{
            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setIsSmartLockEnabled(false)
                            .setAvailableProviders(Arrays.asList(
                                    AuthUI.IdpConfig.GoogleBuilder().build(),
                                    AuthUI.IdpConfig.EmailBuilder().build(),
                                    AuthUI.IdpConfig.PhoneBuilder().build()))
                            .build(),
                    RC_SIGN_IN)
        }
    }
}
