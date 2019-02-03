package com.example.vasialeleka.news.adapters;


import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.example.vasialeleka.news.R;
import com.example.vasialeleka.news.Utils;
import com.example.vasialeleka.news.models.Article;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder> {
    private List<Article> articles;
    private Context context;
    private OnItemClickListener mListener;

    public void setOnClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public NewsAdapter(List<Article> articles, Context context) {
        this.articles = articles;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.a, viewGroup, false);
        return new MyViewHolder(v);
    }

    @SuppressLint({"CheckResult", "SetTextI18n"})
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder viewHolder, int i) {
        Article article = articles.get(i);
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(Utils.getRandomDrawbleColor());
        requestOptions.error(Utils.getRandomDrawbleColor());
        requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);

        Glide.with(context).load(article.getUrlToImage()).apply(requestOptions)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(viewHolder.img);

        viewHolder.title.setText(article.getTitle());
        viewHolder.desc.setText(article.getDescription());
        viewHolder.source.setText(article.getSource().getName());
        viewHolder.data.setText("\u2022" + Utils.DateToTimeFormat(article.getPublishedAt()));
        if (article.getAuthor() != null) {
            viewHolder.author.setText(article.getAuthor());
        } else {
            viewHolder.author.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView author, title, data, source, desc;
        ImageView img;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mListener.onItemClick(position);
                        }
                    }
                }
            });

            author = itemView.findViewById(R.id.author);
            title = itemView.findViewById(R.id.title);
            data = itemView.findViewById(R.id.date);
            source = itemView.findViewById(R.id.source);
            desc = itemView.findViewById(R.id.desc);
            img = itemView.findViewById(R.id.img);

        }


    }

}

