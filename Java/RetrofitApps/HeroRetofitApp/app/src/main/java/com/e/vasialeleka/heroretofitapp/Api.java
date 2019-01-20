package com.e.vasialeleka.heroretofitapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    String BaseUrl = "https://simplifiedcoding.net/demos/";
    @GET("marvel")
    Call<List<Hero>> getHeroes();
}
