package com.example.giaco.gerproject;

import android.content.Context;
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

import java.util.ArrayList;

public class ReviewsFragment extends Fragment {

    LinearLayout mparent;
    LayoutInflater layoutInflater;
    View myView;
    UserTutor chosenTutor;

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
            ArrayList<Feedback> feedbackList = FeedbackFactory.getInstance().getFeedbackByTutorMail(chosenTutor.getEmail());

            /*Parte singola*/
            mparent = view.findViewById(R.id.parentview_feedback);
            layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            myView = layoutInflater.inflate(R.layout.feedback_static, null, false);
            mparent.addView(myView);
            for(Feedback f : feedbackList) {
                myView = layoutInflater.inflate(R.layout.single_feedback, null, false);
                mparent.addView(myView);
                updateSingleReview(f, myView);
        }


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

            autoreNome.setText(autore.getEmail());
            descrizione.setText(f.getDescrizione());
            avatarReview.setImageDrawable(autore.getImage());
        }
    }
