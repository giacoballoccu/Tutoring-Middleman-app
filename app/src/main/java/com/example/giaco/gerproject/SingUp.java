package com.example.giaco.gerproject;
import com.example.giaco.gerproject.*;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class SingUp extends AppCompatActivity {

    EditText email, nome, cognome, password, confermaPassword, cellulare;
    UserFactory factory = UserFactory.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sing_up);

        email = (EditText) findViewById(R.id.emailField);
        nome = (EditText) findViewById(R.id.nameField);
        cognome = (EditText) findViewById(R.id.surnameField);
        password = (EditText) findViewById(R.id.passwordField);
        confermaPassword = (EditText) findViewById(R.id.confirmPasswordField);
        cellulare = (EditText) findViewById(R.id.phoneField);
        
    }
}
