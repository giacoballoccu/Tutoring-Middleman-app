package com.example.giaco.gerproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignUp extends AppCompatActivity {

    UserStudenteFactory factory = UserStudenteFactory.getInstance();

    EditText email, name, surname, password, confirmPassword, phone;
    Button signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        /*Recupero parametri da Layout*/

        email = (EditText) findViewById(R.id.emailField);
        name = (EditText) findViewById(R.id.nameField);
        surname = (EditText) findViewById(R.id.surnameField);
        password = (EditText) findViewById(R.id.passwordField);
        confirmPassword = (EditText) findViewById(R.id.confirmPasswordField);
        phone = (EditText) findViewById(R.id.phoneField);

        signUp = (Button) findViewById(R.id.signUp);

        /*Registrazione*/

        //SCELTA PROGETTUALE, CONTROLLI SUGLI INPUT NEL LAYOUT O QUA? PER ORA NON CE N'E'
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        if(factory.isEmailInUserList(email.getText().toString())){
                //call error gui utente esiste già
            }else{
                UserStudente newUser = new UserStudente(email.getText().toString(), name.getText().toString(), surname.getText().toString(), password.getText().toString(), phone.getText().toString());

                factory.getUserList().add(newUser);
                //call success gui registrazione avvenuta con successo
            Intent registrationSuccess = new Intent(SignUp.this,
                    LoginPage.class);
            startActivity(registrationSuccess);
            }
            }
        });
        }

    }
