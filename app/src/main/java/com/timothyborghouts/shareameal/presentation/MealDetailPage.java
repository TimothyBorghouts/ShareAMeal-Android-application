package com.timothyborghouts.shareameal.presentation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.timothyborghouts.shareameal.R;
import com.timothyborghouts.shareameal.domain.Meal;

import java.util.ArrayList;

public class MealDetailPage extends AppCompatActivity {

    private static final String TAG = "MealDetailPage";

    ImageView mealImage;
    TextView mealTitle;
    TextView mealPrice;
    TextView mealDescription;
    TextView mealDate;
    TextView mealAmountOfParticipants;
    TextView mealAllergies;
    ImageView mealIsVega;
    ImageView mealIsVegan;
    ImageView mealIsToTakeHome;

    private Meal meal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meal_detail_page);
        Log.i(TAG, "onCreate (Start Activity)");

        String actionBarTitle = getResources().getString(R.string.meal_detail_page_label);
        getSupportActionBar().setTitle(actionBarTitle);

        mealImage = findViewById(R.id.meal_image);
        mealTitle = findViewById(R.id.meal_title);
        mealPrice = findViewById(R.id.meal_price);
        mealDescription = findViewById(R.id.meal_description);
        mealDate = findViewById(R.id.meal_date);
        mealAmountOfParticipants = findViewById(R.id.meal_max_amount_of_participants);
        mealAllergies = findViewById(R.id.meal_allergies);
        mealIsVega = findViewById(R.id.check_vega);
        mealIsVegan = findViewById(R.id.check_vegan);
        mealIsToTakeHome = findViewById(R.id.check_take_home);

        Log.d(TAG, "Get the Intent extra with meal");
        meal = (Meal) getIntent().getSerializableExtra(MealsPage.clickedMeal);

        Log.d(TAG, "Place the meal information in all the page components");
        String imageUrl = meal.getImageUrl();
        Picasso.get().load(imageUrl).into(mealImage);

        mealTitle.setText(meal.getName());
        mealPrice.setText("€" + meal.getPrice());
        mealDescription.setText(meal.getDescription());
        mealDate.setText(meal.getDate());
        String maxAmountOfParticipants = String.valueOf(meal.getMaxAmountOfParticipants());
        String maxPeopleText = getResources().getString(R.string.max_amount_of_participants_text);
        mealAmountOfParticipants.setText(maxPeopleText + " " + maxAmountOfParticipants);

        String allergies = getResources().getString(R.string.allergies_text);
        ArrayList<String> allergiesArray = meal.getAllergies();
        for (String allergie : allergiesArray) {
            allergies = allergies + " " + allergie;
        }
        mealAllergies.setText(allergies);


        if (!meal.isVega()) {
            mealIsVega.setVisibility(View.GONE);
        }
        if (!meal.isVegan()) {
            mealIsVegan.setVisibility(View.GONE);
        }
        if (!meal.isToTakeHome()) {
            mealIsToTakeHome.setVisibility(View.GONE);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settings_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings:
                Log.d(TAG, "Go to SettingsPage with settings menu (Close activity)");
                Intent intent = new Intent(MealDetailPage.this, SettingsPage.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}