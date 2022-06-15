package com.timothyborghouts.shareameal.presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.timothyborghouts.shareameal.R;

public class SettingsPage extends AppCompatActivity {

    private static final String TAG = "SettingsPage";

    Button saveButton;
    CheckBox darkModeCheckBox;
    SharedPreferences sharedPreferences;
    Boolean isDarkModeOn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_page);
        Log.i(TAG, "onCreate (Start Activity)");

        saveButton = findViewById(R.id.save_settings_button);
        darkModeCheckBox = findViewById(R.id.dark_mode_checkBox);

        Log.d(TAG, "Create shared preferences for dark mode");
        sharedPreferences = getSharedPreferences("isDarkModeOn", MODE_PRIVATE);

        //Check if darkMode is on or on or off and set the checkBox the correct way.
        Log.d(TAG, "Check if shared preferences dark mode is on or off to set the checkBox");
        Boolean isDarkModeAlreadyOn = sharedPreferences.getBoolean("isDarkModeOn", false);
        darkModeCheckBox.setChecked(isDarkModeAlreadyOn);

        //Save all the changed settings when save settings button is clicked.
        saveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Log.d(TAG, "Clicked save settings button");
                darkModeSettings();
                Log.d(TAG, "Go back to last page (Close activity)");
                finish();
            }
        });
    }

    public void darkModeSettings(){
        Log.d(TAG, "Check if the dark mode CheckBox is checked");
        if(darkModeCheckBox.isChecked()){
            //Turn dark mode on and set boolean isDarkModeOn to true.
            Log.d(TAG, "Turn night mode on");
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            isDarkModeOn = true;

            //Save the darkModeSettings in the SharedPreferences.
            Log.d(TAG, "Save the sahredPreferences darkmode");
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("isDarkModeOn", isDarkModeOn);
            editor.commit();
        }else{
            //Turn dark mode off and set boolean isDarkModeOn to false.
            Log.d(TAG, "Turn night mode off");
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            isDarkModeOn = false;

            //Save the darkModeSettings in the SharedPreferences.
            Log.d(TAG, "Save the sahredPreferences darkmode");
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("isDarkModeOn", isDarkModeOn);
            editor.commit();
        }
    }
}