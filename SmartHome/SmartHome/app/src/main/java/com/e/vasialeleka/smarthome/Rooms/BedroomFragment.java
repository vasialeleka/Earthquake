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

public class BedroomFragment extends Fragment {
View v;
RecyclerView BadroomRecyclerView ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_badroom, container, false);
        BadroomRecyclerView =v.findViewById(R.id.socketBadroom_recyclingview);

        return v;
    }

    public RecyclerView getBadroomRecyclerView() {
        return BadroomRecyclerView;
    }
}
