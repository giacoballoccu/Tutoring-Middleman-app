package com.example.giaco.gerproject;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.giaco.gerproject.Classes.User;
import com.example.giaco.gerproject.Classes.UserStudente;
import com.example.giaco.gerproject.Classes.UserStudenteFactory;
import com.example.giaco.gerproject.Classes.UserTutor;
import com.example.giaco.gerproject.Classes.UserTutorFactory;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    BuyPackagesFragment buyPackages;
    DashBoardFragment dashboard;
    ContactUsFragment contactUs;
    MyReservationsFragment myReservations;
    PersonalPageFragment personalPage, tutorPersonalPage;
    ImageView avatarMenu;
    TextView nomeCognome;
    protected boolean flagTutor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        View hView =  navigationView.getHeaderView(0);
        avatarMenu = (ImageView) hView.findViewById(R.id.avatarMenu);
        nomeCognome = (TextView) hView.findViewById(R.id.nomeMenu);

        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        /*Recupero utente*/
        Intent intent = getIntent();
        String loggedUserMail = intent.getStringExtra("actualUserMail");
        /*Possiamo recuperare tutti i dati che vogliamo, ora bisogna passare l'utente ai fragment*/


        Bundle bundle = new Bundle();
        bundle.putString("actualUserMail", loggedUserMail);
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


        UserStudenteFactory factory = UserStudenteFactory.getInstance();
        UserTutorFactory factoryT = UserTutorFactory.getInstance();
        if(factory.isEmailInUserList(loggedUserMail))
            setTutorFlag(false);
        else setTutorFlag(true);
       // UserStudenteFactory factoryS = UserStudenteFactory.getInstance();


        //UserTutorFactory factoryT = UserTutorFactory.getInstance();
        //UserTutor loggedTutor = factoryT.getUserByEmail(loggedUserMail);
    if(getTutorFlag() == false){
        UserStudente loggedUser = factory.getUserByEmail(loggedUserMail);
        avatarMenu.setImageDrawable(loggedUser.getImage());
        nomeCognome.setText("" + loggedUser.getName() + " " + loggedUser.getSurname() + "");
        /*Device rotation handler*/
        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new DashBoardFragment()).addToBackStack(null).commit();
            navigationView.setCheckedItem(R.id.nav_dashboard);
        }
    }
    else{
        UserTutor loggedUser = factoryT.getUserByEmail(loggedUserMail);
        avatarMenu.setImageDrawable(loggedUser.getImage());
        nomeCognome.setText("" + loggedUser.getName() + " " + loggedUser.getSurname() + "");
        /*Device rotation handler*/
        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new DashBoardFragment()).addToBackStack(null).commit();
            navigationView.setCheckedItem(R.id.nav_dashboard);
        }
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

    protected void setTutorFlag(boolean bool){
        if (bool == true)
            this.flagTutor = true;
        else this.flagTutor = false;
    }

    protected boolean getTutorFlag(){
        return this.flagTutor;
    }
}
