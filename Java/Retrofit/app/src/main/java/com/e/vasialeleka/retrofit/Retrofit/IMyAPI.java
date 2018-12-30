package com.e.vasialeleka.retrofit.Retrofit;



import com.e.vasialeleka.retrofit.Posts.RootObject;

import java.util.List;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface IMyAPI {
    @GET("posts")
    Observable<List<RootObject>> getPosts();
}
