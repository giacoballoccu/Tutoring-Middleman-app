package com.example.giaco.gerproject;

import android.app.Activity;
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
import android.widget.Button;
import android.widget.ImageButton;
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
    ChatFragment chat;
    ImageButton butt;


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

        if (factoryConversazione.checkDestinatario(loggedUserMail) != null) //Se esiste una conversazione con qualcuno
            for (Conversation c : ConversationFactory.getInstance().getConversazioniByMittente(loggedUserMail)) {   //Ciclo tutte le conversazioni
                myView = layoutInflater.inflate(R.layout.conversazione, null, false);
                coparent.addView(myView);
                update(c, myView);
            }
        else
            nomeCognome.setText("Nessuna conversazione");    //Nome e cognome del destinatario
    }

    public void update(Conversation conv, View view) {
        String n, c, nc;
        TextView utente;
        ImageView immagine;
        Drawable avatar;


        utente = view.findViewById(R.id.contatto_conversazione);
        immagine = view.findViewById(R.id.avatar_contatto_conversazione);

        if (getTutorFlag() != true) {
            n = UserTutorFactory.getInstance().getUserByEmail(factoryConversazione.checkDestinatario(loggedUserMail)).getName();
            c = UserTutorFactory.getInstance().getUserByEmail(factoryConversazione.checkDestinatario(loggedUserMail)).getSurname();
            avatar = UserTutorFactory.getInstance().getUserByEmail(factoryConversazione.checkDestinatario(loggedUserMail)).getImage();
        } else {
            n = UserStudenteFactory.getInstance().getUserByEmail(factoryConversazione.checkDestinatario(loggedUserMail)).getName();
            c = UserStudenteFactory.getInstance().getUserByEmail(factoryConversazione.checkDestinatario(loggedUserMail)).getSurname();
            avatar = UserStudenteFactory.getInstance().getUserByEmail(factoryConversazione.checkDestinatario(loggedUserMail)).getImage();
        }
        nc = n + " " + c;
        utente.setText(nc);
        immagine.setImageDrawable(avatar);
        butt = myView.findViewById(R.id.contatto_tasto);
        setOnClick(butt, loggedUserMail);
    }

    private void setOnClick(final ImageButton butt, final String mail) {
        butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("actualUserMail", mail);
                if (tutorFlag == false){
                    bundle.putInt("tFlag", 0);
                    chat = new ChatFragment();
                    chat.setArguments(bundle);
                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    FragmentTransaction transaction = fm.beginTransaction();
                    transaction.replace(R.id.fragment_container, chat).addToBackStack("chat_fragment");
                    transaction.commit();
                }
                else{
                    bundle.putInt("tFlag", 1);
                    chat = new ChatFragment();
                    chat.setArguments(bundle);
                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    FragmentTransaction transaction = fm.beginTransaction();
                    transaction.replace(R.id.fragment_container, chat).addToBackStack("chat_fragment");
                    transaction.commit();
                }

            }
        });
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

