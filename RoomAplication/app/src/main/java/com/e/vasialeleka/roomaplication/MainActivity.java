package com.e.vasialeleka.roomaplication;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
FloatingActionButton button;
RecyclerView recyclerView;
RecyclerView.Adapter adapter;
ArrayList<String> users;
   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.fab);
 users = new ArrayList<>();
 for ( int i=0;i<10;i++){
     users.add("Vasia"+i +"erhha");
 }
        button.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Button",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this,CreateUser.class);
                startActivity(intent);
            }
        });
        recyclerView = findViewById(R.id.list);
       recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new UserAdapter(users);
         recyclerView.setAdapter(adapter);
    }
}
