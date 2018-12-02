package com.e.vasialeleka.recviewinfragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentCall extends Fragment {
    View v;
    public FragmentCall() {
    }


    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
      v = inflater.inflate(R.layout.call_fragment,container,false);
      return v ;
    }
}
