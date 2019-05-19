package com.example.giaco.gerproject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;

import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.example.giaco.gerproject.Classes.Reservation;
import com.example.giaco.gerproject.Classes.ReservationFactory;
import com.example.giaco.gerproject.Classes.UserStudente;
import com.example.giaco.gerproject.Classes.UserStudenteFactory;
import com.example.giaco.gerproject.Classes.UserTutor;
import com.example.giaco.gerproject.Classes.UserTutorFactory;


import java.util.ArrayList;

public class BookFragment extends Fragment {
    UserTutorFactory factory = UserTutorFactory.getInstance();
    //FeedbackFactory feedbackFactory = FeedbackFactory.getInstance();

    ImageView avatarTutor;
    TextView nomeCognome, materia, indirizzo;
    Button prenota, nuovaPrenotazione;
    UserTutor chosenTutor;
    UserStudente loggedUser;
    Spinner spinnerData;
    LayoutInflater layoutInflater;
    RelativeLayout mparent;
    View myView;
    EditAgenda agenda;
    String loggedUserMail;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        loggedUserMail = getArguments().getString("actualUserMail");
        return inflater.inflate(R.layout.prenotaripetizione, container, false);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Prenota una ripetizione");
        if (getArguments() != null) {
            String emailTutor = getArguments().getString("emailTutor");
            String emailUtente = getArguments().getString("emailStudente");
            loggedUser = UserStudenteFactory.getInstance().getUserByEmail(emailUtente);
            chosenTutor = UserTutorFactory.getInstance().getUserByEmail(emailTutor);
            mparent = (RelativeLayout) view.findViewById(R.id.prenotaripetizione_view);
        }

        avatarTutor = (ImageView) view.findViewById(R.id.avatarTutor);
        nomeCognome = (TextView) view.findViewById(R.id.nome_cognome_prenotazione);
        materia = (TextView) view.findViewById(R.id.materia_prenotazione);
        indirizzo = (TextView) view.findViewById(R.id.indirizzo_residenza_prenotazione);
        spinnerData = (Spinner) view.findViewById(R.id.spinnerData);
        prenota = (Button) view.findViewById(R.id.prenota);
        nuovaPrenotazione = (Button) view.findViewById(R.id.nuovaRipetizione);

        ArrayList<String> arrayDate = new ArrayList<String>();
        Context context = ApplicationContextProvider.getContext();


        arrayDate = chosenTutor.getDisponibilitaData();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.support_simple_spinner_dropdown_item, arrayDate);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerData.setAdapter(adapter);

        final String selected = spinnerData.getSelectedItem().toString();

        nomeCognome.setText("" + chosenTutor.getName() + " " + chosenTutor.getSurname() + "");
        materia.setText("" + chosenTutor.getMateria() + "");
        indirizzo.setText("" + chosenTutor.getIndirizzo() + " " + chosenTutor.getCitta() + "");
        avatarTutor.setImageDrawable(chosenTutor.getImage());

        prenota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                myView = layoutInflater.inflate(R.layout.pop_up_prenotazione, null, false);
                //myView.setBackgroundDrawable(getResources().getDrawable(R.drawable.background_trasparency));
                mparent.addView(myView);
                final Button conferma, cancella;
                conferma = (Button) myView.findViewById(R.id.prenotazione_yes);
                cancella = (Button) myView.findViewById(R.id.prenotazione_no);

                conferma.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (Integer.parseInt(loggedUser.getHours()) > 0) {
                            ReservationFactory factoryR = ReservationFactory.getInstance();
                            Reservation r = new Reservation(loggedUser, chosenTutor, selected, chosenTutor.getMateria()); //Nuova reservation
                            factoryR.addReservation(r);
                            chosenTutor.removeData(selected); //Il tutor perde quella disponibilità
                            Toast.makeText(getContext(), "Prenotazione Avvenuta con Successo!", Toast.LENGTH_LONG).show();
                            mparent.removeView(myView);
                            prenota.setVisibility(View.VISIBLE);
                        } else {
                            final Button accetta;
                            mparent.removeView(myView);
                            myView = layoutInflater.inflate(R.layout.prenotazione_fallita, null, false);
                            mparent.addView(myView);
                            accetta = (Button) myView.findViewById(R.id.accetta);
                            accetta.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    mparent.removeView(myView);
                                }
                            });
                        }
                    }
                });

                cancella.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mparent.removeView(myView);
                        prenota.setVisibility(View.VISIBLE);
                    }
                });
            }
        });

        nuovaPrenotazione.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle5 = new Bundle();
                bundle5.putString("actualUserMail", loggedUserMail);
                bundle5.putInt("tFlag", 0);
                agenda = new EditAgenda();
                agenda.setArguments(bundle5);
                FragmentManager fm5 = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction5 = fm5.beginTransaction();
                transaction5.replace(R.id.fragment_container, agenda).addToBackStack("prenotaripetizione");
                transaction5.commit();
            }
        });
    }


    public ArrayList<Integer> sortByDate(ArrayList<String> date, String dateToSearch) {
        ArrayList<Integer> arrayToReturn = new ArrayList<>();

        for (int i = 0; i < date.size(); i++) {
            if (date.equals(dateToSearch)) {
                arrayToReturn.add((Integer) i);
            }
        }
        return arrayToReturn;
    }
}
