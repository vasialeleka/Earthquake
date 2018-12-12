package com.e.vasialeleka.roomaplication;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateUser extends AppCompatActivity {
EditText name ;
EditText second;
EditText email;
Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);
        name = findViewById(R.id.name);
        second = findViewById(R.id.second);
        email = findViewById(R.id.email);
        button = findViewById(R.id.button);
        final AppDatabase db = Room.databaseBuilder(
                getApplicationContext(),AppDatabase.class,"production"
        ).allowMainThreadQueries().build();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              db.userDao().insertAll(new User(name.getText().toString(),second.getText().toString(),email.getText().toString()));
            }
        });
    }
    @Override
    public  boolean onCreateOptionsMenu (Menu menu){
        MenuInflater inflator = getMenuInflater();
        inflator.inflate(R.menu.save,menu);

        return true;

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
       if( item.getItemId() == R.id.save){
           Toast.makeText(getApplicationContext(),name.getText().toString(),Toast.LENGTH_SHORT).show();
       startActivity(new Intent( CreateUser.this,MainActivity.class));
       }
        return super.onOptionsItemSelected(item);
    }
}
