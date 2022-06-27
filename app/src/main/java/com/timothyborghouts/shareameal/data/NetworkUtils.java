package com.timothyborghouts.shareameal.data;

import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkUtils {
    private static final String MEAL_URL = "https://shareameal-api.herokuapp.com/api/meal";
    private static final String MAX_RESULTS = "maxResults";
    private static final String PRINT_TYPE = "printType";

    static String getMeal(String queryString){
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String MealJSONString = null;

        try {
            Uri builtURI = Uri.parse(MEAL_URL).buildUpon().appendQueryParameter(MAX_RESULTS, "10").appendQueryParameter(PRINT_TYPE, "meals").build();
            URL requestURL = new URL(builtURI.toString());

            urlConnection = (HttpURLConnection) requestURL.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();

            reader = new BufferedReader(new InputStreamReader(inputStream));

            StringBuilder builder = new StringBuilder();

            String line;

            while((line = reader.readLine()) != null) {
                builder.append(line);
                builder.append("/n");
            }

            if (builder.length() == 0 ) {
                return null;
            }

        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
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

        Log.d("string", MealJSONString);

        return MealJSONString;
    }

}
