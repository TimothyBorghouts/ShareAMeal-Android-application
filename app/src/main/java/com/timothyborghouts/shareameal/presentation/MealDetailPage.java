package com.timothyborghouts.shareameal.presentation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.timothyborghouts.shareameal.R;
import com.timothyborghouts.shareameal.domain.Meal;

public class MealDetailPage extends AppCompatActivity {

    ImageView mealImage;
    TextView mealTitle;
    TextView mealPrice;
    TextView mealDescription;
    TextView mealDate;
    TextView mealAllergies;
    ImageView mealIsVega;
    ImageView mealIsVegan;
    ImageView mealIsToTakeHome;

    private Meal meal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meal_detail_page);

        mealImage = findViewById(R.id.meal_image);
        mealTitle = findViewById(R.id.meal_title);
        mealPrice = findViewById(R.id.meal_price);
        mealDescription = findViewById(R.id.meal_description);
        mealDate = findViewById(R.id.meal_date);
        mealAllergies = findViewById(R.id.meal_allergies);
        mealIsVega = findViewById(R.id.check_vega);
        mealIsVegan = findViewById(R.id.check_vegan);
        mealIsToTakeHome = findViewById(R.id.check_take_home);

        meal = (Meal) getIntent().getSerializableExtra(MealsPage.clickedMeal);

        String imageUrl = meal.getImageUrl();
        Picasso.get().load(imageUrl).into(mealImage);

        mealTitle.setText(meal.getName());
        mealPrice.setText(meal.getPrice());
        mealDescription.setText(meal.getDescription());
        mealDate.setText(meal.getDate());

        String allergies = "Allergies: ";
        String[] allergiesArray = meal.getAllergies();
        for(String allergie: allergiesArray){
            allergies = allergies + " " + allergie;
        }

        mealAllergies.setText(allergies);
        if(meal.isVega()){mealIsVega.setVisibility(View.GONE);}
        if(meal.isVegan()){mealIsVegan.setVisibility(View.GONE);}
        if(meal.isToTakeHome()){mealIsToTakeHome.setVisibility(View.GONE);}

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settings_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings:
                Intent intent = new Intent(MealDetailPage.this, SettingsPage.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}