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

import com.example.giaco.gerproject.Classes.Conversation;
import com.example.giaco.gerproject.Classes.ConversationFactory;
import com.example.giaco.gerproject.Classes.MessageFactory;
import com.example.giaco.gerproject.Classes.User;
import com.example.giaco.gerproject.Classes.UserStudenteFactory;
import com.example.giaco.gerproject.Classes.UserTutor;
import com.example.giaco.gerproject.Classes.UserTutorFactory;

public class ConversazioniFragment extends Fragment {
    private String loggedUserMail;
    private boolean tutorFlag;
    LinearLayout coparent;
    LayoutInflater layoutInflater;
    User loggedUser;
    ConversationFactory factoryConversazione;
    TextView nomeCognome;
    ImageView avatarDestinatario;
    //String nomeContatto, cognomeContatto, nomeCognomeContatto;
    View myView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //if (loggedUser.getEmail().equals(factory.getMittente()))
        return inflater.inflate(R.layout.conversazioni_fragment, container, false);
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

        coparent = view.findViewById(R.id.coparent);
        layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (getTutorFlag() != true)
            loggedUser = UserStudenteFactory.getInstance().getUserByEmail(loggedUserMail);  //Studente p
        else
            loggedUser = UserTutorFactory.getInstance().getUserByEmail(loggedUserMail);  //Tutor

        factoryConversazione = ConversationFactory.getInstance();
        getActivity().setTitle("Le mie conversazioni");

        avatarDestinatario = view.findViewById(R.id.avatar_contatto_conversazione);
        nomeCognome = view.findViewById(R.id.contatto_conversazione);

        if (factoryConversazione.checkDestinatario(loggedUserMail) != null) //Se esiste una conversazione con qualcuno
            for (Conversation c : ConversationFactory.getInstance().getConversazioniByMittente(loggedUserMail)) {   //Ciclo tutte le conversazioni
                myView = layoutInflater.inflate(R.layout.messaggio_fragment, null, false);
                coparent.addView(myView);
                update(c, myView, avatarDestinatario, nomeCognome);
            }
        else
            nomeCognome.setText("Nessuna conversazione");    //Nome e cognome del destinatario
    }

    public void update(Conversation conv, View view, ImageView avatarSK, TextView nomeSK){
        String n, c, nc;

        if(getTutorFlag() != true){
            n = UserTutorFactory.getInstance().getUserByEmail(factoryConversazione.checkDestinatario(loggedUserMail)).getName();
            c = UserTutorFactory.getInstance().getUserByEmail(factoryConversazione.checkDestinatario(loggedUserMail)).getSurname();
        }
        else{
            n = UserStudenteFactory.getInstance().getUserByEmail(factoryConversazione.checkDestinatario(loggedUserMail)).getName();
            c = UserStudenteFactory.getInstance().getUserByEmail(factoryConversazione.checkDestinatario(loggedUserMail)).getName();
        }
        nc = n + " " + c;

        nomeSK.setText(nc);


    }

    public void setTutorFlag(boolean bool){
        if(bool == true)
            this.tutorFlag = true;
        else this.tutorFlag = false;
    }

    public boolean getTutorFlag(){
        return this.tutorFlag;
    }

}

