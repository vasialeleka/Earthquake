package com.e.vasialeleka.earthqueke;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.StrictMode;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
  private static final String url ="https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson";
    ListView list ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

       // StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        //StrictMode.setThreadPolicy(policy);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

EartQuake task = new EartQuake();
task.execute(url);
      /*  ArrayList<Eearthquake> earthqueke = null; //new ArrayList<Eearthquake>();
        try {
            earthqueke = QueryUtils.fetchEarthQuakeData(url);
        } catch (JSONException e) {
            e.printStackTrace();
        }*/
        // earthqueke.add(QueryUtils.fetchEarthQuakeData(url));


        //Update(earthqueke);




    }

    private void Update(ArrayList<Eearthquake> earthqueke) {
        list = (ListView) findViewById(R.id.list);
        EarthquakeAdapter adapter = new EarthquakeAdapter(this,earthqueke) ;
        list.setAdapter(adapter);
    }

    public class EartQuake extends AsyncTask<String,Void,ArrayList<Eearthquake> >{
        @Override
        protected ArrayList<Eearthquake> doInBackground(String... strings) {
            ArrayList<Eearthquake> earthqueke = null; //new ArrayList<Eearthquake>();
            try {
                earthqueke = QueryUtils.fetchEarthQuakeData(url);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return  earthqueke;
        }


        protected void onPostExecute(ArrayList<Eearthquake> eearthquakes) {
           Update( eearthquakes);
        }
    }
}
