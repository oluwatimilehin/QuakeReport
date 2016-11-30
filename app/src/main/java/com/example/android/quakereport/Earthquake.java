package com.example.android.quakereport;

/**
 * Created by timad on 30/11/2016.
 */

public class Earthquake {

    public Double getMagnitude() {
        return mMagnitude;
    }

    public String getLocation() {
        return mLocation;
    }

    public String getDate() {
        return mDate;
    }

    private Double mMagnitude;
    private String mLocation, mDate;

    public Earthquake(Double magnitude, String location, String date){

        mMagnitude = magnitude;
        mLocation = location;
        mDate = date;
    }

}
