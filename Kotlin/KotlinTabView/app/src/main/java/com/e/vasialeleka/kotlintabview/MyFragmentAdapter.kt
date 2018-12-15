package com.e.vasialeleka.kotlintabview

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import java.text.FieldPosition

class MyFragmentAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> FirstFragmnet()
            1 -> SecondFragment()
            else -> ThirdFragment()

        }
    }

    override fun getCount(): Int {
        return 3;
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> "FirstFragmnet"
            1 -> "SecondFragment"
            else -> "ThirdFragment"
        }
    }
}