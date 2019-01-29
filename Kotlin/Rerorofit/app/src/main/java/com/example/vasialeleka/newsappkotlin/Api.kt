package com.example.vasialeleka.newsappkotlin

import retrofit2.Call
import retrofit2.http.GET

interface Api {
    @get:GET("marvel")
    val heroes: Call<List<Hero>>

    companion object {
        val BASE_URL = "https://simplifiedcoding.net/demos/"
    }
}