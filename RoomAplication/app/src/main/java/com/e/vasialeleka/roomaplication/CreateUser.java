package com.e.vasialeleka.roomaplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class CreateUser extends AppCompatActivity {
EditText name ;
EditText second;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);
        name = findViewById(R.id.name);
        second = findViewById(R.id.second);
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
       }
        return super.onOptionsItemSelected(item);
    }
}
