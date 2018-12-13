package com.e.vasialeleka.smarthome.SendData;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class SendToServer extends AsyncTask<Void, Void, Void> {
    //TODO insert url for sending  smartdevgroup.hopto.org/service/send_mob.php
    String urls = "http://smartdevgroup.hopto.org/service/send_mob.php";
    JSONObject json;

    public SendToServer(JSONObject jsonObject) {

        super();
        this.json = jsonObject;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URL url = new URL(urls);
            try {
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
                //TODO here  must be Json for send
                JSONObject jsonObject = null;
                setPostRequestContent(connection, json);

            } catch (IOException e) {
                Log.e("doInBac", "trobles");
            }

        } catch (MalformedURLException e) {
            Log.e("doInBac", "trobles");
        }

        return null;
    }

    private void setPostRequestContent(HttpURLConnection connection, JSONObject jsonObject) {
        try {
            OutputStream stream = connection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(stream, "UTF-8"));
            writer.write(jsonObject.toString());
            writer.flush();
            writer.close();
            stream.close();
        } catch (IOException e) {
            Log.e("doInBac", "setRequestMethod");
        }


    }
}
