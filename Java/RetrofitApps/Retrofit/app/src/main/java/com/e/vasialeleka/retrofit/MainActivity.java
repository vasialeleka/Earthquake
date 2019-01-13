package com.e.vasialeleka.retrofit;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.e.vasialeleka.retrofit.Adapter.PostAdapter;
import com.e.vasialeleka.retrofit.Posts.RootObject;
import com.e.vasialeleka.retrofit.Retrofit.IMyAPI;
import com.e.vasialeleka.retrofit.Retrofit.RetrofitClient;

import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
int i =0;
    IMyAPI myAPI;
    RecyclerView recyclerView;
    SwipeRefreshLayout refreshLayout;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = RetrofitClient.getIntence();
        myAPI = retrofit.create(IMyAPI.class);

        recyclerView = findViewById(R.id.recyclerView);
        refreshLayout = findViewById(R.id.refresh);
        recyclerView.setHasFixedSize(true);
        fetchData();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                //Toast.makeText(MainActivity.this, "swipe", Toast.LENGTH_SHORT).show();
                fetchData();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.setRefreshing(false);
                    }
                }, 4000);

            }
        });


    }

    private void fetchData() {
        i++;
        Log.e("refresh",""+i);
        compositeDisposable.add(myAPI.getPosts()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<RootObject>>() {
                    @Override
                    public void accept(List<RootObject> rootObjects) throws Exception {
                        displayData(rootObjects);
                    }
                })

        );
    }

    private void displayData(List<RootObject> rootObjects) {
        PostAdapter adapter = new PostAdapter(this, rootObjects);
        recyclerView.setAdapter(adapter);

    }

    @Override
    protected void onStop() {
        super.onStop();
        compositeDisposable.clear();
    }
}
