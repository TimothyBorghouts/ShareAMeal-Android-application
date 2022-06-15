package com.timothyborghouts.shareameal.presentation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.timothyborghouts.shareameal.R;
import com.timothyborghouts.shareameal.domain.Meal;
import com.timothyborghouts.shareameal.logic.MealListener;
import com.timothyborghouts.shareameal.logic.MealsAdapter;

import java.util.ArrayList;
import java.util.Collections;

public class MealsPage extends AppCompatActivity implements MealListener {

    private static final String TAG = "MealsPage";

    ArrayList<Meal> meals;
    RecyclerView mealsRecyclerView;
    MealsAdapter mealsAdapter;

    public static final String clickedMeal = "Meal";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meals_page);
        Log.i(TAG, "onCreate (Start Activity)");

        //If there are local meals created add them to the list
        Log.d(TAG, "Used test data and filled list with meals");
        this.meals = fillListWithMeals();

        //If meals were added with create page add them to the list
        Log.d(TAG, "Check if a meal was added");
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            Meal addedMeal = (Meal) getIntent().getSerializableExtra("Meal");
            this.meals.add(addedMeal);
        }

        //Create Recyclerview with layout and meals.
        Log.d(TAG, "Create recyclerview");
        mealsRecyclerView = findViewById(R.id.meal_recyclerview);
        mealsAdapter = new MealsAdapter(meals, this);

        mealsRecyclerView.setAdapter(mealsAdapter);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            mealsRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        } else {
            mealsRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        }

        Log.d(TAG, "Toast the amount of meals loaded");
        printItemCount();
    }

    public void printItemCount() {
        Toast toast = Toast.makeText(getApplicationContext(), "Loaded " + mealsAdapter.getItemCount() + " meals", Toast.LENGTH_SHORT);
        toast.show();
    }

    private ArrayList<Meal> fillListWithMeals() {
        ArrayList<Meal> localMeals = new ArrayList<>();
        localMeals.add(new Meal("Frietjes", "Friet is geweldig ofzo", true, true, false, true, "02/06/2022", 4, "$3.69", "https://cdn.pixabay.com/photo/2016/11/21/15/52/french-fries-1846083_960_720.jpg", new String[]{"gluten", "lactose",}));
        localMeals.add(new Meal("Pizza", "Pizza is nog beter dan friet", true, true, false, true, "31/05/2022", 2, "$6.99", "https://cdn.pixabay.com/photo/2017/12/10/14/47/pizza-3010062_960_720.jpg", new String[]{"gluten",}));
        localMeals.add(new Meal("Pasta", "Pasta is toch wel favoriet", false, true, false, false, "01/06/2022", 3, "$2.00", "https://cdn.pixabay.com/photo/2018/07/18/19/12/pasta-3547078_960_720.jpg", new String[]{"lactose", "ei",}));
        localMeals.add(new Meal("Lasagna", "Lasagna is soms lekker maar niet altijd", true, false, false, true, "30/05/2022", 6, "$13.00", "https://cdn.pixabay.com/photo/2021/02/06/11/51/food-5987888_960_720.jpg", new String[]{"gluten, lactose", "ei"}));
        return localMeals;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d(TAG, "Creating menu");
        getMenuInflater().inflate(R.menu.filter_menu, menu);
        getMenuInflater().inflate(R.menu.settings_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings:
                Log.d(TAG, "Menu/Settings Go to SettingsPage");
                Intent intent = new Intent(MealsPage.this, SettingsPage.class);
                startActivity(intent);
                break;

            case R.id.filter_vega:
                Log.d(TAG, "Menu/Vega Only a toast displays");
                Toast filterVegaToast = Toast.makeText(getApplicationContext(), "Could not Filter all vega meals.", Toast.LENGTH_SHORT);
                filterVegaToast.show();
                break;

            case R.id.filter_vegan:
                Log.d(TAG, "Menu/Vegan Only a toast displays");
                Toast filterVeganToast = Toast.makeText(getApplicationContext(), "Could not Filter all vegan meals.", Toast.LENGTH_SHORT);
                filterVeganToast.show();
                break;

            case R.id.filter_take_home:
                Log.d(TAG, "Menu/Take home Only a toast displays");
                Toast filterTakeHomeToast = Toast.makeText(getApplicationContext(), "Could not Filter all take home meals.", Toast.LENGTH_SHORT);
                filterTakeHomeToast.show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void goToDetailPage(Meal meal) {
        Log.d(TAG, "Go to the DetailPage (Close activity)");
        Intent intent = new Intent(this, MealDetailPage.class);
        intent.putExtra("Meal", meal);
        startActivity(intent);
    }

    public void goToCreateMealPage(View view) {
        Log.d(TAG, "Go to the CreateMealPage (Close activity)");
        Intent intent = new Intent(this, CreateMealPage.class);
        startActivity(intent);
    }
}