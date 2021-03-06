package com.example.giaco.gerproject;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
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
    PersonalPageFragment personalPage;
    ReviewsFragment reviewFragment;
    AgendaTutorFragment agendaFragment;
    ImageView avatarMenu;
    TextView nomeCognome;
    ChatFragment chatFragment;
    ConversazioniFragment conversazioniFragment;
    protected boolean flagTutor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        View hView = navigationView.getHeaderView(0);
        avatarMenu = (ImageView) hView.findViewById(R.id.avatarMenu);
        nomeCognome = (TextView) hView.findViewById(R.id.nomeMenu);

        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        /*Recupero utente*/
        Intent intent = getIntent();
        String loggedUserMail = intent.getStringExtra("actualUserMail");

        UserStudenteFactory factory = UserStudenteFactory.getInstance();
        UserTutorFactory factoryT = UserTutorFactory.getInstance();
        if (factory.isEmailInUserList(loggedUserMail))
            setTutorFlag(false);
        else setTutorFlag(true);

        /*Possiamo recuperare tutti i dati che vogliamo, ora bisogna passare l'utente ai fragment*/
        Bundle bundle = new Bundle();
        bundle.putString("actualUserMail", loggedUserMail);
        if (getTutorFlag() != true)
            bundle.putInt("tFlag", 0);
        else
            bundle.putInt("tFlag", 1);

        if (!flagTutor) {
            navigationView.getMenu().setGroupVisible(R.id.studente, true);
        } else {
            navigationView.getMenu().setGroupVisible(R.id.tutor, true);
        }

        // Moving bundle to every fragment present in our application after the login

        buyPackages = new BuyPackagesFragment();
        buyPackages.setArguments(bundle);

        buyPackages = new BuyPackagesFragment();
        buyPackages.setArguments(bundle);

        contactUs = new ContactUsFragment();
        contactUs.setArguments(bundle);

        myReservations = new MyReservationsFragment();
        myReservations.setArguments(bundle);

        personalPage = new PersonalPageFragment();
        personalPage.setArguments(bundle);

        dashboard = new DashBoardFragment();
        bundle.putString("materiaSelezionata", null);
        bundle.putBoolean("spinnerFlag", false);
        dashboard.setArguments(bundle);

        if (getTutorFlag() == true) {
            Bundle bundle1 = new Bundle();
            bundle1.putString("chosenTutor", loggedUserMail);
            reviewFragment = new ReviewsFragment();
            reviewFragment.setArguments(bundle1);
        }
        agendaFragment = new AgendaTutorFragment();
        agendaFragment.setArguments(bundle);

        conversazioniFragment = new ConversazioniFragment();
        conversazioniFragment.setArguments(bundle);

        if (getTutorFlag() == false) {
            UserStudente loggedUser = factory.getUserByEmail(loggedUserMail);
            avatarMenu.setImageDrawable(loggedUser.getImage());
            nomeCognome.setText("" + loggedUser.getName() + " " + loggedUser.getSurname() + "");
            /*Device rotation handler*/
            if (savedInstanceState == null) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, dashboard).commit();
                navigationView.setCheckedItem(R.id.nav_dashboardS);
            }
        } else {
            UserTutor loggedUser = factoryT.getUserByEmail(loggedUserMail);
            avatarMenu.setImageDrawable(loggedUser.getImage());
            nomeCognome.setText("" + loggedUser.getName() + " " + loggedUser.getSurname() + "");
            /*Device rotation handler*/
            if (savedInstanceState == null) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, personalPage).commit();
                navigationView.setCheckedItem(R.id.nav_personalpageT);
            }
        }


    }

    /*Menu handler*/
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            /*Handler menustudente*/
            case R.id.nav_personalpageS:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, personalPage, "Personal Page").addToBackStack("fragment_dashboard").commit();
                break;
            case R.id.nav_dashboardS:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, dashboard, "DashBoard").addToBackStack("fragment_dashboard").commit();
                break;
            case R.id.nav_buyPackagesS:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, buyPackages, "Buy Packages").addToBackStack("fragment_dashboard").commit();
                break;
            case R.id.nav_myReservationsS:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, myReservations, "My Reservations").addToBackStack("fragment_dashboard").commit();
                break;
            case R.id.nav_logoutS:
                FragmentManager fm = getSupportFragmentManager();

                for(int i = 0; i < fm.getBackStackEntryCount(); ++i) {
                    fm.popBackStack();
                }

                Intent loggoutS = new Intent(MainActivity.this,
                        LoginPage.class);
                startActivity(loggoutS);
                break;
            case R.id.nav_convS:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, conversazioniFragment, "My conversations").addToBackStack("fragment_dashboard").commit();
                break;
            /*Handler menu tutor*/
            case R.id.nav_personalpageT:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, personalPage, "Personal Page").addToBackStack("fragment_dashboard").commit();
                break;
            case R.id.nav_myReservationsT:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, agendaFragment, "My Agenda").addToBackStack("fragment_dashboard").commit();
                break;
            case R.id.nav_feedbacksT:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, reviewFragment, "My Reviews").addToBackStack("fragment_dashboard").commit();
                break;
            case R.id.nav_logoutT:
                FragmentManager fm1 = getSupportFragmentManager();

                for(int i = 0; i < fm1.getBackStackEntryCount(); ++i) {
                    fm1.popBackStack();
                }

                Intent loggoutT = new Intent(MainActivity.this,
                        LoginPage.class);
                startActivity(loggoutT);
                break;

            case R.id.nav_convT:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, conversazioniFragment, "My conversations").addToBackStack("fragment_dashboard").commit();
                break;

        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    protected void setTutorFlag(boolean bool) {
        if (bool == true)
            this.flagTutor = true;
        else this.flagTutor = false;
    }

    protected boolean getTutorFlag() {
        return this.flagTutor;
    }
}
