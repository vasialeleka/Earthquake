package com.example.vasialeleka.news.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class News {
    @SerializedName("status")
    private String status;

    @SerializedName("totalResults")
   // @Expose
    private int totalResult;


    @SerializedName("articles")
  //  @Expose
    private List<Article> article;
}
