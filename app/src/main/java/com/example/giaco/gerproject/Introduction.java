package com.example.giaco.gerproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Introduction extends AppCompatActivity {

    Button hocapito;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.introduction_page);


        hocapito = (Button) findViewById(R.id.hocapito);

        hocapito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent LoginPage = new Intent(Introduction.this,
                        LoginPage.class);
                startActivity(LoginPage);
            }
        });
    }
}