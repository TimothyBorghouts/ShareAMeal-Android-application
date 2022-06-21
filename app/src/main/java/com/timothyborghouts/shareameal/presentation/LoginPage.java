package com.timothyborghouts.shareameal.presentation;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.timothyborghouts.shareameal.R;
import com.timothyborghouts.shareameal.domain.Meal;
import com.timothyborghouts.shareameal.domain.User;

public class LoginPage extends AppCompatActivity {

    private static final String TAG = "LoginPage";

    Button loginButton;

    User Timothy = new User("Timothy", "Borghouts", "Langdonk 31", "Breda", true, "ja", "nee", "0681391266");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
        Log.i(TAG, "onCreate (Start Activity)");

        String actionBarTitle = getResources().getString(R.string.login_page_label);
        getSupportActionBar().setTitle(actionBarTitle);

        loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Log.d(TAG, "Clicked on login button");
                login();
            }
        });
    }

    public void login() {
        Log.d(TAG, "Started method login");
        EditText emailInputField = (EditText) findViewById(R.id.emailInputField);
        EditText passwordInputField = (EditText) findViewById(R.id.passwordInputField);

        String inputEmail = emailInputField.getText().toString().toLowerCase();
        String inputPassword = passwordInputField.getText().toString();

        if (isPasswordCorrect(inputEmail, inputPassword)) {
            openActivity();
            Toast.makeText(this, "Logged in successfully", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "Logged in succesfully");
        } else {
            Toast.makeText(this, "Email or password is incorrect", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "Logged in failed");
        }

    }

    private boolean isPasswordCorrect(String inputEmail, String inputPassword) {
        Log.d(TAG, "Checking if input is correct");
        return (inputEmail.equals(Timothy.getEmailAdress().toLowerCase())) && (inputPassword.equals(Timothy.getPassword()));
    }

    private void openActivity() {
        Intent intent = new Intent(LoginPage.this, MealsPage.class);
        startActivity(intent);
        Log.d(TAG, "Go to MealsPage (Close activity)");
    }
}