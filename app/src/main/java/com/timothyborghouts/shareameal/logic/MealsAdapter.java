package com.timothyborghouts.shareameal.logic;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.timothyborghouts.shareameal.R;
import com.timothyborghouts.shareameal.domain.Meal;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MealsAdapter extends RecyclerView.Adapter<MealsAdapter.MealViewHolder> {

    private static final String TAG = "RecyclerView";

    private ArrayList<Meal> meals;
    private LayoutInflater mInflater;
    private MealListener listener;
    private Context context;

    public MealsAdapter(ArrayList<Meal> meals, Context context) {
        this.context = context;
        this.listener = (MealListener) context;
        mInflater = LayoutInflater.from(context);
        this.meals = meals;
    }

    @NonNull
    @Override
    public MealsAdapter.MealViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.meal_recyclerview_item, parent, false);
        Log.i(TAG, "onCreateViewHolder (Start RecyclerView)");
        return new MealViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MealsAdapter.MealViewHolder holder, int position) {
        Meal meal = this.meals.get(position);

        try {
            Picasso.get().load(meals.get(position).getImageUrl()).into(holder.image);
        } catch (Exception e) {
        }
        holder.title.setText(meal.getName());
        holder.date.setText(meal.getDate());
        holder.price.setText("€" + meal.getPrice());
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "retrieving ItemCount");
        return meals.size();
    }

    class MealViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;
        public TextView title;
        public TextView date;
        public TextView price;

        public MealViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.meal_image);
            title = itemView.findViewById(R.id.meal_title);
            date = itemView.findViewById(R.id.meal_date);
            price = itemView.findViewById(R.id.meal_price);

            itemView.setOnClickListener(this::onClick);
        }


        public void onClick(View view) {
            Log.d(TAG, "Clicked on a meal and going to that specific meal detail page");
            int itemIndex = getLayoutPosition();

            Meal meal = meals.get(itemIndex);

            listener.goToDetailPage(meal);
        }

    }

}