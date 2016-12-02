package com.example.android.quakereport;

import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by timad on 30/11/2016.
 */

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    public EarthquakeAdapter(Activity context, ArrayList<Earthquake> earthquakes) {

        super(context, 0, earthquakes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false
            );
        }

        Earthquake currentEarthquake = getItem(position);

        TextView magnitudeView = (TextView) listItemView.findViewById(R.id.magnitude);
        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeView.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currentEarthquake.getMagnitude());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);

        DecimalFormat formatter = new DecimalFormat("0.0");
        String output = formatter.format(currentEarthquake.getMagnitude());
        magnitudeView.setText(output);

        String location = currentEarthquake.getLocation();
        String primary, offset;

        if (location.contains("of")) {
            String[] parts = location.split("of");
             offset = " " +parts[0] + " of";
             primary = parts[1];
        }
        else {
             offset = "Near the";
             primary = location;
        }


        TextView offsetTextView = (TextView) listItemView.findViewById(R.id.offset);
        offsetTextView.setText(offset);

        TextView primaryTextView = (TextView) listItemView.findViewById(R.id.primary);
        primaryTextView.setText(primary);

        Date date = new Date(currentEarthquake.getTime());
        TextView dateTextView = (TextView) listItemView.findViewById(R.id.date);
        dateTextView.setText(formatDate(date));

        TextView timeTextView = (TextView) listItemView.findViewById(R.id.time);
        timeTextView.setText(formatTime(date));


        return listItemView;
    }

    private String formatDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL DD, yyyy");
        String dateToDisplay = dateFormat.format(date);
        return dateToDisplay;
    }

    private String formatTime(Date date) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        String timeToDisplay = timeFormat.format(date);
        return timeToDisplay;
    }

    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }

}
