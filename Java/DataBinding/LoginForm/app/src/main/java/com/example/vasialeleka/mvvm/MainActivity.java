package com.example.vasialeleka.mvvm;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.vasialeleka.mvvm.commands.UserLogin;
import com.example.vasialeleka.mvvm.databinding.ActivityMainBinding;
import com.example.vasialeleka.mvvm.model.User;
import com.example.vasialeleka.mvvm.viewmodel.UserModel;

public class MainActivity extends AppCompatActivity {
private ActivityMainBinding activityMainBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        UserModel userModel = new UserModel(new User("Email", "Password"));
        activityMainBinding.setLogin(userModel);

        UserLogin userL = new UserLogin() {
            @Override
            public void onClickLogin() {
                Toast.makeText(getApplicationContext(), "" + activityMainBinding.getLogin().getEmail(), Toast.LENGTH_SHORT).show();


            }
        };
        activityMainBinding.setUserLoginEvent(userL);

    }}

