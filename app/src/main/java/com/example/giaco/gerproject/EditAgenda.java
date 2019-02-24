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

public class EditAgenda extends Fragment implements View.OnClickListener {
    private int anno = 100;
    private int mese = 100;
    private int giorno = 100;

    CalendarView calendario;
    Button conferma;
    PersonalPageFragment profilo;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_editagenda, container, false);;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Aggiungi una data");
        conferma = view.findViewById(R.id.editAgendaButton);

        calendario = view.findViewById(R.id.calendario_agenda);

        calendario.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView viewC, int year, int month, int dayOfMonth) {
                setGiorno(dayOfMonth);
                setMese(month);
                setAnno(year);
            }
        });
        conferma.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.editAgendaButton:
                Bundle bundle = new Bundle();
                bundle.putInt("annoN", getAnno());
                bundle.putInt("meseN", getMese());
                bundle.putInt("giornoN", getGiorno());
                profilo = new PersonalPageFragment();
                profilo.setArguments(bundle);
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();
                transaction.replace(R.id.fragment_container, profilo);
                transaction.commit();
                break;
        }

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
