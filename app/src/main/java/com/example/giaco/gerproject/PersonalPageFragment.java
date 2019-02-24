package com.example.giaco.gerproject;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.giaco.gerproject.Classes.FeedbackFactory;
import com.example.giaco.gerproject.Classes.User;
import com.example.giaco.gerproject.Classes.UserStudente;
import com.example.giaco.gerproject.Classes.UserStudenteFactory;
import com.example.giaco.gerproject.Classes.UserTutor;
import com.example.giaco.gerproject.Classes.UserTutorFactory;

public class PersonalPageFragment extends Fragment implements View.OnClickListener {
    private UserStudente loggedStudente;
    private UserTutor loggedTutor;
    Bundle bundle = new Bundle();
    LinearLayout dparent;
    ImageView userImg, stelline;
    String loggedUserMail;
    TextView userName, hours, materia, orari, orario, orariAgenda;
    Button recharge, editAgenda, recensioni;
    Button editProfile;
    View myView;
    LayoutInflater layoutInflater;
    private boolean flagTutor = false;
    EditAgenda agenda;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        if (getArguments() != null) {
            if(getArguments().getInt("flag") == 0){ //Studente
                loggedUserMail = getArguments().getString("actualUserMail");
                loggedStudente = UserStudenteFactory.getInstance().getUserByEmail(loggedUserMail);
                setTutorFlag(loggedStudente);
            }
            else{   //Tutor
                loggedUserMail = getArguments().getString("actualUserMail");
                loggedTutor = UserTutorFactory.getInstance().getUserByEmail(loggedUserMail);
                setTutorFlag(loggedTutor);
                //bundle = savedInstanceState;
            }
        }
        if (getTutorFlag() == true) {
            bundle.putString("chosenTutor", loggedUserMail);
            return inflater.inflate(R.layout.fragment_tutor_personal_page, container, false);
        }
        else
            return inflater.inflate(R.layout.fragment_personal_page, container, false);

    }


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // do your variables initialisations here except Views!!!
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rechargeButton: {
                BuyPackagesFragment clickedFragment = new BuyPackagesFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content, clickedFragment);
                fragmentTransaction.commit();
                break;
            }
            case R.id.editButton: {
                EditProfileFragment clickedFragment = new EditProfileFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content, clickedFragment);
                fragmentTransaction.commit();
                break;
            }/*
            case R.id.editAgendaButton: {
                EditProfileFragment clickedFragment = new EditProfileFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content, clickedFragment);
                fragmentTransaction.commit();
                break;
            }*/
            case R.id.editButtonT: {
                EditProfileFragment clickedFragment = new EditProfileFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content, clickedFragment);
                fragmentTransaction.commit();
                break;
            }
            case R.id.feedbackButton: {
                ReviewsFragment clickedFragment = new ReviewsFragment();
                FragmentManager fragmentManager = getFragmentManager();
                clickedFragment.setArguments(bundle);
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, clickedFragment);
                fragmentTransaction.commit();
                break;
            }


        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("I Miei Dati");
        if (getTutorFlag() == false) {  //Dati dello studente
            userName = (TextView) view.findViewById(R.id.username);
            hours = (TextView) view.findViewById(R.id.hours);
            userImg = (ImageView) view.findViewById(R.id.profileImg);
            recharge = (Button) view.findViewById(R.id.rechargeButton);
            editProfile = (Button) view.findViewById(R.id.editButton);
            if (getArguments() != null) {
                    loggedStudente = UserStudenteFactory.getInstance().getUserByEmail(loggedUserMail);
                    /*Dynamic data*/
                    userName.setText("" + loggedStudente.getName() + " " + loggedStudente.getSurname() + "");
                    hours.setText("" + loggedStudente.getHours() + "");
                    userImg.setImageDrawable(resize(loggedStudente.getImage()));
            }
            recharge.setOnClickListener(this);
            editProfile.setOnClickListener(this);
        }
        else{   //Dati del tutor
            userName = (TextView) view.findViewById(R.id.usernameT);
            userImg = (ImageView) view.findViewById(R.id.profileImgT);
            stelline = (ImageView) view.findViewById(R.id.stelline);
            editProfile = (Button) view.findViewById(R.id.editButtonT);
            materia = (TextView) view.findViewById(R.id.materia);
            editAgenda = (Button) view.findViewById(R.id.editAgendaButton);
            setOnClick(editAgenda, loggedUserMail);
            recensioni = (Button) view.findViewById(R.id.feedbackButton);
            orariAgenda = (TextView) view.findViewById(R.id.orari);


            loggedTutor = UserTutorFactory.getInstance().getUserByEmail(loggedUserMail);
            FeedbackFactory feedbackFactory = FeedbackFactory.getInstance();
            loggedTutor.setFeedbacks(feedbackFactory.getFeedbackByTutorMail(loggedTutor.getEmail()));
            loggedTutor.setVotoTotaleMedio(feedbackFactory.getVotoTotaleMedio(feedbackFactory.getFeedbackByTutorMail(loggedTutor.getEmail())));

            /*Dynamic data*/
            layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                userName.setText("" + loggedTutor.getName() + " " + loggedTutor.getSurname() + "");
                materia.setText(""+ loggedTutor.getMateria() +"");
                dparent = view.findViewById(R.id.dparent);

                for(String str : loggedTutor.getDisponibilitaData()){
                    myView = layoutInflater.inflate(R.layout.disponibilita_tutor, null, false);
                    dparent.addView(myView);
                    updateDisponibilita(str, myView);
                }
                userImg.setImageDrawable(resize(loggedTutor.getImage()));

                switch (loggedTutor.getVotoTotaleMedio()){
                    case 0:
                        stelline.setImageDrawable(getResources().getDrawable(R.drawable.zerostelle));
                        break;
                    case 1:
                        stelline.setImageDrawable(getResources().getDrawable(R.drawable.unastella));
                        break;
                    case 2:
                        stelline.setImageDrawable(getResources().getDrawable(R.drawable.duestelle));
                        break;
                    case 3:
                        stelline.setImageDrawable(getResources().getDrawable(R.drawable.trestelle));
                        break;
                    case 4:
                        stelline.setImageDrawable(getResources().getDrawable(R.drawable.quattrostelle));
                        break;
                    case 5:
                        stelline.setImageDrawable(getResources().getDrawable(R.drawable.cinquestelle));
                        break;
                }
            recensioni.setOnClickListener(this);
            //editAgenda.setOnClickListener(this);
            editProfile.setOnClickListener(this);
        }
    }

    private Drawable resize(Drawable image) {
        Bitmap b = ((BitmapDrawable)image).getBitmap();
        Bitmap bitmapResized = Bitmap.createScaledBitmap(b, 150, 150, false);
        Context context = ApplicationContextProvider.getContext();
        return new BitmapDrawable(context.getResources(), bitmapResized);
    }

    protected void setTutorFlag(User usr){
        if (usr instanceof UserStudente)
            this.flagTutor = false;
        else this.flagTutor = true;
    }

    public void updateDisponibilita (String str, View myView){
        TextView text_disponibilita;
        text_disponibilita = myView.findViewById(R.id.disponibilita);
        text_disponibilita.setText("" + str + "");
    }

    protected boolean getTutorFlag(){
        return this.flagTutor;
    }

    private void setOnClick(final Button butt, final String mail) {
        butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("actualUserMail", mail);
                bundle.putInt("tFlag", 1);
                agenda = new EditAgenda();
                agenda.setArguments(bundle);
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();
                transaction.replace(R.id.fragment_container, agenda);
                transaction.commit();
            }
        });
    }
}

