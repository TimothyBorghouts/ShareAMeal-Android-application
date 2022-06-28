package com.timothyborghouts.shareameal.data;

import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

public class NetworkUtils {

    private static final String TAG = "NetworkUtils";

    private static final String MEAL_URL = "https://shareameal-api.herokuapp.com/api/meal";

    static String getMeal(){
        Log.d(TAG, "Starting NetworkUtils method getMeal()");
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String MealJSONString = null;

        try {
            Uri buildURI = Uri.parse(MEAL_URL);
            URL requestURL = new URL(buildURI.toString());

            urlConnection = (HttpURLConnection) requestURL.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();

            reader = new BufferedReader(new InputStreamReader(inputStream));

            StringBuilder builder = new StringBuilder();

            String line;

            while((line = reader.readLine()) != null) {
                builder.append(line);
                builder.append("\n");
            }

            if (builder.length() == 0 ) {
                Log.d(TAG, "Failed to retrieve JSON data.");
                return null;
            }

            MealJSONString = builder.toString();

        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            Log.d(TAG, "Closing connection and reader if not already closed");
            if(urlConnection != null) {
                urlConnection.disconnect();
            }
            if(reader != null){
                try {
                    reader.close();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        }

        Log.d(TAG, "Api has been succesfully called");
        Log.d(TAG, MealJSONString);

        return MealJSONString;
    }

}
