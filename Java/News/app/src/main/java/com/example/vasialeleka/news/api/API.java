package com.example.vasialeleka.news.api;
import com.example.vasialeleka.news.models.News;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface API {
    @GET("top-headlines")
    Call<News> getNews(
            @Query("country") String country,
            @Query("apiKey") String apiKey

    );
    @GET("top-headlines")
    Call<News> getCategoryNews(

            @Query("country") String country,
            @Query("apiKey") String apiKey,
            @Query("category") String category
    );
}
