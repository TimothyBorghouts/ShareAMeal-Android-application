package com.timothyborghouts.shareameal.presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.timothyborghouts.shareameal.R;
import com.timothyborghouts.shareameal.domain.Meal;
import com.timothyborghouts.shareameal.logic.MealsAdapter;

import java.util.ArrayList;

public class MealsPage extends AppCompatActivity {

    ArrayList<Meal> meals;
    RecyclerView mealsRecyclerView;
    MealsAdapter mealsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meals_page);

        this.meals = fillListWithMeals();

        mealsRecyclerView = findViewById(R.id.meal_recyclerview);
        mealsAdapter = new MealsAdapter(meals, this);

        mealsRecyclerView.setAdapter(mealsAdapter);
        mealsRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));



    }

    private ArrayList<Meal> fillListWithMeals() {
        ArrayList<Meal> localMeals = new ArrayList<>();
        localMeals.add(new Meal("Frietjes", "Friet is geweldig ofzo", true, true, false, true, "2022/01/101", 4, "$4.69", "true", new String[]{"array", "of", "String",}));
        return localMeals;
    }

}