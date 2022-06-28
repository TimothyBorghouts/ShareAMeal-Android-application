package com.timothyborghouts.shareameal.presentation;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
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

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MealsPage extends AppCompatActivity implements MealListener {

    private static final String TAG = "MealsPage";

    ArrayList<Meal> meals = new ArrayList<>();
    ArrayList<Meal> filteredMeals = new ArrayList<>();
    ArrayList<Meal> savedMeals = new ArrayList<>();
    RecyclerView mealsRecyclerView;
    MealsAdapter mealsAdapter;

    boolean didFilterOnce = false;

    public static final String clickedMeal = "Meal";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meals_page);
        Log.i(TAG, "onCreate (Start Activity)");

        String actionBarTitle = getResources().getString(R.string.meals_page_label);
        getSupportActionBar().setTitle(actionBarTitle);


        savedMeals.addAll(meals);

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
                Log.d(TAG, "Filtering all meals on vega");
                filterMeals(1);
                mealsAdapter.notifyDataSetChanged();
                printItemCount();
                break;

            case R.id.filter_vegan:
                Log.d(TAG, "Filtering all meals on vegan");
                filterMeals(2);
                mealsAdapter.notifyDataSetChanged();
                printItemCount();
                break;

            case R.id.filter_take_home:
                Log.d(TAG, "Filtering all meals on take home");
                filterMeals(3);
                mealsAdapter.notifyDataSetChanged();
                printItemCount();
                break;
            case R.id.filter_remove:
                meals.clear();
                meals.addAll(savedMeals);
                Log.d(TAG, "Removed all filters.");
                mealsAdapter.notifyDataSetChanged();
                printItemCount();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void filterMeals(int filter){
        for(Meal meal : meals) {
            switch (filter) {
                case 1:
                    if(meal.isVega()){
                        this.filteredMeals.add(meal);
                    }
                    break;

                case 2:
                    if(meal.isVegan()){
                        this.filteredMeals.add(meal);
                    }
                    break;

                case 3:
                    if(meal.isToTakeHome()){
                        this.filteredMeals.add(meal);
                    }
                    break;
            }

        }
        meals.clear();
        meals.addAll(filteredMeals);

        didFilterOnce = true;

        filteredMeals.clear();
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