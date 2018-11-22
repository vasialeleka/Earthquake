package com.e.vasialeleka.earthqueke;

import android.app.LoaderManager;



import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.content.Loader;
android.support.v4.app.LoaderManager
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.content.Context;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  implements LoaderCallbacks<ArrayList<Eearthquake>> {
    private static final String url = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson";
    private static final int EARTHQUAKE_LOADER_ID = 1;
    ListView list;

    private EarthquakeAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        android.app.LoaderManager loaderManager = getLoaderManager();
        // Initialize the loader. Pass in the int ID constant defined above and pass in null for
        // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
        // because this activity implements the LoaderCallbacks interface).
        loaderManager.initLoader(1, null, this);

        //    EartQuake task = new EartQuake();
        //     task.execute(url);
    }
    @Override
    public Loader<ArrayList<Eearthquake>> onCreateLoader(int i, Bundle bundle) {


        return new EarthquakeLoader(this, url);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<Eearthquake>> loader, ArrayList<Eearthquake> earthquakes) {
        mAdapter.clear();
        // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (earthquakes != null && !earthquakes.isEmpty()) {
            mAdapter.addAll(earthquakes);
        }
        Update(earthquakes);
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<Eearthquake>> loader) {
        // TODO: Loader reset, so we can clear out our existing data.
    }
    private void Update(ArrayList<Eearthquake> earthqueke) {
        list = (ListView) findViewById(R.id.list);
        EarthquakeAdapter adapter = new EarthquakeAdapter(this, earthqueke);
        list.setAdapter(adapter);
    }

   /* public class EartQuake extends AsyncTask<String,Void,ArrayList<Eearthquake> >{
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
    }*/
}
