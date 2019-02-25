package com.example.giaco.gerproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.giaco.gerproject.Classes.UserStudente;
import com.example.giaco.gerproject.Classes.UserStudenteFactory;
import com.example.giaco.gerproject.Classes.UserTutor;
import com.example.giaco.gerproject.Classes.UserTutorFactory;

public class LoginPage extends AppCompatActivity implements View.OnClickListener {

    Button login;


    EditText email, password;
    ImageButton infoLink;

    TextView loginError;
    TextView forgottenPass, signUp;
    String emailStr, passwordStr;

    UserStudenteFactory factoryStudente = UserStudenteFactory.getInstance();
    UserTutorFactory factoryTutor = UserTutorFactory.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
        setTitle("Login");

        infoLink = (ImageButton) findViewById(R.id.infoLink);

        email = (EditText) findViewById(R.id.emailEditText);
        password = (EditText) findViewById(R.id.passwordEditText);

        loginError = (TextView) findViewById(R.id.loginError);
        loginError.setVisibility(View.GONE);

        forgottenPass = (TextView) findViewById(R.id.forgottenPass);
        signUp = (TextView) findViewById(R.id.signUp);

        login = (Button) findViewById(R.id.login_button);

        infoLink.setOnClickListener(this);
        signUp.setOnClickListener(this);
        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.infoLink: {
                Intent info = new Intent(LoginPage.this,
                        Introduction.class);
                startActivity(info);
                break;
            }
            case R.id.signUp: {
                Intent signUp = new Intent(LoginPage.this,
                        SignUp.class);
                startActivity(signUp);
                break;
            }
            case R.id.login_button: {
                emailStr = email.getText().toString();
                passwordStr = password.getText().toString();


                if (factoryStudente.isEmailInUserList(emailStr)) {
                    if (factoryStudente.getUserByEmail(emailStr).getPassword().equals(passwordStr)) {
                        UserStudente actualUser = factoryStudente.getUserByEmail(emailStr);

                        Intent successfullyLogged = new Intent(LoginPage.this,MainActivity.class);
                        successfullyLogged.putExtra("actualUserMail", actualUser.getEmail());
                        successfullyLogged.putExtra("tFlag", 0);
                        startActivity(successfullyLogged);
                        finish();
                    }
                } else if(factoryTutor.isEmailInUserList(emailStr)){
                    if (factoryTutor.getUserByEmail(emailStr).getPassword().equals(passwordStr)) {
                        UserTutor actualUser = factoryTutor.getUserByEmail(emailStr);

                        Intent successfullyLogged = new Intent(LoginPage.this, MainActivity.class);
                        successfullyLogged.putExtra("actualUserMail", actualUser.getEmail());
                        successfullyLogged.putExtra("tFlag", 1);
                        startActivity(successfullyLogged);
                        finish();
                    }
                }

                else{
                    if(emailStr.length() == 0 || passwordStr.length() == 0){
                        loginError.setText("Completare tutti i campi");
                        loginError.setVisibility(View.VISIBLE);
                    }else{
                        loginError.setText("Dati errati");
                        loginError.setVisibility(View.VISIBLE);
                    }
                }
                break;
            }
        }
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}

