package com.e.vasialeleka.newsapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class News {
    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("totalResults")
    @Expose
    private int totalResult;


     @SerializedName("articles")
    @Expose
    private List<Article> article;

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTotalResult(int totalResult) {
        this.totalResult = totalResult;
    }

    public void setArticle(List<Article> article) {
        this.article = article;
    }
}
