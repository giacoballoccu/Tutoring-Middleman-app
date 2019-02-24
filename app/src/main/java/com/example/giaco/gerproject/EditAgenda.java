package com.example.giaco.gerproject;

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
import android.widget.CalendarView;

public class EditAgenda extends Fragment{
    private int anno = 100;
    private int mese = 100;
    private int giorno = 100;
    View myView;
    CalendarView calendario;
    Button conferma;
    PersonalPageFragment profilo;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //if (loggedUser.getEmail().equals(factory.getMittente()))
        return inflater.inflate(R.layout.conversazioni_fragment, container, false);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        conferma = myView.findViewById(R.id.editAgendaButton);

        calendario = myView.findViewById(R.id.calendario_agenda);
        calendario.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                setGiorno(dayOfMonth);
                setMese(month);
                setAnno(year);
            }
        });

        if(getGiorno() != 100 && getMese() != 100 && getAnno() != 100)
            setOnClick(conferma);
    }

    private void setOnClick(final Button butt) {
        butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("giornoN", getGiorno());
                bundle.putInt("meseN", getMese());
                bundle.putInt("annoN", getAnno());
                profilo = new PersonalPageFragment();
                profilo.setArguments(bundle);
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();
                transaction.replace(R.id.fragment_container, profilo);
                transaction.commit();
            }
        });
    }

    public void setAnno(int a){
        this.anno = a;
    }
    public void setMese(int m){
        this.mese = m;
    }
    public void setGiorno(int g){
        this.giorno = g;
    }

    public int getAnno(){
        return this.anno;
    }
    public int getMese(){
        return this.mese;
    }
    public int getGiorno(){
        return this.giorno;
    }
}
