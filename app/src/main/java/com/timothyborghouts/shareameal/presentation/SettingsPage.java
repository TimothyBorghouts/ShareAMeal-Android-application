package com.timothyborghouts.shareameal.presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;

import com.timothyborghouts.shareameal.R;

import java.util.Locale;

public class SettingsPage extends AppCompatActivity {

    private static final String TAG = "SettingsPage";

    CheckBox darkModeCheckBox;
    Spinner languageSpinner;
    Button saveButton;

    SharedPreferences darkModeSharedPreferences;
    SharedPreferences languageSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_page);
        Log.i(TAG, "onCreate (Start Activity)");

        String actionBarTitle = getResources().getString(R.string.settings_page_label);
        getSupportActionBar().setTitle(actionBarTitle);

        darkModeCheckBox = findViewById(R.id.dark_mode_checkBox);
        languageSpinner = findViewById(R.id.select_language_spinner);
        saveButton = findViewById(R.id.save_settings_button);

        Log.d(TAG, "Create shared preferences for all the settings");
        darkModeSharedPreferences = getSharedPreferences("selectedDarkMode", 0);
        languageSharedPreferences = getSharedPreferences("selectedLanguage", 0);

        setSettings();

        //Save all the changed settings when save settings button is clicked.
        saveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Log.d(TAG, "Clicked save settings button");

                darkModeSettings();
                languageSettings();
                recreate();

                Log.d(TAG, "Go back to last page (Close activity)");
                restartApplication();
            }
        });

    }
    public void setSettings(){
        //Check if darkMode is on or on or off and set the checkBox the correct way.
        Boolean selectedDarkModeSetting = darkModeSharedPreferences.getBoolean("selectedDarkMode", false);
        darkModeCheckBox.setChecked(selectedDarkModeSetting);

        //Check which language is selected and set the spinner the correct way.
        int selectedLanguageSetting = languageSharedPreferences.getInt("selectedLanguage", 0);
        languageSpinner.setSelection(selectedLanguageSetting);
    }

    public void darkModeSettings(){
        SharedPreferences.Editor editorDarkMode = darkModeSharedPreferences.edit();
        //Check Checkbox and set the dark mode on or off.
        if(darkModeCheckBox.isChecked()){
            Log.d(TAG, "Turn night mode on");
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            editorDarkMode.putBoolean("selectedDarkMode", true);
            editorDarkMode.commit();
        }else{
            Log.d(TAG, "Turn night mode off");
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            editorDarkMode.putBoolean("selectedDarkMode", false);
            editorDarkMode.commit();
        }
    }

    private void languageSettings() {
        SharedPreferences.Editor editor = languageSharedPreferences.edit();
        //Check the selected language in spinner and change the language.
        int inputLanguage = languageSpinner.getSelectedItemPosition();
        if(inputLanguage == 0) {
                setLanguage("en");
                editor.putInt("selectedLanguage", 0);
                editor.commit();
        }else {
            setLanguage("nl");
            editor.putInt("selectedDarkMode", 1);
            editor.commit();
        }
    }

    public void setLanguage(String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Resources resources = getResources();
        Configuration configuration = new Configuration();
        configuration.locale = locale;
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
    }

    private void restartApplication() {
        Intent intent = new Intent(SettingsPage.this, LoginPage.class);
        startActivity(intent);
    }


}