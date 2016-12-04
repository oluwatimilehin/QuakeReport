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

    public String getUrl() {
        return mUrl;
    }

    private String mUrl;

    public Long getTime() {
        return mTime;
    }

    private Long mTime;

    public Earthquake(Double magnitude, String location, Long time, String url){

        mMagnitude = magnitude;
        mLocation = location;
        mTime = time;
        mUrl = url;
    }

}
