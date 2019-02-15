package com.example.giaco.gerproject;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class LoginPage extends AppCompatActivity implements View.OnClickListener {

    Button login;

    EditText email, password;
    Button infoLink;

    TextView loginError;
    TextView forgottenPass, signUp;
    String emailStr, passwordStr;

    UserStudenteFactory factory = UserStudenteFactory.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        infoLink = (Button) findViewById(R.id.infoLink);

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

                if (factory.isEmailInUserList(emailStr)) {
                    if (factory.getUserByEmail(emailStr).getPassword().equals(passwordStr)) {
                        Intent logged = new Intent(LoginPage.this,
                                DashBoard.class);
                        //logged.putExtra("nome", username.getText().toString()); passaggio dati
                        startActivity(logged);
                    }
                } else {
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
}

