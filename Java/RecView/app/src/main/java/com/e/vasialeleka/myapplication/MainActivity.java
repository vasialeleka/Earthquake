package com.e.vasialeleka.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<Socket>  list = new ArrayList<>();
        int count  = 30;
        for (int i =0;i<= count;i++){
            if (i<count/2){
            list.add(new Socket("Enable",""+i,"Microvawe"));}else{
                list.add(new Socket("Disable",""+i,"Microvawe"));
            }

        }

        RecyclerView rec = findViewById(R.id.list);
        rec.setHasFixedSize(true);
        rec.setLayoutManager(new LinearLayoutManager(this));
        SocketAdapter2 adapter = new SocketAdapter2(list);
        rec.setAdapter(adapter);
        WorkManager workManager = WorkManager.getInstance();
        FooWorker f = new FooWorker("sdf");
        workManager.enqueue(new OneTimeWorkRequest.Builder(FooWorker.class).build());
    }
}
