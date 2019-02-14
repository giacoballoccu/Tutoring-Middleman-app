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

public class LoginPage extends AppCompatActivity {

    Button login;

    EditText email, password;
    ImageButton infoLink;

    TextView loginError;
    TextView forgottenPass, signUp;
    String emailStr, passwordStr;

    UserStudenteFactory factory = UserStudenteFactory.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        infoLink = (ImageButton) findViewById(R.id.infoLink);

        email = (EditText) findViewById(R.id.emailEditText);
        emailStr = email.getText().toString();

        password = (EditText) findViewById(R.id.passwordEditText);
        passwordStr = password.getText().toString();

        loginError = (TextView) findViewById(R.id.loginError);
        loginError.setVisibility(View.GONE);

        forgottenPass = (TextView) findViewById(R.id.forgottenPass);
        signUp = (TextView) findViewById(R.id.signUp);

        login = (Button) findViewById(R.id.login_button);

        /*Controlli sugli input*/

        if(TextUtils.isEmpty(emailStr)) {
            email.setError("Inserire una mail valida");
            return;
        }
        if(TextUtils.isEmpty(passwordStr)) {
            password.setError("Inserire una password");
            return;
        }

        /*Info*/

        infoLink.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            Intent info = new Intent(LoginPage.this,
                                                    Introduction.class);
                                            //logged.putExtra("nome", username.getText().toString());
                                            startActivity(info);
                                        }
                                    });

        /*SignUp*/

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signUp = new Intent(LoginPage.this,
                        SignUp.class);
                //logged.putExtra("nome", username.getText().toString());
                startActivity(signUp);
            }
        });

        /*Login*/

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(factory.isEmailInUserList(emailStr)){
                    if(factory.getUserByEmail(emailStr).getPassword().equals(passwordStr)){
                        Intent logged = new Intent(LoginPage.this,
                                DashBoard.class);
                        //logged.putExtra("nome", username.getText().toString()); passaggio dati
                        startActivity(logged);
                    }
                }else{
                    loginError.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}
