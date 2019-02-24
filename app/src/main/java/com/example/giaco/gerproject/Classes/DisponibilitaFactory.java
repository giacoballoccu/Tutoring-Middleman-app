package com.example.giaco.gerproject.Classes;

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
        Disponibilita data1 = new Disponibilita();
        data1.setGiorno(26);
        data1.setMese(2);
        data1.setAnno(2019);
        data1.setOraInizio(10);
        data1.setOraFine(12);
        data1.setMinutoInizio(30);
        data1.setMinutoFine(30);
        date.add(data1);

        Disponibilita data2 = new Disponibilita();
        data2.setGiorno(30);
        data2.setMese(3);
        data2.setAnno(2019);
        data2.setOraInizio(15);
        data2.setOraFine(19);
        data2.setMinutoInizio(30);
        data2.setMinutoFine(30);
        date.add(data2);

        Disponibilita data3 = new Disponibilita();
        data3.setGiorno(12);
        data3.setMese(4);
        data3.setAnno(2019);
        data3.setOraInizio(11);
        data3.setOraFine(22);
        data3.setMinutoInizio(30);
        data3.setMinutoFine(30);
        date.add(data3);

        Disponibilita data4 = new Disponibilita();
        data4.setGiorno(29);
        data4.setMese(5);
        data4.setAnno(2019);
        data4.setOraInizio(10);
        data4.setOraFine(16);
        data4.setMinutoInizio(30);
        data4.setMinutoFine(30);
        date.add(data4);
    }

    public ArrayList<String> getDate(){
        ArrayList<String> lista = new ArrayList<>();
        for(Disponibilita d : date){
            if()
        }
    }
}
