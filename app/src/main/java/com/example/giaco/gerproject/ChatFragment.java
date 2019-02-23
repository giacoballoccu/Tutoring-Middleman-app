package com.example.giaco.gerproject;

import android.content.Context;
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

import com.example.giaco.gerproject.Classes.Message;
import com.example.giaco.gerproject.Classes.MessageFactory;
import com.example.giaco.gerproject.Classes.User;
import com.example.giaco.gerproject.Classes.UserStudenteFactory;
import com.example.giaco.gerproject.Classes.UserTutor;

public class ChatFragment extends Fragment {

    private LinearLayout cparent;
    String loggedUserMail;
    LayoutInflater layoutInflater;
    private User loggedUser;
    String nome;
    String cognome;
    TextView nomeCognome;
    TextView testo;
    MessageFactory factory;
    //View cparentR, cparentL;
    LinearLayout cparentR, cparentL;
    boolean tutorFlag;
    View myView;


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


        loggedUser = UserStudenteFactory.getInstance().getUserByEmail(loggedUserMail);
        factory = MessageFactory.getInstance();
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Chat");

        nomeCognome = view.findViewById(R.id.contatto);
        nomeCognome.setText("" + loggedUser.getName() + " " + loggedUser.getSurname() + "");

        for(int i=0; i<6; i++) {
            myView = layoutInflater.inflate(R.layout.messaggio_fragment, null, false);
            if(i % 2 == 0){
                cparentL.addView(myView);
                updateL(factory.getMessaggi().get(0), myView, i);
            }
            else{
                cparentR.addView(myView);
                updateR(factory.getMessaggi().get(0), myView, i);

            }


            //cparentR.addView(myView);

            //updateR(factory.getMessaggi().get(0), myView);

        }
    }

    public void updateR(Message msg, View myView, int id){
        TextView contenuto, fakeContenuto;
        contenuto = myView.findViewById(R.id.messaggio);
        fakeContenuto = (TextView) myView.findViewById(R.id.fakeMessaggio);
        if(id % 2 == 0)
            fakeContenuto.setVisibility(View.GONE);

        //contenuto.setText(msg.getContenuto());
        contenuto.setText("MI SCUSI????");
        fakeContenuto.setText("SONO NASCOSTO");
    }
    public void updateL(Message msg, View myView,  int id){
        TextView contenuto, fakeContenuto;
        contenuto = (TextView) myView.findViewById(R.id.messaggio);
        fakeContenuto = (TextView) myView.findViewById(R.id.fakeMessaggio);
        if(id % 2 == 0)
            fakeContenuto.setVisibility(View.GONE);
        //contenuto.setText(msg.getContenuto());
        contenuto.setText("Bella zio");
        fakeContenuto.setText("SONO NASCOSTO");

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



