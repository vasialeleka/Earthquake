package com.example.vasialeleka.news.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Source {
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("name")
    @Expose
    private String name;
}
