package com.example.vasialeleka.newsappkotlin

import com.google.gson.annotations.SerializedName

class Hero{
    @SerializedName("name")
    val name: String? = null

    @SerializedName("realname")
    val realName: String? = null

    @SerializedName("firstappearance")
    val firstAppearance: String? = null

    @SerializedName("createdby")
    val createdBy: String? = null

    @SerializedName("published")
    val published: String? = null

    @SerializedName("imageurl")
    val imageUrl: String? = null

    @SerializedName("bio")
    val bio: String? = null
}