package com.e.vasialeleka.earthqueke;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

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
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * Helper methods related to requesting and receiving earthquake data from USGS.
 */
public final class QueryUtils {

    String data ="";
    public static final String LOG_TAG = QueryUtils.class.getSimpleName();

    /**
     * Sample JSON response for a USGS query
     *
     */
    public static  ArrayList<Eearthquake> fetchEarthQuakeData(String urlf) throws JSONException {
        URL url = createUrl(urlf);
        String json = null;
        try{
            json = makeHttpRequest(url);
        }catch (IOException e ){
            Log.e(LOG_TAG, "Error closing input stream", e);
        }
        JSONArray features = getJsonArray(json);
      ArrayList<Eearthquake> Eearthquake = new ArrayList<>();//eearthquake = extractEarthquakes(features);
      for (int i= 0 ;i<features.length();i++){ Eearthquake .add(extractEarthquakes(features,i));}
        return  Eearthquake;
    }

    private static String makeHttpRequest(URL url )throws IOException {



        String json = "";
        if (url == null){return json;}
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection =(HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(1000000 /* milliseconds */);
            urlConnection.setConnectTimeout(1500000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                json = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }

        }catch (IOException a ){
            Log.e(LOG_TAG, "Problem retrieving the earthquake JSON results.", a);
        }
        finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return json;

    }
    private static String readFromStream(InputStream inputStream) throws IOException {

        StringBuilder output = new StringBuilder();
        if (inputStream !=null){
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }


    private static URL createUrl(String urlf) {
        URL url = null;
        try{
            url = new URL(urlf);
        }catch (MalformedURLException e){
            Log.e(LOG_TAG, "Error with creating URL ", e);
        }
        return url;
    }



    public static Eearthquake extractEarthquakes(JSONArray features,int i) {

     /*   if (TextUtils.isEmpty(data)) {
            return null;
      /*  }*/



        try {


             //for (int i = 1; i < features.length(); i++) {
                JSONObject current = features.getJSONObject(i);
                JSONObject prop = current.getJSONObject("properties");
                double mag = prop.getDouble("mag");
                String place = prop.getString("place");
                long date = prop.getLong("time");
                String url = prop.getString("url");


                return new Eearthquake(mag,place,date,url);
          //  }


        } catch (JSONException e) {

            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }

        // Return the list of earthquakes
        return null;
    }

    @NonNull
    private static JSONArray getJsonArray(String data) throws JSONException {
        JSONObject root = new JSONObject(data);
        JSONArray features = root.getJSONArray("features");
        int b= features.length();
        return features;
    }

}