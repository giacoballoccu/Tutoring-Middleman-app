package com.example.giaco.gerproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class SingUp extends AppCompatActivity {

    EditText email, nome, cognome, password, confermaPassword, cellulare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sing_up);

        email = (EditText) FindViewById(R.id.emailField);
        nome = (EditText) FindViewById(R.id.nameField);
        cognome = (EditText) FindViewById(R.id.surnameField);
        password = (EditText) FindViewById(R.id.passwordField);
        confermaPassword = (EditText) FindViewById(R.id.confirmPasswordField);
        cellulare = (EditText) FindViewById(R.id.phoneField);
    }
}
