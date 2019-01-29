package com.example.vasialeleka.newsappkotlin

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager

import android.support.v7.widget.RecyclerView
import android.widget.Toast
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {
// val API_KEY = "e60697f7f9874f35a82708c0df1618a9"
  //  val BASE_URL = ""
lateinit var recView : RecyclerView
lateinit var adapter :  Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recView = findViewById(R.id.recView)

        var linearLayoutManager = LinearLayoutManager(this)
        recView.layoutManager = linearLayoutManager

        var retrofit = Retrofit.Builder()
            .baseUrl(Api.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        var api = retrofit.create(Api::class.java)
        var call = api.heroes

        call.enqueue(object : Callback<List<Hero>> {
            @SuppressLint("LongLogTag")
            override fun onResponse(call: Call<List<Hero>>, response: Response<List<Hero>>) {
                val heroes = response.body()
                adapter = Adapter(heroes!!, this@MainActivity)
                recView.adapter = adapter
                adapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<List<Hero>>, t: Throwable) {
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()

            }
        })




    }
}
