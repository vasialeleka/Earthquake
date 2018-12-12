package com.e.vasialeleka.smarthome.Rooms;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;


import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.e.vasialeleka.smarthome.R;


public class KitchenFragment extends Fragment {
  View v;
  RecyclerView recyclerViewKitchen;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_kitchen, container, false);
        recyclerViewKitchen = v.findViewById(R.id.socketKitchen);
    return  v;
    }

    public RecyclerView getKitchenRecyclerView() {
        return recyclerViewKitchen;
    }
}
