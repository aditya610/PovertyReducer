package com.example.android.povertyreducer.`class`

import android.content.Context
import android.support.constraint.R.id.parent
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import com.example.android.povertyreducer.R
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_rvincomecalc.view.*
import kotlinx.android.synthetic.main.incomelayout.view.*


class incomerv(var list:ArrayList<income>,var con:Context,var mainname:String) :RecyclerView.Adapter<incomerv.viewholder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        val li = parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view= li.inflate(R.layout.incomelayout,
                parent,false)
        return viewholder(view)
    }

    override fun getItemCount(): Int =list.size

    override fun onBindViewHolder(holder: viewholder, position: Int) {
        var name:String=""
        var age:String=""
        var gender:String=""

            var view=LayoutInflater.from(con).inflate(R.layout.activity_rvincomecalc,null);

        holder.itemView.etname?.addTextChangedListener(object:TextWatcher{
            override fun afterTextChanged(s: Editable?) {
               }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                name=holder.itemView.etname.text.toString()

            }

        })
        holder.itemView.etage?.addTextChangedListener(object:TextWatcher{
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                age=holder.itemView.etage.text.toString()

            }

        })
        holder.itemView.etgender?.addTextChangedListener(object:TextWatcher{
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (gender=="") {
                    gender = holder.itemView.etgender.text.toString()
                }
                if(gender!=""&&(gender=="m"||gender=="f"))
                {
                    Log.d("name11",name+age+gender)
                    val db=FirebaseFirestore.getInstance()
                    val obj=income(name,age,gender)
                    db.collection("povert").document("income").collection(mainname).document(obj.name).set(obj)
                }
            }

        })

            view.btnproceed.setOnClickListener({
                val name = holder.itemView.etname.text.toString()
                val age = holder.itemView.etage.text.toString()
                val gender = holder.itemView.etgender.text.toString()
                Log.d("ans", gender + "  " + age + "  " + name)
            })


    }

    class viewholder(view: View):RecyclerView.ViewHolder(view){

    }
}