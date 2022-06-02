package com.timothyborghouts.shareameal.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.timothyborghouts.shareameal.R;
import com.timothyborghouts.shareameal.domain.User;

import java.util.Locale;

public class LoginPage extends AppCompatActivity {

    User Timothy = new User("Timothy", "Borghouts", "Langdonk 31", "Breda", true, "Timothy.borghouts2@gmail.com", "#Suckdick2", "0681391266");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
    }

    public void login(View view) {
        EditText emailInputField = (EditText) findViewById(R.id.emailInputField);
        EditText passwordInputField = (EditText) findViewById(R.id.passwordInputField);

        String inputEmail = emailInputField.getText().toString().toLowerCase();
        String inputPassword = passwordInputField.getText().toString();

        if (isPasswordCorrect(inputEmail, inputPassword)) {
            openActivity();
            Toast.makeText(this, "Logged in successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Email or password is incorrect", Toast.LENGTH_SHORT).show();
        }

    }

    private boolean isPasswordCorrect(String inputEmail, String inputPassword) {
        return (inputEmail.equals(Timothy.getEmailAdress().toLowerCase())) && (inputPassword.equals(Timothy.getPassword()));
    }

    private void openActivity() {
        Intent intent = new Intent(LoginPage.this, MealsPage.class);
        startActivity(intent);
    }
}