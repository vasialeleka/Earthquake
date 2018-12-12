package com.e.vasialeleka.smarthome.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.e.vasialeleka.smarthome.FetchData.FetchDataLogin;
import com.e.vasialeleka.smarthome.R;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class StartActivity extends AppCompatActivity {
    private Button start;
    private EditText login;
    private EditText password;
    public static TextView connect;
    public static String answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        login();
    }

    private void login() {
        start = findViewById(R.id.start);
        login = findViewById(R.id.login);
        password = findViewById(R.id.password);
        connect = findViewById(R.id.connect);


        Button check = findViewById(R.id.check);
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TabViewActivity.class);
                intent.putExtra("KEY", "123456789");
                startActivity(intent);
            }
        });


        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (login.getText().length() == 0 || password.getText().length() == 0) {

                    Toast.makeText(getApplicationContext(), "Please enter your data.", Toast.LENGTH_SHORT).show();
                } else {

                    try {

                        request(login.getText().toString(), password.getText().toString());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (TimeoutException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void request(String login, String passward) throws InterruptedException, ExecutionException, TimeoutException {
        String url = "http://smartdevgroup.hopto.org/service/mobile.php?login=" + login + "&pass=" + passward;
        FetchDataLogin login1 = new FetchDataLogin(url, connect, this);//,this);
        login1.execute();
    /*    Data myData = new Data.Builder()
                .putString("URL",url)


                .build();
        Constraints constraints = new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED).build();
        OneTimeWorkRequest oneTimeWorkRequest = new OneTimeWorkRequest.Builder(getLoginData.class)
            //   .setConstraints(constraints)
                .setInputData(myData)
        .build();
        WorkManager.getInstance().enqueue(oneTimeWorkRequest);*/


    }
}