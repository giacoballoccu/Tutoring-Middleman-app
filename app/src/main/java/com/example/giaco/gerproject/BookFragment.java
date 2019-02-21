package com.example.giaco.gerproject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
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

import com.example.giaco.gerproject.Classes.FeedbackFactory;
import com.example.giaco.gerproject.Classes.Reservation;
import com.example.giaco.gerproject.Classes.ReservationFactory;
import com.example.giaco.gerproject.Classes.UserStudenteFactory;
import com.example.giaco.gerproject.Classes.UserTutor;
import com.example.giaco.gerproject.Classes.UserTutorFactory;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class BookFragment extends Fragment {
    UserTutorFactory factory = UserTutorFactory.getInstance();
    //FeedbackFactory feedbackFactory = FeedbackFactory.getInstance();

    ImageView avatarTutor;
    TextView nomeCognome, materia, indirizzo, data_prenotazione;
    UserTutor chosenTutor;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.prenotaripetizione, container, false);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Prenota una ripetizione");
        if (getArguments() != null) {
            String email =  getArguments().getString("email");
            chosenTutor = UserTutorFactory.getInstance().getUserByEmail(email);
            //chosenTutor.setFeedbacks(feedbackFactory.getFeedbackByTutorMail(email));
            //chosenTutor.setVotoTotaleMedio(feedbackFactory.getVotoTotaleMedio(feedbackFactory.getFeedbackByTutorMail(email)));
        }

        avatarTutor = (ImageView) view.findViewById(R.id.avatarTutor);
        nomeCognome = (TextView) view.findViewById(R.id.nome_cognome_prenotazione);
        materia = (TextView) view.findViewById(R.id.materia_prenotazione);
        indirizzo = (TextView) view.findViewById(R.id.indirizzo_residenza_prenotazione);

        nomeCognome.setText("" + chosenTutor.getName() + " " + chosenTutor.getSurname() + "");
        materia.setText("" + chosenTutor.getMateria() + "");
        indirizzo.setText("" + chosenTutor.getIndirizzo() + " " + chosenTutor.getCitta() + "");
        avatarTutor.setImageDrawable(resize(chosenTutor.getImage()));


    }

    private Drawable resize(Drawable image) {
        Bitmap b = ((BitmapDrawable)image).getBitmap();
        Bitmap bitmapResized = Bitmap.createScaledBitmap(b, 100, 100, false);
        Context context = ApplicationContextProvider.getContext();
        return new BitmapDrawable(context.getResources(), bitmapResized);
    }

}
