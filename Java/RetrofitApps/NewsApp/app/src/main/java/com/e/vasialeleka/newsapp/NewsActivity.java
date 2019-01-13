package com.e.vasialeleka.newsapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telecom.Call;
import android.widget.Toast;

import com.e.vasialeleka.newsapp.api.ApiClient;
import com.e.vasialeleka.newsapp.api.ApiInterface;
import com.e.vasialeleka.newsapp.models.Article;
import com.e.vasialeleka.newsapp.models.News;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;

public class NewsActivity extends AppCompatActivity {
public static  final String API_KEY="e60697f7f9874f35a82708c0df1618a9";
private RecyclerView recyclerView;
private  RecyclerView.LayoutManager layoutManager;
private List<Article> articles = new ArrayList<>();
private  Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        recyclerView = findViewById(R.id.recyclrView);
        layoutManager = new LinearLayoutManager(NewsActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);
        loadJson();

    }

    public void loadJson(){

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        //Call<News> call;
        String country = Utils.getCountry();
        retrofit2.Call<News> call;
         call = apiInterface.getNews(country,API_KEY);
         call.enqueue(new Callback<News>() {
             @Override
             public void onResponse(retrofit2.Call<News> call, Response<News> response) {
                 if (response.isSuccessful()&& response.body().getArticle()!=null){
                     if(!articles.isEmpty()){articles.clear();}
                     articles= response.body().getArticle();
                     adapter = new Adapter(articles,NewsActivity.this);
                     recyclerView.setAdapter(adapter);
                     adapter.notifyDataSetChanged();

                 }else{
                     Toast.makeText(NewsActivity.this,"No results",Toast.LENGTH_SHORT).show();
                 }
             }

             @Override
             public void onFailure(retrofit2.Call<News> call, Throwable t) {

             }
         });
    }
}
