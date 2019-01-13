package com.e.vasialeleka.retrofit.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.e.vasialeleka.retrofit.Posts.RootObject;
import com.e.vasialeleka.retrofit.R;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostViewHolder> {
    Context context;
    List<RootObject> rootObjects;

    public PostAdapter(Context context, List<RootObject> rootObjects) {
        this.context = context;
        this.rootObjects = rootObjects;
    }


    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.post_layout, viewGroup, false);


        return new PostViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder postViewHolder, int i) {
        postViewHolder.txt_title.setText(String.valueOf(rootObjects.get(i).getTitle()));
        postViewHolder.txt_content.setText(new StringBuilder(rootObjects.get(i).getBody().
                substring(0,20)).append("...").toString());

        postViewHolder.txt_author.setText(String.valueOf(rootObjects.get(i).getUserId()));

    }

    @Override
    public int getItemCount() {
        return rootObjects.size();
    }
}
