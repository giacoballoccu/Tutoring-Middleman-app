package com.example.giaco.gerproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DashBoard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dash_board);

    }

    @Override
    public void onBackPressed() {
        // The user cannot go back to the previous activity pressing the back button, the only way is the loggout button
    }
}
