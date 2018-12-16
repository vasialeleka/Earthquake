package com.e.vasialeleka.kotlintabview

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class EarthquakeAdapter (val list:MutableList<EarthQuake>):RecyclerView.Adapter<EarthquakeAdapter.ViewHolder>(){
    class ViewHolder(itemView : View) :RecyclerView.ViewHolder(itemView){

val place:TextView = itemView.findViewById(R.id.place)
        val mag:TextView=itemView.findViewById(R.id.mag)
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
       val view:View = LayoutInflater.from(parent.context ).inflate(R.layout.layout,parent,false)
    return ViewHolder(view)
    }


    override fun getItemCount()=list.size

    override fun onBindViewHolder(holder:ViewHolder, position: Int) {
        holder.place.text = list[position].place
        holder.mag.text = list[position].mag.toString()


     }

}