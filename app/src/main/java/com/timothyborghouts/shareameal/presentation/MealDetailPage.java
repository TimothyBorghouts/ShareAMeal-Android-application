package com.timothyborghouts.shareameal.presentation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.timothyborghouts.shareameal.R;
import com.timothyborghouts.shareameal.domain.Meal;

public class MealDetailPage extends AppCompatActivity {
    Meal meal = new Meal("Frietjes", "Friet is geweldig ofzo", true, true, false, true, "02/06/2022", 4, "$3.69", "https://cdn.pixabay.com/photo/2016/11/21/15/52/french-fries-1846083_960_720.jpg", new String[]{"gluten", "lactose",});

    ImageView mealImage;
    TextView mealTitle;
    TextView mealPrice;
    TextView mealDescription;
    TextView mealDate;
    TextView mealAllergies;
    CheckBox mealIsVega;
    CheckBox mealIsVegan;
    CheckBox mealIsToTakeHome;

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
        mealIsVega = findViewById(R.id.meal_is_vega);
        mealIsVegan = findViewById(R.id.meal_is_vegan);
        mealIsToTakeHome = findViewById(R.id.meal_is_to_take_home);

        String imageUri = "https://cdn.pixabay.com/photo/2016/11/21/15/52/french-fries-1846083_960_720.jpg";
        Picasso.get().load(imageUri).into(mealImage);
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
        mealIsVega.setChecked(meal.isVega());
        mealIsVegan.setChecked(meal.isVegan());
        mealIsToTakeHome.setChecked(meal.isToTakeHome());

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