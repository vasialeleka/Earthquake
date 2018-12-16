package com.e.vasialeleka.navbar.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.e.vasialeleka.navbar.fragments.fragmentOne.BlankFragmentOne
import com.e.vasialeleka.navbar.fragments.fragmentOne.BlankFragmentTwo

class MyFragmentAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {
    override fun getCount(): Int {
        return 2;
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0->"first"
            else->"second"
        }
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> BlankFragmentOne()

            else -> BlankFragmentTwo()

        }
    }
}