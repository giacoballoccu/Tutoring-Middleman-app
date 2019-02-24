package com.example.giaco.gerproject;

import android.content.Context;
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
import android.widget.TextView;
import com.example.giaco.gerproject.Classes.DisponibilitaFactory;


public class EditAgenda extends Fragment {
    private int anno;
    private int mese;
    private int giorno;
    CalendarView calendario;
    String emailLoggedUser;
    PersonalPageFragment p;
    LayoutInflater layoutInflater;
    View myView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_editagenda, container, false);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Aggiungi una data");
        if (getArguments() != null) {
            emailLoggedUser = getArguments().getString("actualUserMail");
        }
        //aparent = (LinearLayout) view.findViewById(R.id.parentview);
        //layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //staticView = layoutInflater.inflate(R.layout.ore_rimanenti_static, null, false);
        //mparent.addView(staticView);
        ///aux = (TextView) staticView.findViewById(R.id.n_ore_rimanenti);
        //ore_rimaste = Integer.parseInt(aux.getText().toString());

        //salvaModifiche.setOnClickListener();
        layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        myView = layoutInflater.inflate(R.layout.conversazione, null, false);
        calendario = view.findViewById(R.id.calendario_agenda);
        calendario.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int anno, int mese, int giorno) {
                setAnno(anno);
                setMese(mese);
                setGiorno(giorno);
            }
        });

        update(view);
        //setOnClick
    }

    public void update(View myView){
        Button butt;
        butt = myView.findViewById(R.id.salvaAggiunta);
        setOnClick(butt, myView);
    }

    private void setOnClick(final Button butt, final View view) {
        butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("annoN", getAnno());
                bundle.putInt("meseN", getMese());
                bundle.putInt("giornoM", getGiorno());
                bundle.putString("flagAggiunta", "ok");
                p = new PersonalPageFragment();
                p.setArguments(bundle);
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();
                transaction.replace(R.id.fragment_container, p);
                transaction.commit();}
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
