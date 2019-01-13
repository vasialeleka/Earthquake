package com.e.vasialeleka.retrofit.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.e.vasialeleka.retrofit.R;

public class PostViewHolder extends RecyclerView.ViewHolder {
    TextView txt_title,txt_author,txt_content;
    public PostViewHolder(@NonNull View itemView) {
        super(itemView);
        txt_author = itemView.findViewById(R.id.txt_author);
        txt_title = itemView.findViewById(R.id.txt_title);
        txt_content = itemView.findViewById(R.id.txt_content);
    }
}
