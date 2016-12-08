package com.example.android.quakereport;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * Helper methods related to requesting and receiving earthquake data from USGS.
 */
 public final class QueryUtils {


    /**
     * Create a private constructor because no one should ever create a {@link QueryUtils} object.
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name QueryUtils (and an object instance of QueryUtils is not needed).
     */
    private QueryUtils() {
    }

    /**
     * Return a list of {@link Earthquake} objects that has been built up from
     * parsing a JSON response.
     */
    public static   ArrayList<Earthquake> extractEarthquakes(String requiredUrl) {

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Log.i(QueryUtils.class.getSimpleName(), "QueryUtil method called");

         URL url = createUrl(requiredUrl);

        // Create an empty ArrayList that we can start adding earthquakes to
        ArrayList<Earthquake> earthquakes = new ArrayList<>();


        // Try to parse the SAMPLE_JSON_RESPONSE. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {
             JSONObject parser = new JSONObject(makeNetworkRequest(url));
             JSONArray features = parser.getJSONArray("features");
            for(int i = 0; i < features.length(); i ++){

                    JSONObject parse = features.getJSONObject(i);
                    JSONObject properties = parse.getJSONObject("properties");
                    Double mag = properties.getDouble("mag");
                    String place = properties.getString("place");
                    Long time = properties.getLong("time");
                    String earthquakeUrl = properties.getString("url");

                    earthquakes.add(new Earthquake(mag, place,time, earthquakeUrl ));

            }

            // TODO: Parse the response given by the SAMPLE_JSON_RESPONSE string and
            // build up a list of Earthquake objects with the corresponding data.

        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }

        // Return the list of earthquakes
        return earthquakes;
    }
    private static URL createUrl(String requestUrl){
        URL url = null;

        try{
            url = new URL(requestUrl);
        }
        catch (MalformedURLException e){
            Log.e(EarthquakeActivity.LOG_TAG,"Error creating url", e );
            return null;
        }
        return  url;
    }

    /**
     * Here we make the connection and get the JSON response back
     * @param url
     * @return
     */

    private static String makeNetworkRequest(URL url){

        String jsonResponse = " ";
        InputStream inputStream;
        HttpURLConnection urlConnection;


        try{
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.connect();

            if(urlConnection.getResponseCode() == 200) {

                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            }

        }
        catch (IOException e){
            Log.e(EarthquakeActivity.LOG_TAG, "Error making connection", e);
        }

         return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) throws IOException //This refers to the readLine exception
        {
            StringBuilder output = new StringBuilder();
            if(inputStream != null) {

                InputStreamReader reader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
                BufferedReader bufferedReader = new BufferedReader(reader);
                String line = bufferedReader.readLine();
                while (line != null) {
                    output.append(line);
                    line = bufferedReader.readLine();
                }

            }
            return  output.toString();
        }

}
