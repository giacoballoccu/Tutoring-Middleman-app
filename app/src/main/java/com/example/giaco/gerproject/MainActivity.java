package com.example.giaco.gerproject;

import android.content.ClipData;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        /*Device rotation handler*/
        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new DashBoardFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_dashboard);
        }
    }

    /*Menu handler*/
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch(menuItem.getItemId()){
            case R.id.nav_personalpage:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new PersonalPageFragment()).commit();
                break;
            case R.id.nav_dashboard:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new DashBoardFragment()).commit();
                break;
            case R.id.nav_buyPackages:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new BuyPackagesFragment()).commit();
                break;
            case R.id.nav_myReservations:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MyReservationsFragment()).commit();
                break;
            case R.id.nav_contactUs:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ContactUsFragment()).commit();
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /*Disable back button*/
    @Override
    public void onBackPressed() {

    }
}
