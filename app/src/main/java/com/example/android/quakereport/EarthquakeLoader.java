package com.example.android.quakereport;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by timad on 08/12/2016.
 */

public class EarthquakeLoader extends AsyncTaskLoader<ArrayList<Earthquake>> {

    String mUrl;

    public EarthquakeLoader(Context context, String url){
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        Log.i(EarthquakeLoader.class.getSimpleName(), "onStartLoadingCalled()");
        forceLoad();
    }

    @Override
    public ArrayList<Earthquake> loadInBackground() {

        Log.i(EarthquakeLoader.class.getSimpleName(), "loadInBackground()");

        if(mUrl == null) {
            return null;
        }

          ArrayList<Earthquake> earthquakes = QueryUtils.extractEarthquakes(mUrl);
        return earthquakes;
    }
}
