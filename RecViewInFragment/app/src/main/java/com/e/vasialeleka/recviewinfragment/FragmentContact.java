package com.e.vasialeleka.recviewinfragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class FragmentContact extends Fragment {
    View v;
    private RecyclerView MyReccyclerView;
    private List<Contact> listContact;


    public FragmentContact() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        listContact= new ArrayList<>();
        for (int i =0;i<=6;i++){
            listContact.add(new Contact("Someone "+i, "12345678", R.drawable.ic_group));
        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.contact_fragment,container,false);
       MyReccyclerView = v.findViewById(R.id.contact_recyclingview);
       RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(getContext(),listContact);
MyReccyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
MyReccyclerView.setAdapter(recyclerViewAdapter);


        return v;
    }
}
