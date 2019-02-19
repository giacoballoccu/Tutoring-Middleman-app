package com.example.giaco.gerproject;

import android.content.ClipData;
import android.content.Intent;
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
    BuyPackagesFragment buyPackages;
    DashBoardFragment dashboard;
    ContactUsFragment contactUs;
    MyReservationsFragment myReservations;
    PersonalPageFragment personalPage;

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

        /*Recupero utente*/
        Intent intent = getIntent();
        UserStudente loggedUser = intent.getParcelableExtra("actualUser");
        /*Possiamo recuperare tutti i dati che vogliamo, ora bisogna passare l'utente ai fragment*/
        String nome = loggedUser.getName();

        Bundle bundle = new Bundle();
        bundle.putParcelable("actualUser", loggedUser);
        // Moving bundle to every fragment present in our application after the loggin
        dashboard = new DashBoardFragment();
        dashboard.setArguments(bundle);

        buyPackages = new BuyPackagesFragment();
        buyPackages.setArguments(bundle);

        contactUs = new ContactUsFragment();
        contactUs.setArguments(bundle);

        myReservations = new MyReservationsFragment();
        myReservations.setArguments(bundle);

        personalPage = new PersonalPageFragment();
        personalPage.setArguments(bundle);

        /*Device rotation handler*/
        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new DashBoardFragment()).addToBackStack(null).commit();
            navigationView.setCheckedItem(R.id.nav_dashboard);
        }
    }

    /*Menu handler*/
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch(menuItem.getItemId()){
            case R.id.nav_personalpage:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, personalPage, "Personal Page").commit();
                break;
            case R.id.nav_dashboard:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, dashboard, "DashBoard").commit();
                break;
            case R.id.nav_buyPackages:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, buyPackages, "Buy Packages").commit();
                break;
            case R.id.nav_myReservations:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, myReservations, "My Reservations").commit();
                break;
            case R.id.nav_contactUs:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, contactUs, "Contact Us").commit();
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /*ORA COME ORA è DISABILITATO SOLO IL BACK DOPO IL LOGGIN(grazie a .addToBackStack(null)), COMPLETAMENTE DISABILITATO DOVREMMO PERMETTERE DI TORNARE ALL'ALTRO FRAME*/
    @Override
    public void onBackPressed() {

    }
}
