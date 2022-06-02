package com.timothyborghouts.shareameal.presentation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.timothyborghouts.shareameal.R;
import com.timothyborghouts.shareameal.domain.Meal;
import com.timothyborghouts.shareameal.logic.MealsAdapter;

import java.util.ArrayList;

public class MealsPage extends AppCompatActivity {

    boolean mDarkModeEnabled = false;

    ArrayList<Meal> meals;
    RecyclerView mealsRecyclerView;
    MealsAdapter mealsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meals_page);

        //Create meals to use in recyclerview
        this.meals = fillListWithMeals();

        mealsRecyclerView = findViewById(R.id.meal_recyclerview);
        mealsAdapter = new MealsAdapter(meals, this);

        mealsRecyclerView.setAdapter(mealsAdapter);
        mealsRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));



    }

    private ArrayList<Meal> fillListWithMeals() {
        ArrayList<Meal> localMeals = new ArrayList<>();
        localMeals.add(new Meal("Frietjes", "Friet is geweldig ofzo", true, true, false, true, "02/06/2022", 4, "$3.69", "https://cdn.pixabay.com/photo/2016/11/21/15/52/french-fries-1846083_960_720.jpg", new String[]{"array", "of", "String",}));
        localMeals.add(new Meal("Pizza", "Pizza is nog beter dan friet", true, true, false, true, "31/05/2022", 2, "$6.99", "https://cdn.pixabay.com/photo/2017/12/10/14/47/pizza-3010062_960_720.jpg", new String[]{"array", "of", "String",}));
        localMeals.add(new Meal("Pasta", "Pasta is toch wel favoriet", false, true, false, false, "01/06/2022", 3, "$2.00", "https://cdn.pixabay.com/photo/2018/07/18/19/12/pasta-3547078_960_720.jpg", new String[]{"array", "of", "String",}));
        localMeals.add(new Meal("Lasagna", "Lasagna is soms lekker maar niet altijd", true, false, false, true, "30/05/2022", 6, "$13.00", "https://cdn.pixabay.com/photo/2021/02/06/11/51/food-5987888_960_720.jpg", new String[]{"array", "of", "String",}));
        return localMeals;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.dark_mode_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {

            case R.id.dark_mode:

                if(mDarkModeEnabled){
                    mDarkModeEnabled = false;
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    Toast.makeText(this, "Set theme to light mode", Toast.LENGTH_SHORT).show();
                }else{
                    mDarkModeEnabled = true;
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    Toast.makeText(this, "Set theme to dark mode", Toast.LENGTH_SHORT).show();
                }

                break;
        }

        return super.onOptionsItemSelected(item);

    }

}