package com.e.vasialeleka.recviewinfragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebViewFragment;

public class MainActivity extends AppCompatActivity {
private TabLayout tabLayout;
private ViewPager viewPager;
private ViewPagerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabLayout = findViewById(R.id.tablayout);
        viewPager =  findViewById(R.id.viewpager);
adapter = new ViewPagerAdapter(getSupportFragmentManager());

adapter.AddFRagment(new FragmentCall(),"Call");
        adapter.AddFRagment(new FragmentContact(),"Contact");
        adapter.AddFRagment(new FragmentFav(),"Favorites");
viewPager.setAdapter(adapter);
tabLayout.setupWithViewPager(viewPager);
tabLayout.getTabAt(0).setIcon(R.drawable.ic_call);
tabLayout.getTabAt(1).setIcon(R.drawable.ic_group);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_star_);


    }
}
