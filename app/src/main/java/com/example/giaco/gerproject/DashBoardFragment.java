package com.example.giaco.gerproject;

import android.content.Context;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.giaco.gerproject.Classes.Feedback;
import com.example.giaco.gerproject.Classes.FeedbackFactory;
import com.example.giaco.gerproject.Classes.UserStudente;
import com.example.giaco.gerproject.Classes.UserStudenteFactory;
import com.example.giaco.gerproject.Classes.UserTutor;
import com.example.giaco.gerproject.Classes.UserTutorFactory;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class DashBoardFragment extends Fragment {
    LinearLayout mparent;
    LayoutInflater layoutInflater;
    View myView;
    BookFragment book;
    ImageButton b;
    ArrayList<Feedback> feedbackList;
    String emailLoggedUser;
    FeedbackFactory feedbackFactory = FeedbackFactory.getInstance();
    UserTutorFactory userTutorFactory = UserTutorFactory.getInstance();
    ArrayList<UserTutor> tutorList = userTutorFactory.getUserList();
    Spinner spinner;
    SearchView ricerca;
    private String prova;
    String selected;
    private int MODIFICA = 0;
    private String materiaSelezionata;
    DashBoardFragment dash;
    private boolean flagSpinner = false;
    private boolean fs = false;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dashboard, container, false);
    }

    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Bacheca");


        if (getArguments() != null) {
            emailLoggedUser = getArguments().getString("actualUserMail");
            fs = getArguments().getBoolean("flagSpinner");
            if (getArguments().getString("materiaSelezionata") != null) {
                materiaSelezionata = getArguments().getString("materiaSelezionata");
            } else {
                materiaSelezionata = "DEFAULT";
            }
        }

        mparent = view.findViewById(R.id.mparent);
        layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        myView = layoutInflater.inflate(R.layout.searchview_static, null, false);
        mparent.addView(myView);
        spinner = myView.findViewById(R.id.spinnerFiltro);

        ArrayList<String> arrayMaterie = new ArrayList<String>();
        final Context context = ApplicationContextProvider.getContext();

        arrayMaterie.add(" ");
        arrayMaterie.add("DEFAULT");
        arrayMaterie.add("Matematica");
        arrayMaterie.add("Informatica");
        arrayMaterie.add("Fisica");
        //arrayMaterie.add("Chimica");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), R.layout.support_simple_spinner_dropdown_item, arrayMaterie);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        selected = spinner.getSelectedItem().toString();

        if (materiaSelezionata.equals("DEFAULT")) {
            for (UserTutor t : UserTutorFactory.getInstance().getUserList()) {
                switch (t.getMateria()) {
                    case "Fisica":
                        myView = layoutInflater.inflate(R.layout.postitgreen, null, false);
                        mparent.addView(myView);
                        b = myView.findViewById(R.id.postitverde);
                        updatePostit(t, myView);
                        break;
                    case "Informatica":
                        myView = layoutInflater.inflate(R.layout.postitred, null, false);
                        mparent.addView(myView);
                        b = myView.findViewById(R.id.postitrosso);
                        updatePostit(t, myView);
                        break;
                    case "Matematica":
                        myView = layoutInflater.inflate(R.layout.postitblue, null, false);
                        mparent.addView(myView);
                        b = myView.findViewById(R.id.postitblu);
                        updatePostit(t, myView);
                        break;
                }
            }
        } else {
            for (UserTutor t : UserTutorFactory.getInstance().getUserList()) {
                if (t.getMateria().equals(materiaSelezionata))
                    switch (t.getMateria()) {
                        case "Fisica":
                            myView = layoutInflater.inflate(R.layout.postitgreen, null, false);
                            mparent.addView(myView);
                            b = myView.findViewById(R.id.postitverde);
                            updatePostit(t, myView);
                            break;
                        case "Informatica":
                            myView = layoutInflater.inflate(R.layout.postitred, null, false);
                            mparent.addView(myView);
                            b = myView.findViewById(R.id.postitrosso);
                            updatePostit(t, myView);
                            break;
                        case "Matematica":
                            myView = layoutInflater.inflate(R.layout.postitblue, null, false);
                            mparent.addView(myView);
                            b = myView.findViewById(R.id.postitblu);
                            updatePostit(t, myView);
                            break;
                    }
            }
        }

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                String selectedNew = spinner.getSelectedItem().toString();

                if (fs != false) {
                    Bundle bundle = new Bundle();
                    bundle.putString("actualUserMail", emailLoggedUser);
                    bundle.putString("materiaSelezionata", selectedNew);
                    bundle.putBoolean("flagSpinner", false);
                    dash = new DashBoardFragment();
                    dash.setArguments(bundle);
                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    FragmentTransaction transaction = fm.beginTransaction();
                    transaction.replace(R.id.fragment_container, dash);
                    transaction.commit();
                } else
                    fs = true;
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void updatePostit(UserTutor tutor, View myView) {

        ImageView avatar;
        TextView nomeCognome, materia;
        ImageView feedback;
        int feedback_intero;
        Drawable zerostelle, unastella, duestelle, trestelle, quattrostelle, cinquestelle;
        zerostelle = getResources().getDrawable(R.drawable.zerostelle);
        unastella = getResources().getDrawable(R.drawable.unastella);
        duestelle = getResources().getDrawable(R.drawable.duestelle);
        trestelle = getResources().getDrawable(R.drawable.trestelle);
        quattrostelle = getResources().getDrawable(R.drawable.quattrostelle);
        cinquestelle = getResources().getDrawable(R.drawable.cinquestelle);

        nomeCognome = myView.findViewById(R.id.nomeCognome);
        nomeCognome.setText("" + tutor.getName() + " " + tutor.getSurname() + "");
        avatar = myView.findViewById(R.id.avatarTutor);
        avatar.setImageDrawable(tutor.getImage());
        feedback = myView.findViewById(R.id.feedback_stelle);
        materia = myView.findViewById(R.id.materia);
        materia.setText("" + tutor.getMateria() + "");
        feedbackList = feedbackFactory.getFeedbackByTutorMail(tutor.getEmail());
        if (feedbackList.size() > 0) {
            feedback_intero = feedbackFactory.getVotoTotaleMedio(feedbackList);

            switch (feedback_intero) {
                case 0:
                    feedback.setImageDrawable(zerostelle);
                    break;
                case 1:
                    feedback.setImageDrawable(unastella);
                    break;
                case 2:
                    feedback.setImageDrawable(duestelle);
                    break;
                case 3:
                    feedback.setImageDrawable(trestelle);
                    break;
                case 4:
                    feedback.setImageDrawable(quattrostelle);
                    break;
                case 5:
                    feedback.setImageDrawable(cinquestelle);
                    break;
            }
        }
        setOnClick(b, tutor.getEmail(), emailLoggedUser);
    }

    private void setOnClick(final ImageButton btn, final String emailTutor, final String emailUser) {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("emailTutor", emailTutor);
                bundle.putString("emailStudente", emailUser);
                book = new BookFragment();
                book.setArguments(bundle);
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();
                transaction.replace(R.id.fragment_container, book);
                transaction.commit();
            }
        });
    }
}