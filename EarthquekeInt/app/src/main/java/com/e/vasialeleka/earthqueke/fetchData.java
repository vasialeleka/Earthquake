package com.e.vasialeleka.earthqueke;

import android.os.AsyncTask;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

import static com.e.vasialeleka.earthqueke.QueryUtils.extractEarthquakes;

/*public class fetchData  {
    String data = "";
    TextView dat;
    String dataBase = "";
    String magn;
    TextView mag;

    public fetchData(TextView text) {
        this.dat = text;

    }







    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);


    }
    private String makeHttpRequest(URL url) throws IOException {
        String line = "";
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000 /* milliseconds );
            urlConnection.setConnectTimeout(15000 /* milliseconds );
            urlConnection.connect();
            inputStream = urlConnection.getInputStream();
            line = readFromStream(inputStream);
        } catch (IOException e) {
            // TODO: Handle the exception
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                // function must handle java.io.IOException here
                inputStream.close();
            }
        }
        return line;
    }
    private String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }*/
