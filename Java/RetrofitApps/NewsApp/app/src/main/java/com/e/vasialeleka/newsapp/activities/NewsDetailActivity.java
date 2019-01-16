package com.e.vasialeleka.newsapp.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.e.vasialeleka.newsapp.R;
import com.e.vasialeleka.newsapp.Utils;

public class NewsDetailActivity extends AppCompatActivity {
    private TextView author, data, title, text;
    private ImageView imageView;
    private String mUrl, mImg, mTitle, mDate, mAuthor, mSource;
    private WebView webView;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        author = findViewById(R.id.authorDetails);
        data = findViewById(R.id.dataDetails);
        title = findViewById(R.id.titleDetails);
        //text = findViewById(R.id.textDetails);
        imageView = findViewById(R.id.imageDetails);
        webView = findViewById(R.id.webView);
        Intent intent = getIntent();
        mUrl = intent.getStringExtra("URL");
        mImg = intent.getStringExtra("IMG");
        mAuthor = intent.getStringExtra("AUTHOR");
        mDate = intent.getStringExtra("DATE");
        mSource = intent.getStringExtra("SOURCE");
        mTitle = intent.getStringExtra("TITLE");

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.error(Utils.getRandomDrawbleColor());
        Glide.with(this)
                .load(mImg)
                .apply(requestOptions)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView);
        data.setText(mDate);
        if (mAuthor != null || mAuthor != "") {
            author.setText(mAuthor);
        } else {
            author.setVisibility(View.GONE);
        }
        title.setText(mTitle);
        if (mDate != null || mDate != "") {

    //        data.setText("\u2022" + Utils.DateFormat(mDate));
        }else {data.setVisibility(View.GONE);}
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(mUrl);
    }
}
