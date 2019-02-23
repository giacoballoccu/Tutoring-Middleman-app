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

import com.example.giaco.gerproject.Classes.MessageFactory;
import com.example.giaco.gerproject.Classes.User;
import com.example.giaco.gerproject.Classes.UserStudenteFactory;

public class ChatFragment extends Fragment{

    private LinearLayout cparent;
    String loggedUserMail;
    LayoutInflater layoutInflater;
    private User loggedUser;
    String nome;
    String cognome;
    TextView nomeCognome;
    TextView testo;
    MessageFactory factory;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        loggedUser = UserStudenteFactory.getInstance().getUserByEmail(loggedUserMail);
        factory = MessageFactory.getInstance();
        return inflater.inflate(R.layout.chat_fragment, container, false);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Chat");
            nomeCognome.setText("" + loggedUser.getName() + " " + loggedUser.getSurname() + "");

            testo.setText(""+ factory.getContenuto() +"");
}
