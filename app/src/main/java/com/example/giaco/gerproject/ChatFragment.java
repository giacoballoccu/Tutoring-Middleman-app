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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.giaco.gerproject.Classes.ConversationFactory;
import com.example.giaco.gerproject.Classes.Message;
import com.example.giaco.gerproject.Classes.MessageFactory;
import com.example.giaco.gerproject.Classes.User;
import com.example.giaco.gerproject.Classes.UserStudenteFactory;
import com.example.giaco.gerproject.Classes.UserTutor;
import com.example.giaco.gerproject.Classes.UserTutorFactory;

import java.util.ArrayList;

public class ChatFragment extends Fragment {

    private LinearLayout cparent;
    String loggedUserMail;
    LayoutInflater layoutInflater;
    ImageView avatar;
    private User loggedUser;
    String nome, cognome, tutorNomeCognome, tutorNome, tutorCognome;
    TextView nomeCognome;
    TextView testo;
    ImageView avatarDestinatario;
    MessageFactory factory;
    ConversationFactory factoryConversazione;
    //View cparentR, cparentL;
    LinearLayout cparentR, cparentL;
    boolean tutorFlag;
    View myView;
    private boolean isTutorFlag;
    UserTutorFactory tutorFactory = UserTutorFactory.getInstance();
    UserStudenteFactory studenteFactory = UserStudenteFactory.getInstance();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //if (loggedUser.getEmail().equals(factory.getMittente()))
        return inflater.inflate(R.layout.chat_fragment, container, false);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null) {
            loggedUserMail = getArguments().getString("actualUserMail");
            if (getArguments().getInt("tFlag") == 0)
                setTutorFlag(false);
            else
                setTutorFlag(true);
        }
        cparentR = view.findViewById(R.id.cparentR);
        cparentL = view.findViewById(R.id.cparentL);
        layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (getTutorFlag() != true)
            loggedUser = UserStudenteFactory.getInstance().getUserByEmail(loggedUserMail);  //Studente p
        else
            loggedUser = UserTutorFactory.getInstance().getUserByEmail(loggedUserMail);  //Tutor

        factory = MessageFactory.getInstance();
        factoryConversazione = ConversationFactory.getInstance();
        getActivity().setTitle("Chat");
        nomeCognome = view.findViewById(R.id.contatto);
        avatarDestinatario = view.findViewById(R.id.avatar_destinatario);

        if (factoryConversazione.checkDestinatario(loggedUserMail) != null) {    //Se esiste una conversazione con qualcuno
            if (getTutorFlag() != true) {
                avatarDestinatario.setImageDrawable(UserTutorFactory.getInstance().getUserByEmail(factoryConversazione.checkDestinatario(loggedUserMail)).getImage());
                tutorNome = UserTutorFactory.getInstance().getUserByEmail(factoryConversazione.checkDestinatario(loggedUserMail)).getName();
                tutorCognome = UserTutorFactory.getInstance().getUserByEmail(factoryConversazione.checkDestinatario(loggedUserMail)).getSurname();
            } else {
                avatarDestinatario.setImageDrawable(UserStudenteFactory.getInstance().getUserByEmail(factoryConversazione.checkDestinatario(loggedUserMail)).getImage());
                tutorNome = UserStudenteFactory.getInstance().getUserByEmail(factoryConversazione.checkDestinatario(loggedUserMail)).getName();
                tutorCognome = UserStudenteFactory.getInstance().getUserByEmail(factoryConversazione.checkDestinatario(loggedUserMail)).getSurname();
            }
            tutorNomeCognome = tutorNome + " " + tutorCognome; //Nome e cognome  del destinatario
            nomeCognome.setText(tutorNomeCognome);    //Nome e cognome del destinatario

            for (int i = 0; i < 3; i++) {
                myView = layoutInflater.inflate(R.layout.messaggio_fragment, null, false);
                if (i % 2 == 0) {
                    cparentL.addView(myView);
                    updateL(factoryConversazione.getConversazioneByMittente(loggedUserMail).getMessaggiMit(), myView, i);
                } else {
                    cparentR.addView(myView);
                    updateR(factoryConversazione.getConversazioneByMittente(loggedUserMail).getMessaggiDes(), myView, i);
                }
            }
        } else {
            avatarDestinatario.setVisibility(View.GONE);
            nomeCognome.setText("Nessuna conversazione");    //Nome e cognome del destinatario
        }
    }

    public void updateR(ArrayList<Message> msg, View myView, int id) {
        TextView contenuto, fakeTOP;
        int n = (int) (Math.random() * (msg.size() - 1) + 0);
        contenuto = myView.findViewById(R.id.messaggio);
        fakeTOP = (TextView) myView.findViewById(R.id.fakeMessaggioTOP);
        if (id == 0)
            fakeTOP.setVisibility(View.GONE);
        contenuto.setText(msg.get(n).getContenuto());
        fakeTOP.setText("SONO NASCOSTO IN ALTO");
    }

    public void updateL(ArrayList<Message> msg, View myView, int id) {
        TextView contenuto, fakeTOP;
        int n = (int) (Math.random() * (msg.size() - 1) + 0);
        contenuto = (TextView) myView.findViewById(R.id.messaggio);
        fakeTOP = (TextView) myView.findViewById(R.id.fakeMessaggioTOP);
        if (id == 0)
            fakeTOP.setVisibility(View.GONE);
        contenuto.setText(msg.get(n).getContenuto());
        fakeTOP.setText("SONO NASCOSTO IN ALTO");
    }

    public void setTutorFlag(boolean bool) {
        if (bool == true)
            this.tutorFlag = true;
        else this.tutorFlag = false;
    }

    public boolean getTutorFlag() {
        return this.tutorFlag;
    }

}



