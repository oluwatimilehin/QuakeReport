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


    private Double mMagnitude;
    private String mLocation;

    public Long getTime() {
        return mTime;
    }

    private Long mTime;

    public Earthquake(Double magnitude, String location, Long time){

        mMagnitude = magnitude;
        mLocation = location;
        mTime = time;
    }

}
