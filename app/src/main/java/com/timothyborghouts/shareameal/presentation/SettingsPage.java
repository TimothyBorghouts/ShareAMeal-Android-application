package com.timothyborghouts.shareameal.presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.timothyborghouts.shareameal.R;

public class SettingsPage extends AppCompatActivity {

    Button saveButton;
    CheckBox darkModeCheckBox;
    SharedPreferences sharedPreferences;
    Boolean isDarkModeOn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_page);

        saveButton = findViewById(R.id.save_settings_button);
        darkModeCheckBox = findViewById(R.id.dark_mode_checkBox);

        sharedPreferences = getSharedPreferences("isDarkModeOn", MODE_PRIVATE);

        //Check if darkMode is on or on or off and set the checkBox the correct way.
        Boolean isDarkModeAlreadyOn = sharedPreferences.getBoolean("isDarkModeOn", false);
        darkModeCheckBox.setChecked(isDarkModeAlreadyOn);

        //Save all the changed settings when save settings button is clicked.
        saveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                darkModeSettings();
                finish();
            }
        });
    }

    public void darkModeSettings(){
        if(darkModeCheckBox.isChecked()){
            //Turn dark mode on and set boolean isDarkModeOn to true.
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            isDarkModeOn = true;

            //Save the darkModeSettings in the SharedPreferences.
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("isDarkModeOn", isDarkModeOn);
            editor.commit();
        }else{
            //Turn dark mode off and set boolean isDarkModeOn to false.
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            isDarkModeOn = false;

            //Save the darkModeSettings in the SharedPreferences.
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("isDarkModeOn", isDarkModeOn);
            editor.commit();
        }
    }
}