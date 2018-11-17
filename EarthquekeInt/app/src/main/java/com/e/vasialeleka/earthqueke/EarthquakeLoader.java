package com.e.vasialeleka.earthqueke;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import org.json.JSONException;

import java.util.ArrayList;

public class  EarthquakeLoader extends AsyncTaskLoader<ArrayList<Eearthquake>> {
    /** Tag for log messages */
    private static final String LOG_TAG = EarthquakeLoader.class.getName();
    /** Query URL */
    private String mUrl;
    /**
     * Constructs a new {@link EarthquakeLoader}.
     *
     * @param context of the activity
     * @param url to load data from
     */
    public EarthquakeLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }
    @Override
    protected void onStartLoading() {
        forceLoad();}
    /**
     * This is on a background thread.
     */
    @Override
    public ArrayList<Eearthquake> loadInBackground() {
        if (mUrl == null) {
            return null;
        }
        // Perform the network request, parse the response, and extract a list of earthquakes.
        ArrayList<Eearthquake> earthquakes = null;

        try {
            earthquakes = QueryUtils.fetchEarthQuakeData(mUrl);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return earthquakes;
    }
}