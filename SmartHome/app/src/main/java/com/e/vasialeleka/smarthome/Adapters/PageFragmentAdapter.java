package com.e.vasialeleka.smarthome.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;


public class PageFragmentAdapter extends FragmentPagerAdapter {

    private List<Fragment> content;

    public PageFragmentAdapter(FragmentManager fm, List<Fragment> content) {
        super(fm);
        this.content = content;
    }

    @Override
    public Fragment getItem(int i) {
        return content.get(i);
    }

    @Override
    public int getCount() {
        return content.size();
    }


}
