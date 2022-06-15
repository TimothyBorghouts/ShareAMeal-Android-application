package com.timothyborghouts.shareameal.presentation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.timothyborghouts.shareameal.R;
import com.timothyborghouts.shareameal.domain.Meal;

public class CreateMealPage extends AppCompatActivity {

    private static final String TAG = "CreateMealPage";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_meal_page);
        Log.i(TAG, "onCreate (Start Activity)");
    }

    public void createMeal(View view) {
        Log.d(TAG, "Pressed create meal button");
        Log.d(TAG, "Getting all the input from the fields");
        //Get all the input fields and checkboxes.
        EditText nameInputField = findViewById(R.id.meal_name_input);
        EditText priceInputField = findViewById(R.id.meal_price_input);
        EditText dateInputField = findViewById(R.id.meal_date_input);
        EditText descriptionInputField = findViewById(R.id.meal_description_input);
        EditText imageUrlInputField = findViewById(R.id.meal_image_url_input);
        CheckBox vegaCheckbox = findViewById(R.id.vega_checkbox_input);
        CheckBox veganCheckbox = findViewById(R.id.vegan_checkbox_input);
        CheckBox takeHomeCheckbox = findViewById(R.id.take_home_checkbox_input);
        Spinner maxAmountParticipants = findViewById(R.id.max_amount_of_participants_spinner);

        Log.d(TAG, "Retrieving all the input from the fields");
        //Retrieve all the input from the input fields and checkboxes.
        String name = nameInputField.getText().toString();
        String description = descriptionInputField.getText().toString();
        boolean active = true;
        boolean vega = vegaCheckbox.isChecked();
        boolean vegan = veganCheckbox.isChecked();
        boolean takeHome = takeHomeCheckbox.isChecked();
        String date = dateInputField.getText().toString();
        int maxAmountOfParticipants = Integer.parseInt(maxAmountParticipants.getSelectedItem().toString());
        String price = priceInputField.getText().toString();

        String imageurl = imageUrlInputField.getText().toString();
        if(imageurl.length() < 1){
            imageurl = "drawable/no_image.png";
        }

        String[] allergies = {""};

        Log.d(TAG, "Creating meal object with all the input");
        //Use all the retrieved atributes and put them in a meal object.
        Meal meal = new Meal(name, description, active, vega, vegan, takeHome, date, maxAmountOfParticipants, price, imageurl, allergies);

        //Send the meal object back to the meals page.
        addMealAndGoBack(meal);

    }

    public void addMealAndGoBack(Meal meal){
        Intent intent = new Intent(this, MealsPage.class);
        intent.putExtra("Meal", meal);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d(TAG, "Go back to the meals page and send the meal with it. (Close activity)");
        getMenuInflater().inflate(R.menu.settings_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings:
                Log.d(TAG, "Go to SettingsPage (Close activity)");
                Intent intent = new Intent(CreateMealPage.this, SettingsPage.class);
                startActivity(intent);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}