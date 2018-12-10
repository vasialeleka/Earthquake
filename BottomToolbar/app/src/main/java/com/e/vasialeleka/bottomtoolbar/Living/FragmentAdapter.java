package com.e.vasialeleka.bottomtoolbar.Living;

import android.net.LinkAddress;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class FragmentAdapter extends FragmentPagerAdapter {
private List<Fragment> fragments;
    public FragmentAdapter(FragmentManager fm,List<Fragment> fragments) {
        super(fm);
        this.fragments=fragments;
    }

    @Override
    public Fragment getItem(int i) {
        return fragments.get(i);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0: return "Socket";
            case 1: return "Light";
        }
        return super.getPageTitle(position);
    }
}
