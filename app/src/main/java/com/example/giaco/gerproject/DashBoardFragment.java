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

import java.util.ArrayList;

public class DashBoardFragment extends Fragment {
    LinearLayout mparent;
    LayoutInflater layoutInflater;
    View myView;

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
                    updatePostit(tutorList.get(i), myView);
                    break;
                case "Informatica":
                    myView = layoutInflater.inflate(R.layout.postitred, null, false);
                    mparent.addView(myView);
                    updatePostit(tutorList.get(i), myView);
                    break;
                case "Matematica":
                    myView = layoutInflater.inflate(R.layout.postitblue, null, false);
                    mparent.addView(myView);
                    updatePostit(tutorList.get(i), myView);
                    break;
            }

        }

    }

    public void updatePostit (UserTutor tutor, View myView){
        ImageView avatar;
        TextView nomeCognome, materia;

        nomeCognome = myView.findViewById(R.id.nomeCognome);
        nomeCognome.setText("" + tutor.getName() + " " + tutor.getSurname() + "");
        avatar = myView.findViewById(R.id.avatarTutor);
        avatar.setImageDrawable(tutor.getImage());
        materia = myView.findViewById(R.id.materia);
        materia.setText("" + tutor.getMateria() + "");
    }


}
