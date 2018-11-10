package com.e.vasialeleka.earthqueke;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Internet  extends AsyncTask<Void,Void,Void> {
    String data ="";
    TextView dat;
    String dataBase = "";

    public String getData(){

        return data;
    }

    @Override

    protected Void doInBackground(Void... voids) {
        try {

            URL url = new URL("https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&minmagnitude=4");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            String line = " ";
            while (line != null) {
                line = bufferedReader.readLine();
                data = data + line;
            }
            data.toString();



        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

    }
}