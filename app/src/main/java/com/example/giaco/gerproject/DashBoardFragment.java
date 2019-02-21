package com.example.giaco.gerproject;

import android.content.Context;
import android.content.Intent;
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

import com.example.giaco.gerproject.Classes.MyListener;
import com.example.giaco.gerproject.Classes.UserTutor;
import com.example.giaco.gerproject.Classes.UserTutorFactory;

import java.util.ArrayList;

public class DashBoardFragment extends Fragment implements MyListener {
    LinearLayout mparent;
    LayoutInflater layoutInflater;
    View myView;
    BookFragment book;
    String email;
    ImageButton b;
    MyListener onClickListener;

    UserTutorFactory factory = UserTutorFactory.getInstance();
    ArrayList<UserTutor> tutorList = factory.getUserList();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dashboard, container, false);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Bacheca");

        mparent = view.findViewById(R.id.mparent);
        layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);


        for(int i = 0; i < tutorList.size() ; i++) {
            switch (tutorList.get(i).getMateria()){
                case "Fisica":
                    myView = layoutInflater.inflate(R.layout.postitgreen, null, false);
                    mparent.addView(myView);
                    b = myView.findViewById(R.id.postitverde);
                    onClickListener = new MyListener(i);
                    b.setOnClickListener(onClickListener);
                    updatePostit(tutorList.get(i), myView);
                    break;
                case "Informatica":
                    myView = layoutInflater.inflate(R.layout.postitred, null, false);
                    mparent.addView(myView);
                    b = myView.findViewById(R.id.postitrosso);
                    onClickListener = new MyListener(i);
                    b.setOnClickListener(onClickListener);
                    updatePostit(tutorList.get(i), myView);
                    break;
                case "Matematica":
                    myView = layoutInflater.inflate(R.layout.postitblue, null, false);
                    mparent.addView(myView);
                    b = myView.findViewById(R.id.postitblu);
                    onClickListener = new MyListener(i);
                    b.setOnClickListener(onClickListener);
                    updatePostit(tutorList.get(i), myView);
                    break;
            }

        }

    }

    public void updatePostit (UserTutor tutor, View myView){

        ImageView avatar;
        TextView nomeCognome, materia;
        email = tutor.getEmail();

        nomeCognome = myView.findViewById(R.id.nomeCognome);
        nomeCognome.setText("" + tutor.getName() + " " + tutor.getSurname() + "");
        avatar = myView.findViewById(R.id.avatarTutor);
        avatar.setImageDrawable(tutor.getImage());
        materia = myView.findViewById(R.id.materia);
        materia.setText("" + tutor.getMateria() + "");

    }

    @Override
    public void onClick(View v) {
        Bundle bundle = new Bundle();
        bundle.putString("email", tutorList.get(this.getN()).getEmail());
        book = new BookFragment();
        book.setArguments(bundle);
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.fragment_container , book);
        transaction.commit();
    };



}
