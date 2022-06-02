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
        localMeals.add(new Meal("Frietjes", "Friet is geweldig ofzo", true, true, false, true, "02/06/2022", 4, "$3.69", "https://www.google.com/url?sa=i&url=https%3A%2F%2Fnl.wikipedia.org%2Fwiki%2FFriet&psig=AOvVaw3iz7lWUgCowXYwWe3g7qoS&ust=1654272436494000&source=images&cd=vfe&ved=2ahUKEwjR_-OYk4_4AhXci_0HHdIeBgcQjRx6BAgAEAs", new String[]{"array", "of", "String",}));
        localMeals.add(new Meal("Pizza", "Pizza is nog beter dan friet", true, true, false, true, "31/05/2022", 2, "$6.99", "https://www.google.com/imgres?imgurl=https%3A%2F%2Fimages0.persgroep.net%2Frcs%2F5XA03cGALr-SKZ0BlzVGmD6vMo8%2Fdiocontent%2F114943802%2F_fitwidth%2F694%2F%3FappId%3D21791a8992982cd8da851550a453bd7f%26quality%3D0.8&imgrefurl=https%3A%2F%2Fwww.ad.nl%2Fkoken-en-eten%2Fpizza-is-net-zo-verslavend-als-drugs~ae94718c%2F&tbnid=L-UO75r_WubgEM&vet=12ahUKEwiRvqqik4_4AhVZh_0HHU-jANwQMygEegUIARDeAQ..i&docid=XbUlu3AR6NnhxM&w=694&h=463&q=pizza&client=opera-gx&ved=2ahUKEwiRvqqik4_4AhVZh_0HHU-jANwQMygEegUIARDeAQ", new String[]{"array", "of", "String",}));
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