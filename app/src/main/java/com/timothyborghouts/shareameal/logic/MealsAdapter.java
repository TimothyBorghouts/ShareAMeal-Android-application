package com.timothyborghouts.shareameal.logic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.timothyborghouts.shareameal.R;
import com.timothyborghouts.shareameal.domain.Meal;

import java.util.ArrayList;

public class MealsAdapter extends RecyclerView.Adapter<MealsAdapter.MealViewHolder> {

    private ArrayList<Meal> meals;
    private Context context;
    private LayoutInflater mInflater;

    public MealsAdapter(ArrayList<Meal> meals, Context context) {
        this.meals = meals;
        this.context = context;
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MealsAdapter.MealViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.meal_recyclerview_item, parent, false);
        return new MealViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MealsAdapter.MealViewHolder holder, int position) {
        Meal meal = this.meals.get(position);

        holder.title.setText(meal.getName());
        holder.date.setText(meal.getDateTime());
        holder.price.setText(meal.getPrice());

    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    class MealViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView date;
        public TextView price;


        public MealViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.meal_title);
            date = itemView.findViewById(R.id.meal_date);
            price = itemView.findViewById(R.id.meal_price);

        }

    }

}