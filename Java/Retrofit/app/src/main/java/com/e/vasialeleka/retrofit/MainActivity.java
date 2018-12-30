package com.e.vasialeleka.retrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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

    IMyAPI myAPI;
    RecyclerView recyclerView;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = RetrofitClient.getIntence();
        myAPI = retrofit.create(IMyAPI.class);

recyclerView = findViewById(R.id.recyclerView);
recyclerView.setHasFixedSize(true);
recyclerView.setLayoutManager(new LinearLayoutManager(this));
fetchData();


    }

    private void fetchData() {
        compositeDisposable.add(myAPI.getPosts()
                .subscribeOn(Schedulers.io())
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
        PostAdapter adapter = new PostAdapter(this,rootObjects);
        recyclerView.setAdapter(adapter);

    }

    @Override
    protected void onStop() {
        super.onStop();
        compositeDisposable.clear();
    }
}
