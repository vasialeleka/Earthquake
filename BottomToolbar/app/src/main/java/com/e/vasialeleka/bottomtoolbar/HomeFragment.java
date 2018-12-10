package com.e.vasialeleka.bottomtoolbar;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.e.vasialeleka.bottomtoolbar.Living.FragmentAdapter;
import com.e.vasialeleka.bottomtoolbar.Living.LighrFragment;
import com.e.vasialeleka.bottomtoolbar.Living.Socket;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private FragmentAdapter adapter;
    public View v ;
    private Context myContext;

    @Nullable

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_home,container,false) ;
        tabLayout = v.findViewById(R.id.tabLayout);
        viewPager = v.findViewById(R.id.viewPager);

        List<Fragment> list = new ArrayList<>();
        list.add(new Socket());
        list.add(new LighrFragment());
        FragmentManager fragManager = getActivity().getSupportFragmentManager();

          adapter = new FragmentAdapter(fragManager,list);
       viewPager.setAdapter(adapter);
       tabLayout.setupWithViewPager(viewPager);
        return v ;
    }


/*  @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }*/
}
