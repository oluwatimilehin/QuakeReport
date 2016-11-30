package com.example.android.quakereport;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by timad on 30/11/2016.
 */

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

   public EarthquakeAdapter(Activity context, ArrayList<Earthquake> earthquakes){

       super(context, 0, earthquakes);
   }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Earthquake currentEarthquake = getItem(position);

        View listItemView = convertView;

        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false
            );
        }

        TextView magnitudeTextView = (TextView) listItemView.findViewById(R.id.magnitude);
        magnitudeTextView.setText(String.valueOf(currentEarthquake.getMagnitude()));

        TextView locationTextView = (TextView) listItemView.findViewById(R.id.location);
        locationTextView.setText(currentEarthquake.getLocation());

        TextView dateTextView = (TextView) listItemView.findViewById(R.id.date);
        dateTextView.setText(currentEarthquake.getDate());

        return listItemView;
    }
}
