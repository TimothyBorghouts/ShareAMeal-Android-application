package com.timothyborghouts.shareameal.presentation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.timothyborghouts.shareameal.R;
import com.timothyborghouts.shareameal.data.FetchMealAsyncTask;
import com.timothyborghouts.shareameal.domain.Meal;
import com.timothyborghouts.shareameal.logic.DatasetListener;
import com.timothyborghouts.shareameal.logic.MealListener;
import com.timothyborghouts.shareameal.logic.MealsAdapter;

import java.util.ArrayList;

public class MealsPage extends AppCompatActivity implements MealListener, DatasetListener {

    private static final String TAG = "MealsPage";

    ArrayList<Meal> meals = new ArrayList<>();
    ArrayList<Meal> filteredMeals = new ArrayList<>();
    ArrayList<Meal> savedMeals = new ArrayList<>();

    RecyclerView mealsRecyclerView;
    MealsAdapter mealsAdapter;

    SharedPreferences filterSharedPreferences;

    public static final String clickedMeal = "Meal";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meals_page);
        Log.i(TAG, "onCreate (Start Activity)");

        String actionBarTitle = getResources().getString(R.string.meals_page_label);
        getSupportActionBar().setTitle(actionBarTitle);

        new FetchMealAsyncTask(this).execute();

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
    }

    public void printItemCount() {
        Toast.makeText(getApplicationContext(), "Loaded " + mealsAdapter.getItemCount() + " meals", Toast.LENGTH_SHORT).show();
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
                filterMeals(1);
                saveFilter(1);
                break;

            case R.id.filter_vegan:
                filterMeals(2);
                saveFilter(2);
                break;

            case R.id.filter_take_home:
                filterMeals(3);
                saveFilter(3);
                break;
            case R.id.filter_remove:
                removeFilter();
                saveFilter(0);
                break;
            default:
                printItemCount();
                mealsAdapter.notifyDataSetChanged();
        }

        return super.onOptionsItemSelected(item);
    }

    private void removeFilter() {
        this.meals.clear();
        this.meals.addAll(savedMeals);
        Log.d(TAG, "Removed all filters.");
    }

    public void filterMeals(int filter) {
        Log.d(TAG, "Filtering all meals");
        for (Meal meal : savedMeals) {
            switch (filter) {
                case 1:
                    if (meal.isVega()) {
                        this.filteredMeals.add(meal);
                    }
                    break;

                case 2:
                    if (meal.isVegan()) {
                        this.filteredMeals.add(meal);
                    }
                    break;

                case 3:
                    if (meal.isToTakeHome()) {
                        this.filteredMeals.add(meal);
                    }
                    break;
            }
        }

        meals.clear();
        meals.addAll(filteredMeals);

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

    @Override
    public void addMeal(Meal meal) {
        Log.d(TAG, "" + meal);
        this.meals.add(meal);
    }

    @Override
    public void datasetUpdated() {
        this.mealsAdapter.notifyDataSetChanged();
        this.savedMeals.addAll(meals);
        loadFilter();
        Log.d(TAG, "Toast the amount of meals loaded");
        printItemCount();
    }

    public void saveFilter(int selectedFilter) {

        SharedPreferences.Editor editor = filterSharedPreferences.edit();
        editor.putInt("filter", selectedFilter);

        editor.apply();
    }

    public void loadFilter() {
        filterSharedPreferences = getSharedPreferences("filter", Context.MODE_PRIVATE);

        int savedFilter = filterSharedPreferences.getInt("filter", 0);
        switch (savedFilter) {
            case 0:
                removeFilter();
                break;

            case 1:
                filterMeals(1);
                break;

            case 2:
                filterMeals(2);
                break;

            case 3:
                filterMeals(3);
                break;
        }
    }
}