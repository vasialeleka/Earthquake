package com.e.vasialeleka.smarthome.Rooms;

import android.content.Context;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.e.vasialeleka.smarthome.R;
import com.e.vasialeleka.smarthome.Elements.Socket;

import java.util.ArrayList;
import java.util.List;


public class HallFragment extends Fragment {

View v;
private RecyclerView HallRecyclerView;
private List<Socket> socketList;

    public HallFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       /*socketList = new ArrayList<>();
        for (int i= 0 ;i<=6;i++){
            socketList.add(new Socket("Enable",""+i,"Microwave"));
        }*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v =inflater.inflate(R.layout.fragment_hall, container, false);
        HallRecyclerView = v.findViewById(R.id.socket_recyclingview);

        return v;

    }

    public  Context getCont(){ return  getContext();}
    public  RecyclerView getHallRecyclerView(){return HallRecyclerView;}



}
