package com.example.vasialeleka.news.activities;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.vasialeleka.news.R;

public class NewsActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drewer;
    public static final String API_KEY = "e60697f7f9874f35a82708c0df1618a9";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drewer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this
                , drewer
                , toolbar
                , R.string.navigation_drawer_open
                , R.string.navigation_drawer_close);
        drewer.addDrawerListener(toggle);
        toggle.syncState();

    }

    @Override
    public void onBackPressed() {
        if (drewer.isDrawerOpen(GravityCompat.START)) {
            drewer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.top : makeRequest("top");break;
            case R.id.general: makeRequest("general");break;
            case R.id.business: makeRequest("business");break;
            case R.id.entertaiment: makeRequest("entertaiment"); break;
            case R.id.helth : makeRequest("health");break;
            case R.id.sport:makeRequest("sports");break;
            case R.id.science :makeRequest("science");break;
            case R.id.technology : makeRequest("technology");break;
            case R.id.openSite: break;//TODO intent
        }
        //drewer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void makeRequest(String r) {
        if(r.equals("top")){
        Toast.makeText(getApplicationContext(),"1"+r,Toast.LENGTH_SHORT).show();}else{
            Toast.makeText(getApplicationContext(),"2"+r,Toast.LENGTH_SHORT).show();
        }
    }
}
