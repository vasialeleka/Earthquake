package com.e.vasialeleka.jsom;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
String name;
String twiter;
String countrry;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        name = "Vasia";
        countrry="Україна";
        twiter = "No twitter";
        setContentView(R.layout.activity_main);
        List<Socket> list = new ArrayList<>();
        for (int i = 0; i<3;i++){
            list.add(new Socket(""+i,"Enable","Socket"));
        }
       /// General general = new General(list);
        Gson json = new Gson();
String js = json.toJson(list);
        TextView textView = findViewById(R.id.id);
        textView.setText(js);
String i ;
HTTPAsyncTask httpAsyncTask = new HTTPAsyncTask("");
httpAsyncTask.execute("http://hmkcode.appspot.com/jsonservlet");

    }
}
class HTTPAsyncTask extends AsyncTask<String,Void,String>{
    String urls;

    public HTTPAsyncTask(String urls) {
        this.urls = urls;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }

    @Override
    protected String doInBackground(String... urls) {

        {
            // params comes from the execute() call: params[0] is the url.
            try {
                try {
                    return HttpPost(urls[0]);
                } catch (JSONException e) {
                    e.printStackTrace();
                    return "Error!";
                }
            } catch (IOException e) {
                return "Unable to retrieve web page. URL may be invalid.";
            }
        }

    }

    private String HttpPost(String Url) throws IOException, JSONException {
        String result = "";

        URL url = new URL(Url);

        // 1. create HttpURLConnection
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");

        // 2. build JSON object
        JSONObject jsonObject = buidJsonObject();

        // 3. add JSON content to POST request body
        setPostRequestContent(conn, jsonObject);

        // 4. make POST request to the given URL
        conn.connect();

        // 5. return response message
        return conn.getResponseMessage()+"";
    }

    private void setPostRequestContent(HttpURLConnection conn, JSONObject jsonObject) throws IOException{

        OutputStream os = conn.getOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
        writer.write(jsonObject.toString());
        Log.i(MainActivity.class.toString(), jsonObject.toString());
        writer.flush();
        writer.close();
        os.close();

    }

    private JSONObject buidJsonObject() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.accumulate("name","Vasia");
        jsonObject.accumulate("country",  "Ukraine");
        jsonObject.accumulate("twitter",  "No twitter");
        

        return jsonObject;
    }
}
