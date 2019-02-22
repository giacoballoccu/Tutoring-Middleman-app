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

import com.example.giaco.gerproject.Classes.Reservation;
import com.example.giaco.gerproject.Classes.ReservationFactory;

import java.util.ArrayList;

public class MyReservationsFragment extends Fragment {
    LinearLayout mparent;
    LayoutInflater layoutInflater;
    View myView;
    String emailLoggedUser;

    ReservationFactory factory = ReservationFactory.getInstance();
    ArrayList<Reservation> resList = factory.getReservations();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_my_reservations, container, false);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        if (getArguments() != null) {
            emailLoggedUser = getArguments().getString("actualUserMail");
        }
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Prenotazioni");

        mparent = view.findViewById(R.id.parentview_reservation);
        layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        myView = layoutInflater.inflate(R.layout.reservation_static, null, false);
        mparent.addView(myView);


        for(int i = 0 ; i < resList.size() ; i++){

            myView = layoutInflater.inflate(R.layout.single_reservation, null, false);

            if(emailLoggedUser.equals(resList.get(i).getStudente().getEmail()) || emailLoggedUser.equals(resList.get(i).getProfessore().getEmail())) {
                mparent.addView(myView);    //ERRORE
            }
            updatePackage(resList.get(i), myView);
        }

    }


    public void updatePackage (Reservation res, View myView){
        ImageView img;
        TextView nome, materia, orario;

        Drawable myDrawable =  res.getProfessore().getImage();
        img = myView.findViewById(R.id.avatar_prenotazione);
        img.setImageDrawable(myDrawable);

        nome = myView.findViewById(R.id.nome_reservation);
        nome.setText(res.getProfessore().getName() + " " + res.getProfessore().getSurname());

        materia = myView.findViewById(R.id.materia_reservation);
        materia.setText(res.getMateria());

        switch (res.getMateria()){
            case "Fisica":
                materia.setTextColor(getResources().getColor(R.color.greenfisica));
                break;
            case "Matematica":
                materia.setTextColor(getResources().getColor(R.color.bluematematica));
                break;
            case "Informatica" :
                materia.setTextColor(getResources().getColor(R.color.rossoinformatica));

        }

        orario = myView.findViewById(R.id.ora_prenotazione);
        orario.setText(res.getData());


    }
}
