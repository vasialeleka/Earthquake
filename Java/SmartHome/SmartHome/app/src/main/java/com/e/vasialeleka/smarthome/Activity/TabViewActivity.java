package com.e.vasialeleka.smarthome.Activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;


import com.e.vasialeleka.smarthome.FetchData.FetchDataForRooms;
import com.e.vasialeleka.smarthome.R;
import com.e.vasialeleka.smarthome.Rooms.BedroomFragment;
import com.e.vasialeleka.smarthome.Rooms.HallFragment;
import com.e.vasialeleka.smarthome.Adapters.PageFragmentAdapter;
import com.e.vasialeleka.smarthome.Rooms.KitchenFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class TabViewActivity extends AppCompatActivity {
    String url = "http://smartdevgroup.hopto.org/service/json_mob.php?api=";
    final int[] ICONS = new int[]{R.drawable.living, R.drawable.bed, R.drawable.gas, R.drawable.bath};

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private PageFragmentAdapter adapter;
    List<Fragment> list;
    String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_view);
         key = getIntent().getStringExtra("KEY");

        url = url + key;
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        viewPager.setOffscreenPageLimit(3);
        list = new ArrayList<>();
        list.add(new HallFragment());
        list.add(new BedroomFragment());
        list.add(new KitchenFragment());
        adapter = new PageFragmentAdapter(getSupportFragmentManager(), list);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(ICONS[0]);
        tabLayout.getTabAt(1).setIcon(ICONS[1]);
        tabLayout.getTabAt(2).setIcon(ICONS[2]);

        Timer timer = new Timer();
        timer.schedule(new FetchTimer(), 0, 2000);


    }

    private void asyncData(List<Fragment> list) {
        FetchDataForRooms data = new FetchDataForRooms(list, url,key);
        data.execute();
    }

    class FetchTimer extends TimerTask {
        int i = 0;

        @Override
        public void run() {
            i++;
            Log.e("2 sec", "" + i);
            asyncData(list);

        }
    }
}
