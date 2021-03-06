package com.example.giaco.gerproject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.giaco.gerproject.Classes.DisponibilitaFactory;
import com.example.giaco.gerproject.Classes.FeedbackFactory;
import com.example.giaco.gerproject.Classes.User;
import com.example.giaco.gerproject.Classes.UserStudente;
import com.example.giaco.gerproject.Classes.UserStudenteFactory;
import com.example.giaco.gerproject.Classes.UserTutor;
import com.example.giaco.gerproject.Classes.UserTutorFactory;

import java.util.ArrayList;

public class PersonalPageFragment extends Fragment implements View.OnClickListener {
    private UserStudente loggedStudente;
    private UserTutor loggedTutor;
    Bundle bundle = new Bundle();
    Bundle datiCalendario = new Bundle();
    LinearLayout dparent;
    ImageView userImg, stelline;
    String loggedUserMail;
    TextView userName, hours, materia, orari, orario, orariAgenda;
    Button recharge, recensioni;
    FloatingActionButton add_orario;
    ImageButton editProfile;
    EditProfileFragment editProfileFS, editProfileFT;
    ImageButton editAgenda;
    View myView;
    LayoutInflater layoutInflater;
    BuyPackagesFragment buyPackages;
    ReviewsFragment review;
    private boolean flagTutor = false;
    ArrayList<String> listaDiDate;
    Button backButton;
    ImageButton delete_orario;
    LinearLayout mparent;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        if (getArguments() != null) {
            if (getArguments().getString("flagAggiunta") == "ok") {
                //DisponibilitaFactory.getInstance().addDisponibilita(getArguments().getInt("giornoN"), getArguments().getInt("meseN"), getArguments().getInt("annoN"));
                listaDiDate = DisponibilitaFactory.getInstance().addDisponibilita(getArguments().getInt("giornoN"), getArguments().getInt("meseN"), getArguments().getInt("annoN"), getArguments().getInt("oraiN"), getArguments().getInt("oraF"), getArguments().getInt("giornoSettimana"));

            }
            if (getArguments().getInt("tFlag") == 0) { //Studente
                loggedUserMail = getArguments().getString("actualUserMail");
                loggedStudente = UserStudenteFactory.getInstance().getUserByEmail(loggedUserMail);
                setTutorFlag(loggedStudente);
            } else {   //Tutor
                loggedUserMail = getArguments().getString("actualUserMail");
                loggedTutor = UserTutorFactory.getInstance().getUserByEmail(loggedUserMail);
                setTutorFlag(loggedTutor);
                //bundle = savedInstanceState;
            }
        }
        if (getTutorFlag() == true) {
            bundle.putString("chosenTutor", loggedUserMail);
            return inflater.inflate(R.layout.fragment_tutor_personal_page, container, false);
        } else {

            return inflater.inflate(R.layout.fragment_personal_page, container, false);
        }
    }


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("I Miei Dati");



        if (getTutorFlag() == false) {  //Dati dello studente

            backButton = view.findViewById(R.id.backbutton_personal_page);

            backButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getFragmentManager().popBackStackImmediate();
                }
            });

            userName = (TextView) view.findViewById(R.id.username);
            hours = (TextView) view.findViewById(R.id.hours);
            userImg = (ImageView) view.findViewById(R.id.profileImg);
            recharge = (Button) view.findViewById(R.id.rechargeButton);
            editProfile = (ImageButton) view.findViewById(R.id.editButton);
            if (getArguments() != null) {
                loggedStudente = UserStudenteFactory.getInstance().getUserByEmail(loggedUserMail);
                /*Dynamic data*/
                userName.setText("" + loggedStudente.getName() + " " + loggedStudente.getSurname() + "");
                hours.setText("" + loggedStudente.getHours() + "");
                userImg.setImageDrawable(resize(loggedStudente.getImage()));
            }
            recharge.setOnClickListener(this);


        } else {   //Dati del tutor
            userName = (TextView) view.findViewById(R.id.usernameT);
            userImg = (ImageView) view.findViewById(R.id.profileImgT);
            stelline = (ImageView) view.findViewById(R.id.stelline);
            if (getArguments() != null && getArguments().getString("flagAggiunta") != "ok") {
                listaDiDate = DisponibilitaFactory.getInstance().getDate();
                loggedTutor.setDisponibilitaData(listaDiDate);
            } else
                loggedTutor.setDisponibilitaData(listaDiDate);
            materia = (TextView) view.findViewById(R.id.materia);
            orariAgenda = (TextView) view.findViewById(R.id.orari);
            add_orario = view.findViewById(R.id.add_orario);

            recensioni = (Button) view.findViewById(R.id.feedbackButton);
            editProfile = (ImageButton) view.findViewById(R.id.editButtonT);

            loggedTutor = UserTutorFactory.getInstance().getUserByEmail(loggedUserMail);
            FeedbackFactory feedbackFactory = FeedbackFactory.getInstance();
            loggedTutor.setFeedbacks(feedbackFactory.getFeedbackByTutorMail(loggedTutor.getEmail()));
            loggedTutor.setVotoTotaleMedio(feedbackFactory.getVotoTotaleMedio(feedbackFactory.getFeedbackByTutorMail(loggedTutor.getEmail())));

            /*Dynamic data*/
            layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            userName.setText("" + loggedTutor.getName() + " " + loggedTutor.getSurname() + "");
            materia.setText("" + loggedTutor.getMateria() + "");
            dparent = view.findViewById(R.id.dparent);
            mparent = view.findViewById(R.id.mparent12);


            for (String str : loggedTutor.getDisponibilitaData()) {
                myView = layoutInflater.inflate(R.layout.disponibilita_tutor, null, false);
                dparent.addView(myView);
                updateDisponibilita(str, myView);

                delete_orario = myView.findViewById(R.id.delete_orario);
                delete_orario.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mparent.removeView(myView);
                        add_orario.hide();
                        layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        myView = layoutInflater.inflate(R.layout.pop_up_rimuovi_orario, null, false);
                        mparent.addView(myView);
                        final Button conferma, cancella;
                        conferma = (Button) myView.findViewById(R.id.orario_yes);
                        cancella = (Button) myView.findViewById(R.id.orario_no);

                        conferma.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast toast=Toast.makeText(getActivity().getApplicationContext(),"Orario rimosso",Toast.LENGTH_SHORT);
                                toast.show();
                                mparent.removeView(myView);
                                add_orario.show();
                            }
                        });

                        cancella.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                Toast toast=Toast.makeText(getActivity().getApplicationContext(),"Azione annullata",Toast.LENGTH_SHORT);
                                toast.show();
                                mparent.removeView(myView);
                                add_orario.show();
                            }
                        });

                    }
                });
            }
            userImg.setImageDrawable(resize(loggedTutor.getImage()));
            switch (loggedTutor.getVotoTotaleMedio()) {
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
            add_orario.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putString("actualUserMail", loggedUserMail);
                    if (!getTutorFlag())
                        bundle.putInt("tFlag", 0);
                    else
                        bundle.putInt("tFlag", 1);

                    EditAgenda editAgenda = new EditAgenda();
                    editAgenda.setArguments(bundle);

                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    FragmentTransaction transaction = fm.beginTransaction();
                    transaction.replace(R.id.fragment_container, editAgenda).addToBackStack("fragment_tutor_personal_page");
                    transaction.commit();
                }
            });

        }
        editProfile.setOnClickListener(this);


    }

    private Drawable resize(Drawable image) {
        Bitmap b = ((BitmapDrawable) image).getBitmap();
        Bitmap bitmapResized = Bitmap.createScaledBitmap(b, 150, 150, false);
        Context context = ApplicationContextProvider.getContext();
        return new BitmapDrawable(context.getResources(), bitmapResized);
    }

    protected void setTutorFlag(User usr) {
        if (usr instanceof UserStudente)
            this.flagTutor = false;
        else this.flagTutor = true;
    }

    public void updateDisponibilita(String str, View myView) {
        TextView text_disponibilita;
        text_disponibilita = myView.findViewById(R.id.disponibilita);
        text_disponibilita.setText("" + str + "");
    }

    protected boolean getTutorFlag() {
        return this.flagTutor;
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.editButtonT:
                Bundle bundle = new Bundle();
                bundle.putString("actualUserMail", loggedUserMail);
                bundle.putInt("tFlag", 1);
                editProfileFT = new EditProfileFragment();
                editProfileFT.setArguments(bundle);
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();
                transaction.replace(R.id.fragment_container, editProfileFT).addToBackStack("fragment_personal_page");
                transaction.commit();
                break;

            case R.id.editButton:
                Bundle bundle2 = new Bundle();
                bundle2.putString("actualUserMail", loggedUserMail);
                bundle2.putInt("tFlag", 0);
                editProfileFS = new EditProfileFragment();
                editProfileFS.setArguments(bundle2);
                FragmentManager fm2 = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction2 = fm2.beginTransaction();
                transaction2.replace(R.id.fragment_container, editProfileFS).addToBackStack("fragment_personal_page");
                transaction2.commit();
                break;

            case R.id.rechargeButton:
                Bundle bundle3 = new Bundle();
                bundle3.putString("actualUserMail", loggedUserMail);
                bundle3.putInt("tFlag", 0);
                buyPackages = new BuyPackagesFragment();
                buyPackages.setArguments(bundle3);
                FragmentManager fm3 = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction3 = fm3.beginTransaction();
                transaction3.replace(R.id.fragment_container, buyPackages).addToBackStack("fragment_personal_page");
                transaction3.commit();
                break;

            case R.id.feedbackButton:
                Bundle bundle4 = new Bundle();
                bundle4.putString("actualUserMail", loggedUserMail);
                bundle4.putString("chosenTutor", loggedUserMail);
                bundle4.putInt("tFlag", 1);
                review = new ReviewsFragment();
                review.setArguments(bundle4);
                FragmentManager fm4 = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction4 = fm4.beginTransaction();
                transaction4.replace(R.id.fragment_container, review).addToBackStack("fragment_personal_page");
                transaction4.commit();
                break;


        }
    }


}

