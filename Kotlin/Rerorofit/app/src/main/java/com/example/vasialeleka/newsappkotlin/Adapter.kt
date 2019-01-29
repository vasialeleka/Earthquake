package com.example.vasialeleka.newsappkotlin

import android.content.Context

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class Adapter(internal var list: List<Hero>, internal var context: Context): RecyclerView.Adapter<Adapter.MyViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): MyViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.item_hero, viewGroup, false)

        return MyViewHolder(v)
    }

    override fun onBindViewHolder(myViewHolder: MyViewHolder, i: Int) {
        val hero = list[i]
        myViewHolder.name.text = hero.name
        Picasso.get().load(hero.imageUrl).into(myViewHolder.image)

    }

    override fun getItemCount(): Int {
        return list.size
    }
    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var name: TextView
        internal var image: ImageView

        init {
            name = itemView.findViewById(R.id.name)
            image = itemView.findViewById(R.id.image)
        }


    }
}
