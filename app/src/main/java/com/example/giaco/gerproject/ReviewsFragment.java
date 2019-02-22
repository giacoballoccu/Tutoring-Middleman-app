package com.example.giaco.gerproject;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.giaco.gerproject.Classes.Feedback;
import com.example.giaco.gerproject.Classes.FeedbackFactory;
import com.example.giaco.gerproject.Classes.UserStudente;
import com.example.giaco.gerproject.Classes.UserStudenteFactory;
import com.example.giaco.gerproject.Classes.UserTutor;
import com.example.giaco.gerproject.Classes.UserTutorFactory;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ReviewsFragment extends Fragment {

    LinearLayout mparent;
    LayoutInflater layoutInflater;
    View singleReview, statsReview;
    UserTutor chosenTutor;
    FeedbackFactory feedbackFactory;
    ArrayList<Feedback> feedbacks;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.feedback, container, false);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Recensioni");

        if (getArguments() != null) {
            String emailTutor = getArguments().getString("chosenTutor");
            chosenTutor = UserTutorFactory.getInstance().getUserByEmail(emailTutor);
            feedbackFactory = FeedbackFactory.getInstance();
            feedbacks = feedbackFactory.getFeedbackByTutorMail(chosenTutor.getEmail());

            /*Parte singola*/
            mparent = view.findViewById(R.id.parentview_feedback);
            layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            statsReview = layoutInflater.inflate(R.layout.feedback_static, null, false);
            mparent.addView(statsReview);
            updateStatsReview(feedbackFactory, statsReview);
            for(Feedback f : feedbacks) {
               singleReview = layoutInflater.inflate(R.layout.single_feedback, null, false);
                mparent.addView(singleReview);
                updateSingleReview(f, singleReview);
        }


        }
        }

        public void updateStatsReview(FeedbackFactory f, View statsReview){
            TextView nomeTutor, votoChiarezza, votoDisponibilita, votoCompetenza;
            ImageView stellineMedie;

            /*Stelle*/
            Drawable zerostelle, unastella, duestelle, trestelle,quattrostelle, cinquestelle;
            zerostelle = getResources().getDrawable(R.drawable.zerostelle);
            unastella = getResources().getDrawable(R.drawable.unastella);
            duestelle = getResources().getDrawable(R.drawable.duestelle);
            trestelle = getResources().getDrawable(R.drawable.trestelle);
            quattrostelle = getResources().getDrawable(R.drawable.quattrostelle);
            cinquestelle = getResources().getDrawable(R.drawable.cinquestelle);
            /*View*/
            nomeTutor = (TextView) statsReview.findViewById(R.id.nomecognomefeedbackstatic);
            votoChiarezza = (TextView) statsReview.findViewById(R.id.votochiarezza);
            votoCompetenza = (TextView) statsReview.findViewById(R.id.votocompetenza);
            votoDisponibilita = (TextView) statsReview.findViewById(R.id.votodisponibilita);
            stellineMedie = (ImageView) statsReview.findViewById(R.id.stellineSingolaReview);

            /*Dati dinamici*/
            nomeTutor.setText("" + chosenTutor.getName() + " " + chosenTutor.getSurname() + "");
            votoChiarezza.setText(String.valueOf(f.getVotoChiarezzaMedio(feedbacks)));
            votoCompetenza.setText(String.valueOf(f.getVotoCompetanzaMedio(feedbacks)));
            votoDisponibilita.setText(String.valueOf(f.getVotoDisponibiltaMedio(feedbacks)));
            int votoTotaleMedio = f.getVotoTotaleMedio(feedbacks);

                switch (votoTotaleMedio){
                    case 0 :
                        stellineMedie.setImageDrawable(zerostelle);
                        break;
                    case 1 :
                        stellineMedie.setImageDrawable(unastella);
                        break;
                    case 2 :
                       stellineMedie.setImageDrawable(duestelle);
                        break;
                    case 3 :
                        stellineMedie.setImageDrawable(trestelle);
                        break;
                    case 4 :
                        stellineMedie.setImageDrawable(quattrostelle);
                        break;
                    case 5 :
                        stellineMedie.setImageDrawable(cinquestelle);
                        break;
                }

        }

        public void updateSingleReview(Feedback f, View myView){
            TextView autoreNome, descrizione;
            ImageView stelline, avatarReview;
            UserStudenteFactory factory = UserStudenteFactory.getInstance();
            UserStudente autore = factory.getUserByEmail(f.getAutore());

            autoreNome = (TextView) myView.findViewById(R.id.autoreReview);
            descrizione = (TextView) myView.findViewById(R.id.descrizioneReview);
            stelline = (ImageView) myView.findViewById(R.id.stellineSingolaReview);
            avatarReview = (ImageView) myView.findViewById(R.id.avatarAutore);

            autoreNome.setText("" + autore.getName() + " " + autore.getSurname() + "");
            descrizione.setText(f.getDescrizione());
            avatarReview.setImageDrawable(autore.getImage());
        }
    }
