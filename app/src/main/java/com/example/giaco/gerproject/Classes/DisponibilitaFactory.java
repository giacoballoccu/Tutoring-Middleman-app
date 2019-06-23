package com.example.giaco.gerproject.Classes;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class DisponibilitaFactory extends Disponibilita {
    private static DisponibilitaFactory singleton;

    private ArrayList<Disponibilita> date = new ArrayList<>();

    public static DisponibilitaFactory getInstance() {
        if (singleton == null) {
            singleton = new DisponibilitaFactory();
        }
        return singleton;
    }

    DisponibilitaFactory(){
        ArrayList<Integer> set1 = new ArrayList<>();
        set1.add(1);
        set1.add(5);
        ArrayList<Integer> set2 = new ArrayList<>();
        set2.add(2);
        ArrayList<Integer> set3 = new ArrayList<>();
        set3.add(4);
        set3.add(6);

        Disponibilita data2 = new Disponibilita();
        data2.setGiorno(30);
        data2.setMese(3);
        data2.setAnno(2019);
        data2.setOraInizio(15);
        data2.setOraFine(19);
        data2.setMinutoInizio(0);
        data2.setMinutoFine(0);
        data2.setSettimana(set1);
        date.add(data2);

        Disponibilita data3 = new Disponibilita();
        data3.setGiorno(12);
        data3.setMese(4);
        data3.setAnno(2019);
        data3.setOraInizio(11);
        data3.setOraFine(22);
        data3.setMinutoInizio(0);
        data3.setMinutoFine(0);
        data3.setSettimana(set2);
        date.add(data3);

        Disponibilita data4 = new Disponibilita();
        data4.setGiorno(29);
        data4.setMese(5);
        data4.setAnno(2019);
        data4.setOraInizio(10);
        data4.setOraFine(16);
        data4.setMinutoInizio(0);
        data4.setMinutoFine(0);
        data4.setSettimana(0);
        date.add(data4);
        Disponibilita dateRipetute = new Disponibilita();
    }

    public ArrayList<String> getDate(){
        ArrayList<String> lista = new ArrayList<>();
        for(Disponibilita d : date)
            lista.add(d.toString());
        return lista;
    }

    public ArrayList<Disponibilita> getDateDisponibilita(){
        ArrayList<Disponibilita> lista = new ArrayList<>();
        for(Disponibilita d : date)
            lista.add(d);

        return lista;
    }

    public ArrayList<String> addDisponibilita(int gg, int mm, int aaaa, int oi, int of, int gs){
        ArrayList<String> lista = DisponibilitaFactory.getInstance().getDate();
        Disponibilita nuovaData = new Disponibilita();
        nuovaData.setGiorno(gg);
        nuovaData.setMese(mm);
        nuovaData.setAnno(aaaa);
        nuovaData.setOraInizio(oi);
        nuovaData.setOraFine(of);
        nuovaData.setOraFine(12);
        nuovaData.setMinutoInizio(30);
        nuovaData.setMinutoFine(30);
        nuovaData.setSettimana(gs);
        date.add(nuovaData);
        lista.add(nuovaData.toString());
        return lista;
    }

}
