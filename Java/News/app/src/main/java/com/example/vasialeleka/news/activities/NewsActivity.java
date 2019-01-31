package com.example.vasialeleka.news.activities;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.Toast;

import com.example.vasialeleka.news.Utils;
import com.example.vasialeleka.news.adapters.NewsAdapter;
import com.example.vasialeleka.news.api.API;
import com.example.vasialeleka.news.R;
import com.example.vasialeleka.news.api.ApiClient;
import com.example.vasialeleka.news.models.Article;
import com.example.vasialeleka.news.models.News;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drewer;
    private String openSiteUrl = "https://www.bbc.com/news/world";
    private List<Article> articles = new ArrayList<>();
    public static final String API_KEY = "e60697f7f9874f35a82708c0df1618a9";
    private NewsAdapter adapter;
    private String topic;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        //swipeRefreshLayout = findViewById(R.id.swipe_refresh);
    /*    swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                if (topic != null) {
                    makeRequest("top");
                } else {
                    makeRequest(topic);
                }
            }


        });*/

        //onLoadingSwipe("top");
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drewer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        recyclerView = findViewById(R.id.news_conteiner);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this
                , drewer
                , toolbar
                , R.string.navigation_drawer_open
                , R.string.navigation_drawer_close);
        drewer.addDrawerListener(toggle);
        toggle.syncState();

    }

   /* private void onLoadingSwipe(final String cat) {
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                makeRequest(cat);
            }
        });
    }*/

    @Override
    public void onBackPressed() {
        if (drewer.isDrawerOpen(GravityCompat.START)) {
            drewer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        makeRequest("top");
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()) {
            case R.id.top:
                makeRequest("top");
                break;

            case R.id.business:
                makeRequest("business");
                break;
            case R.id.entertaiment:
                makeRequest("entertaiment");
                break;
            case R.id.helth:
                makeRequest("health");
                break;
            case R.id.sport:
                makeRequest("sports");
                break;
            case R.id.science:
                makeRequest("science");
                break;
            case R.id.technology:
                makeRequest("technology");
                break;
            case R.id.openSite:
                openSite();
                break;//TODO intent
        }
        //drewer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void openSite() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(openSiteUrl));
        startActivity(intent);
    }

    private void makeRequest(String r) {
      //  Toast.makeText(NewsActivity.this, "Refresh",Toast.LENGTH_SHORT).show();
        topic = r;
        API api = ApiClient.getApiClient().create(API.class);
        String country = Utils.getCountry();
        Call<News> call;
        if (r.equals("top")) {
            call = api.getNews(country, API_KEY);

        } else {

            call = api.getCategoryNews(country, API_KEY, r);

        }
        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                articles = response.body().getArticle();
                adapter = new NewsAdapter(articles, NewsActivity.this);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
               // swipeRefreshLayout.setRefreshing(false);

            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {
                Toast.makeText(NewsActivity.this, "No results", Toast.LENGTH_SHORT).show();
               // swipeRefreshLayout.setRefreshing(false);

            }
        });
    }
}
