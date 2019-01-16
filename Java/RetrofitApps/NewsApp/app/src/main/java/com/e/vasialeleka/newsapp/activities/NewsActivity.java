package com.e.vasialeleka.newsapp.activities;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.support.v7.widget.SearchView;
import android.app.SearchManager;

import com.e.vasialeleka.newsapp.Adapter;
import com.e.vasialeleka.newsapp.R;
import com.e.vasialeleka.newsapp.Utils;
import com.e.vasialeleka.newsapp.api.ApiClient;
import com.e.vasialeleka.newsapp.api.ApiInterface;
import com.e.vasialeleka.newsapp.models.Article;
import com.e.vasialeleka.newsapp.models.News;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;

public class NewsActivity extends AppCompatActivity {
    public static final String API_KEY = "e60697f7f9874f35a82708c0df1618a9";
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<Article> articles = new ArrayList<>();
    private Adapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        swipeRefreshLayout = findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadJson("");
                Toast.makeText(NewsActivity.this, "Refresh", Toast.LENGTH_SHORT).show();
            }
        });

        recyclerView = findViewById(R.id.recyclrView);
        layoutManager = new LinearLayoutManager(NewsActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);

        onLoadingSwipeRefresh("");

    }

    public void loadJson(final String keyword) {
swipeRefreshLayout.setRefreshing(true);
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        //Call<News> call;
        String country = Utils.getCountry();
        retrofit2.Call<News> call;

        if (keyword.length() > 0) {
            call = apiInterface.getNewsSearch(keyword, "publishedAt", API_KEY);
        } else {
            call = apiInterface.getNews(country, API_KEY);
        }
        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(retrofit2.Call<News> call, Response<News> response) {
                if (response.isSuccessful() && response.body().getArticle() != null) {
                    if (!articles.isEmpty()) {
                        articles.clear();
                    }
                    articles = response.body().getArticle();
                    adapter = new Adapter(articles, NewsActivity.this);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    adapter.setOnItemClickListener(new Adapter.OnItemClickListener() {
                        @SuppressLint("LongLogTag")
                        @Override
                        public void onItemClick(View view, int position) {
                            Intent intent = new Intent(NewsActivity.this,NewsDetailActivity.class);
                            Article article = articles.get(position);
                            Log.e("_______________________________________________________________","Click");
                            intent.putExtra("URL",article.getUrl());
                            intent.putExtra("TITLE",article.getTitle());
                            intent.putExtra("IMG",article.getUrlToImage());
                            intent.putExtra("DATA",article.getPublishedAt());
                            intent.putExtra("SOURCE",article.getSource().getName());
                            intent.putExtra("AUTHOR",article.getAuthor());
                            startActivity(intent);


                        }
                    });
                    swipeRefreshLayout.setRefreshing(false);

                } else {
                    swipeRefreshLayout.setRefreshing(false);

                    Toast.makeText(NewsActivity.this, "No results", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(retrofit2.Call<News> call, Throwable t) {
                swipeRefreshLayout.setRefreshing(false);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        final SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        MenuItem seachMenuItems = menu.findItem(R.id.action_search);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setQueryHint("Search News...");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (query.length() > 2) {
                    onLoadingSwipeRefresh(query);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                onLoadingSwipeRefresh(newText);
                return false;
            }
        });
        seachMenuItems.getIcon().setVisible(false, false);
        return true;
        //return super.onCreateOptionsMenu(menu);
    }

    private void onLoadingSwipeRefresh(final String keyword) {
        swipeRefreshLayout.post(
                new Runnable() {
                    @Override
                    public void run() {
                        loadJson(keyword);
                    }
                }
        );
    }
}
