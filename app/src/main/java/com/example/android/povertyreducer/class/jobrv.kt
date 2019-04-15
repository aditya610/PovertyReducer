package com.example.android.povertyreducer.`class`

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.android.povertyreducer.R

class jobrv(val list:ArrayList<job>):RecyclerView.Adapter<jobrv.viewholder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        val li = parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view= li.inflate(R.layout.no_of_job,
                parent,false)
        return viewholder(view)
    }

    override fun getItemCount(): Int {
       return list.size
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {
        holder.head.text = list[position].heading
        holder.comp.text=list[position].company
        holder.contact.text=list[position].contact
        holder.place.text=list[position].place
    }


    class viewholder(val view:View):RecyclerView.ViewHolder(view)
    {
       val head=view.findViewById<TextView>(R.id.tvhead)
        val comp=view.findViewById<TextView>(R.id.tvcomp)
        val contact=view.findViewById<TextView>(R.id.tvcontact)
        val place=view.findViewById<TextView>(R.id.tvplace)
    }
}