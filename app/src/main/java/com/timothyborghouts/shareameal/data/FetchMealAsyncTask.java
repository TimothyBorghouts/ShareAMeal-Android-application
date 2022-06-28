package com.timothyborghouts.shareameal.data;

import android.os.AsyncTask;
import android.util.Log;

import com.timothyborghouts.shareameal.domain.Meal;
import com.timothyborghouts.shareameal.logic.DatasetListener;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class FetchMealAsyncTask extends AsyncTask<Void, Void, String> {

    private static final String TAG = "AsyncTask";

    private DatasetListener listener;

    public FetchMealAsyncTask(DatasetListener listener) {
        this.listener = listener;
    }

    @Override
    protected String doInBackground(Void... voids) {
        Log.d(TAG, "Starting doInBackground NetworkUtils method getMeal()");
        return NetworkUtils.getMeal();
    }


    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        try {
            JSONObject jsonObject = new JSONObject(s);
            JSONArray jsonArray = jsonObject.getJSONArray("result");

            int i = 0;
            String name;
            String description;
            boolean isActive;
            boolean isVega;
            boolean isVegan;
            boolean isToTakeHome;
            String dateTime;
            int maxAmountOfParticipants;
            String price;
            String imageUrl;

            JSONArray allergies;
            ArrayList allergenes = new ArrayList();


            Log.d(TAG, "" + jsonArray.length());
            while (i < jsonArray.length() - 32) {

                JSONObject meal = jsonArray.getJSONObject(i);

                try {

                    name = meal.getString("name");
                    Log.d(TAG, "Meal name: " + name);
                    description = meal.getString("description");
                    isActive = meal.getBoolean("isActive");
                    isVega = meal.getBoolean("isVega");
                    isVegan = meal.getBoolean("isVegan");
                    isToTakeHome = meal.getBoolean("isToTakeHome");
                    dateTime = meal.getString("dateTime");
                    maxAmountOfParticipants = meal.getInt("maxAmountOfParticipants");
                    price = meal.getString("price");
                    imageUrl = meal.getString("imageUrl");
                    allergies = meal.getJSONArray("allergenes");

                    if (allergies != null) {
                        allergenes = new ArrayList<>();
                        for (int x = 0; x < allergies.length(); x++) {
                            allergenes.add(allergies.getString(x));
                        }
                    }


                    Meal newMeal = new Meal(name, description, isActive, isVega, isVegan, isToTakeHome, dateTime, maxAmountOfParticipants, price, imageUrl, allergenes);
                    Log.d(TAG, "" + newMeal);

                    listener.addMeal(newMeal);

                } catch (Exception exception) {
                    exception.printStackTrace();
                }

                i++;

            }

            listener.datasetUpdated();

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
