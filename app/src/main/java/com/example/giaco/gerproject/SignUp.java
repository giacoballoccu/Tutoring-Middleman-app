package com.example.giaco.gerproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SignUp extends AppCompatActivity {

    Button signUp;

    Boolean emailAlreadyExists;

    EditText email, name, surname, password, confirmPassword, phone;
    String emailStr, nameStr, surnameStr, passwordStr, confirmPasswordStr, phoneStr;

    TextView errorSignup;

    UserStudenteFactory factory = UserStudenteFactory.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        /*Recupero parametri da Layout*/

        email = (EditText) findViewById(R.id.emailField);
        emailStr = email.getText().toString();

        name = (EditText) findViewById(R.id.nameField);
        nameStr = name.getText().toString();

        surname = (EditText) findViewById(R.id.surnameField);
        surnameStr = surname.getText().toString();

        password = (EditText) findViewById(R.id.passwordField);
        passwordStr = password.getText().toString();

        confirmPassword = (EditText) findViewById(R.id.confirmPasswordField);
        confirmPasswordStr = confirmPassword.getText().toString();

        phone = (EditText) findViewById(R.id.phoneField);
        phoneStr = phone.getText().toString();

        errorSignup = (TextView) findViewById(R.id.errorSignup);

        signUp = (Button) findViewById(R.id.signUp);

        /*Check sugli input*/
        if(TextUtils.isEmpty(emailStr)) {
            email.setError("Inserire una mail valida");
            return;
        }

        if(TextUtils.isEmpty(nameStr)) {
            name.setError("Inserire il proprio nome");
            return;
        }
        if(TextUtils.isEmpty(surnameStr)) {
            surname.setError("Inserire il proprio cognome");
            return;
        }

        if(TextUtils.isEmpty(passwordStr)) {
            password.setError("Inserire una password");
            return;
        }

        if(TextUtils.isEmpty(confirmPasswordStr)) {
            confirmPassword.setError("Inserire nuovamente la password");
            return;
        }

        if(!confirmPasswordStr.equals(passwordStr)) {
            confirmPassword.setError("Le password non corrispondono");
            return;
        }

        if(TextUtils.isEmpty(phoneStr) || phoneStr.length() == 10) {
            phone.setError("Inserire un numero di telefono valido");
            return;
        }


        /*Registrazione*/

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        if(factory.isEmailInUserList(emailStr)){
            errorSignup.setVisibility(View.VISIBLE); //Email esistente
        }else{
                UserStudente newUser = new UserStudente(emailStr, nameStr, surnameStr, passwordStr, phoneStr);

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
