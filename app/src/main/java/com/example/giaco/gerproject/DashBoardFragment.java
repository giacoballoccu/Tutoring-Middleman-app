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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.giaco.gerproject.Classes.Feedback;
import com.example.giaco.gerproject.Classes.FeedbackFactory;
import com.example.giaco.gerproject.Classes.UserStudente;
import com.example.giaco.gerproject.Classes.UserStudenteFactory;
import com.example.giaco.gerproject.Classes.UserTutor;
import com.example.giaco.gerproject.Classes.UserTutorFactory;

import java.util.ArrayList;

public class DashBoardFragment extends Fragment {
    LinearLayout mparent;
    LayoutInflater layoutInflater;
    View myView;
    BookFragment book;
    ImageButton b;
    ArrayList<Feedback> feedbackList;
    UserStudente loggedStudente;
    UserTutor loggedTutor;
    FeedbackFactory feedbackFactory = FeedbackFactory.getInstance();
    UserTutorFactory userTutorFactory = UserTutorFactory.getInstance();
    ArrayList<UserTutor> tutorList = userTutorFactory.getUserList();
    boolean flagTutor = false;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dashboard, container, false);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Bacheca");

        if (getArguments() != null) {
            if(getArguments().getInt("flag") == 0) {
                flagTutor = false;
                String emailLoggedUser = getArguments().getString("email");
                loggedStudente = UserStudenteFactory.getInstance().getUserByEmail(emailLoggedUser);
            }
            else{
                flagTutor = true;
                String emailLoggedUser = getArguments().getString("email");
                loggedTutor = UserTutorFactory.getInstance().getUserByEmail(emailLoggedUser);
            }
        }
        mparent = view.findViewById(R.id.mparent);
        layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);


        for(int i = 0; i < tutorList.size() ; i++) {
            switch (tutorList.get(i).getMateria()){
                case "Fisica":
                    myView = layoutInflater.inflate(R.layout.postitgreen, null, false);
                    mparent.addView(myView);
                    b = myView.findViewById(R.id.postitverde);
                    updatePostit(tutorList.get(i), myView);
                    break;
                case "Informatica":
                    myView = layoutInflater.inflate(R.layout.postitred, null, false);
                    mparent.addView(myView);
                    b = myView.findViewById(R.id.postitrosso);
                    updatePostit(tutorList.get(i), myView);
                    break;
                case "Matematica":
                    myView = layoutInflater.inflate(R.layout.postitblue, null, false);
                    mparent.addView(myView);
                    b = myView.findViewById(R.id.postitblu);
                    updatePostit(tutorList.get(i), myView);
                    break;
            }

        }

    }

    public void updatePostit (UserTutor tutor, View myView){

        ImageView avatar;
        TextView nomeCognome, materia;
        ImageView feedback;
        int feedback_intero;
        Drawable zerostelle, unastella, duestelle, trestelle,quattrostelle, cinquestelle;
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
        if(feedbackList.size() > 0) {
            feedback_intero = feedbackFactory.getVotoTotaleMedio(feedbackList);

            switch (feedback_intero){
                case 0 :
                    feedback.setImageDrawable(zerostelle);
                    break;
                case 1 :
                    feedback.setImageDrawable(unastella);
                    break;
                case 2 :
                    feedback.setImageDrawable(duestelle);
                    break;
                case 3 :
                    feedback.setImageDrawable(trestelle);
                    break;
                case 4 :
                    feedback.setImageDrawable(quattrostelle);
                    break;
                case 5 :
                    feedback.setImageDrawable(cinquestelle);
                    break;
         }
        }
        //if(flagTutor == false)
        //    setOnClick(b,tutor.getEmail(), loggedStudente.getEmail());
        //else
         //   setOnClick(b,tutor.getEmail(), loggedTutor.getEmail());

    }

    private void setOnClick(final ImageButton btn, final String emailTutor, final String emailUser ){
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
                transaction.replace(R.id.fragment_container , book);
                transaction.commit();
            }
        });
    }


}
