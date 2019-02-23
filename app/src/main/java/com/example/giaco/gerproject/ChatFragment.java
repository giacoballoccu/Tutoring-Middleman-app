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
        }
        cparentR = view.findViewById(R.id.cparentR);
        cparentL = view.findViewById(R.id.cparentL);
        layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);


        loggedUser = UserStudenteFactory.getInstance().getUserByEmail(loggedUserMail);  //Funziona solo con studente per ora
        factory = MessageFactory.getInstance();
        factoryConversazione = ConversationFactory.getInstance();
        getActivity().setTitle("Chat");

        nomeCognome = view.findViewById(R.id.contatto);
        avatarDestinatario = view.findViewById(R.id.avatar_destinatario);
        if(factoryConversazione.checkDestinatario(loggedUserMail) != null) {    //Se esiste una conversazione con qualcuno
            avatarDestinatario.setImageDrawable(UserTutorFactory.getInstance().getUserByEmail(factoryConversazione.checkDestinatario(loggedUserMail)).getImage());
            tutorNome = UserTutorFactory.getInstance().getUserByEmail(factoryConversazione.checkDestinatario(loggedUserMail)).getName();
            tutorCognome = UserTutorFactory.getInstance().getUserByEmail(factoryConversazione.checkDestinatario(loggedUserMail)).getSurname();
            tutorNomeCognome = tutorNome + " " + tutorCognome; //Nome e cognome  del destinatario
            nomeCognome.setText(tutorNomeCognome);    //Nome e cognome del destinatario

            for (int i = 0; i < 6; i++) {
                myView = layoutInflater.inflate(R.layout.messaggio_fragment, null, false);
                if (i % 2 == 0) {
                    cparentR.addView(myView);
                    updateR(factoryConversazione.getConversazioni().get(0).getMessaggiMit(), myView, i);
                } else {
                    cparentL.addView(myView);
                    updateL(factoryConversazione.getConversazioni().get(0).getMessaggiDes(), myView, i);
                }


                //cparentR.addView(myView);

                //updateR(factory.getMessaggi().get(0), myView);

            }
        }
        else{
            avatarDestinatario.setVisibility(View.INVISIBLE);
            nomeCognome.setText("Nessuna conversazione");    //Nome e cognome del destinatario
        }
    }

    public void updateR(ArrayList<Message> msg, View myView, int id){
        TextView contenuto, fakeTOP;
        contenuto = myView.findViewById(R.id.messaggio);
        fakeTOP = (TextView) myView.findViewById(R.id.fakeMessaggioTOP);
        if(id == 0){
            fakeTOP.setVisibility(View.GONE);
        }
        //contenuto.setText(msg.getContenuto());
        contenuto.setText("Bella zio!");
        fakeTOP.setText("SONO NASCOSTO IN ALTO");
    }
    public void updateL(ArrayList<Message> msg, View myView,  int id){
        TextView contenuto, fakeTOP;
        contenuto = (TextView) myView.findViewById(R.id.messaggio);
        fakeTOP = (TextView) myView.findViewById(R.id.fakeMessaggioTOP);
        if(id == 0){
            fakeTOP.setVisibility(View.GONE);
        }
        //contenuto.setText(msg.getContenuto());
        contenuto.setText("MI SCUSI?");
        fakeTOP.setText("SONO NASCOSTO IN ALTO");
    }

    public void setTutorFlag(User usr){
        if(usr instanceof UserTutor)
            this.tutorFlag = true;
        else this.tutorFlag = false;
    }

    public boolean getTutorFlag(){
        return this.tutorFlag;
    }

}



