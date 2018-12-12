package com.e.vasialeleka.smarthome.FetchData;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.widget.TextView;

import com.e.vasialeleka.smarthome.Activity.TabViewActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class FetchDataLogin extends AsyncTask<Void,Void,String> {
    private String urls;
    String data="";
    TextView text;
    Context context;
    String noConnect = " ";
    String falseLogin = "false_login";
    String falsePassword = "false_pass";

    public FetchDataLogin(String urlss, TextView text, Context context) {

        this.urls = urlss;
        this.text = text;
        this.context = context;
    }

    @Override
    protected String doInBackground(Void... voids) {
        try {
            URL url = new URL(urls);
            try {
                HttpURLConnection httpURLConnection =(HttpURLConnection) url.openConnection();
                httpURLConnection.connect();
                InputStream inputStream =httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line = "";
                while (line!=null){
                    line = bufferedReader.readLine();
                    if (line!=null) data = data+line;
                }
             //   data.toString();
            } catch (IOException e) {
                return noConnect ;
            }

        } catch (MalformedURLException e) {
            return noConnect ;
        }



        return data;
    }

    @Override
    protected void onPostExecute(String data) {
        if(data.equals(noConnect))
        {
            text.setText("No connection!");
            text.setTextColor(Color.RED);
        } else if (data.equals(falseLogin)){
            text.setText("False Login!");
            text.setTextColor(Color.RED);
        }else if (data.equals(falsePassword)){
            text.setText("False password!");
            text.setTextColor(Color.RED);
        }else {
            Intent intent = new Intent(context, TabViewActivity.class);
            intent.putExtra("KEY", data);
            context.startActivity(intent);
            ((Activity) context).finish();
            super.onPostExecute(data);
        }

    }

    @Override
    protected void onPreExecute() {
        text.setText("Please ,wait!:)");
        text.setTextColor(Color.WHITE);
        super.onPreExecute();
    }
}