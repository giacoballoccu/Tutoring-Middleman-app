package com.example.giaco.gerproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.giaco.gerproject.Classes.UserStudente;
import com.example.giaco.gerproject.Classes.UserStudenteFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUp extends AppCompatActivity {

    Button signUp;
    Boolean emailAlreadyExists;

    int errors = 0;

    EditText email, name, surname, password, confirmPassword, phone;
    String emailStr, nameStr, surnameStr, passwordStr, confirmPasswordStr, phoneStr;

    TextView errorSignup;

    UserStudenteFactory factory = UserStudenteFactory.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        ImageButton back = findViewById(R.id.pulsante_indietro);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplicationContext(), LoginPage.class);
                startActivityForResult(myIntent, 0);
            }
        });


        /*Recupero parametri da Layout*/

        email = (EditText) findViewById(R.id.emailField);

        name = (EditText) findViewById(R.id.nameField);

        surname = (EditText) findViewById(R.id.surnameField);

        password = (EditText) findViewById(R.id.passwordField);

        confirmPassword = (EditText) findViewById(R.id.confirmPasswordField);

        phone = (EditText) findViewById(R.id.phoneField);

        errorSignup = (TextView) findViewById(R.id.errorSignup);

        signUp = (Button) findViewById(R.id.signUp);

        /*Registrazione*/

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            emailStr = email.getText().toString();
            nameStr = name.getText().toString();
            surnameStr = surname.getText().toString();
            passwordStr = password.getText().toString();
            confirmPasswordStr = confirmPassword.getText().toString();
            phoneStr = phone.getText().toString();

            /*Check sugli input*/

            /*EmptyInput*/
            if(TextUtils.isEmpty(emailStr)) {
                email.setError("Inserire una mail");
                errors++;
            }else{
                password.setError(null);
            }

            /*Check if the user has insert an email andress*/
            Pattern pattern = Pattern.compile("[A-Za-z0-9._%+-]+@[AZa-z0-9.-]+-\\.[A-Za-z]{2,4}");
            Matcher mat = pattern.matcher(emailStr);

            if(mat.matches()){
                password.setError(null);

            }else{
                email.setError("Inserisci un email valida");
                errors++;
            }

            /*Check if user has already sign up to the app*/
            if(factory.isEmailInUserList(emailStr)){
                email.setError("L'email è già stata utilizzata");
                errors++;
            }else{
                password.setError(null);
            }

            /*EmptyInput*/
            if(TextUtils.isEmpty(nameStr)) {
                name.setError("Questo campo non può essere vuoto");
                errors++;
            }else{
                password.setError(null);
            }

            if(TextUtils.isEmpty(surnameStr)) {
                surname.setError("Questo campo non può essere vuoto");
                errors++;
            }else{
                password.setError(null);
            }

            if(TextUtils.isEmpty(passwordStr)) {
                password.setError("Questo campo non può essere vuoto");
                errors++;
            }else{
                password.setError(null);
            }

            //Da scommentare quando finiamo, per questioni di test non ci serve ora
            /*Check if password is long enough
            if(passwordStr.length() >= 8) {
                password.setError("La password deve essere più lunga di 8 caratteri");
                return;
            }*/

            if(TextUtils.isEmpty(confirmPasswordStr)) {
                confirmPassword.setError("Questo campo non può essere vuoto");
                errors++;
            }

            /*ConfirmPassword doesn't match with the first password*/
            if(!confirmPasswordStr.equals(passwordStr)) {
                confirmPassword.setError("Le password non corrispondono");
                errors++;
            }


            if(TextUtils.isEmpty(phoneStr)) {
                phone.setError("Questo campo non può essere vuoto");
            }

            /*Check if the input is a telephone number (10 digits) and only digits*/
            if(TextUtils.isDigitsOnly(phoneStr) || phoneStr.length() == 10){
                phone.setError("Inserire un numero di telefono valido");
            }

            if(errors == 0){
                /*Inserimento utente*/
                UserStudente newUser = new UserStudente(emailStr, nameStr, surnameStr, passwordStr, phoneStr);
                factory.getUserList().add(newUser);
                Toast.makeText(SignUp.this,"Registrazione avvenuta con successo!", Toast.LENGTH_LONG).show();
                Intent registrationSuccess = new Intent(SignUp.this,
                        LoginPage.class);
                startActivity(registrationSuccess);
            }else{
                errors = 0;
            }

            }
        });
        }

    }
