package com.example.giaco.gerproject.Classes;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Disponibilita {
    private int giorno;
    private int mese;
    private int anno;
    private int oraInizio;
    private int oraFine;
    private int minutoInizio;
    private int minutoFine;
    private ArrayList<Integer> settimana;

    public Disponibilita(int gg, int mm, int aa, int oi, int of, int mi, int mf, ArrayList<Integer> set){
        this.giorno = gg;
        this.mese = mm;
        this.anno = aa;
        this.oraInizio = oi;
        this.oraFine = of;
        this.minutoInizio = mi;
        this.minutoFine = mf;
        this.settimana = set;
    }

    public Disponibilita(int gg, int mm, int aa, int oi, int of){
        this.giorno = gg;
        this.mese = mm;
        this.anno = aa;
        this.oraInizio = oi;
        this.oraFine = of;
        this.minutoInizio = 0;
        this.minutoFine = 0;
    }

    public Disponibilita(){
        this.giorno = 1;
        this.mese = 1;
        this.anno = 2019;
        this.oraInizio = 10;
        this.oraFine = 11;
        this.minutoInizio = 30;
        this.minutoFine = 30;
        ArrayList<Integer> set = new ArrayList<>();
        set.add(0);
        this.settimana = set;
    }

    public int getGiorno() {
        return this.giorno;
    }

    public void setGiorno(int giorno) {
        this.giorno = giorno;
    }

    public int getMese() {
        return this.mese;
    }

    public void setMese(int mese) {
        this.mese = mese;
    }

    public int getAnno() {
        return this.anno;
    }

    public void setAnno(int anno) {
        this.anno = anno;
    }

    public int getOraInizio() {
        return this.oraInizio;
    }

    public void setOraInizio(int oraInizio) {
        this.oraInizio = oraInizio;
    }

    public int getOraFine() {
        return this.oraFine;
    }

    public void setOraFine(int oraFine) {
        this.oraFine = oraFine;
    }

    public int getMinutoInizio() {
        return this.minutoInizio;
    }

    public void setMinutoInizio(int minutoInizio) {
        this.minutoInizio = minutoInizio;
    }

    public int getMinutoFine() {
        return this.minutoFine;
    }

    public void setMinutoFine(int minutoFine) {
        this.minutoFine = minutoFine;
    }

    public ArrayList<Integer> getSettimana(){
        return this.settimana;
    }

    public void setSettimana(ArrayList<Integer> set){
        for(int i : set)
            this.settimana.add(i);
    }

    public void setSettimana(int i){
        this.settimana = null;
    }

    public String toString(){
        String gg ="", mm="", aa="", oi="", of="", mi="", mf="", set=" ";
        if(this.giorno < 10)
            gg = "0" + Integer.toString(this.giorno);
        else
            gg = Integer.toString(this.giorno);
        if(this.giorno > 30 && this.mese == 4 || this.mese ==6 || this.mese ==9 || this.mese ==11)
            this.giorno = 30;
        if(this.giorno > 30 && this.mese == 2)
            this.giorno = 28;
        aa = Integer.toString(this.anno);
        oi = Integer.toString(this.oraInizio);
        of = Integer.toString(this.oraFine);
        mi = Integer.toString(this.minutoInizio);
        mf = Integer.toString(this.minutoFine);

        switch (this.mese){
            case 1:
                mm = "Gennaio";
                break;
            case 2:
                mm = "Febbraio";
                break;
            case 3:
                mm = "Marzo";
                break;
            case 4:
                mm = "Aprile";
                break;
            case 5:
                mm = "Maggio";
                break;
            case 6:
                mm = "Giugno";
                break;
            case 7:
                mm = "Luglio";
                break;
            case 8:
                mm = "Agosto";
                break;
            case 9:
                mm = "Settembre";
                break;
            case 10:
                mm = "Ottobre";
                break;
            case 11:
                mm = "Novembre";
                break;
            case 12:
                mm = "Dicemrbe";
                break;
        }

        if(this.settimana != null){
            for(int i : this.settimana)
                switch(i){
                    case 1:
                        set += "Lunedì ";
                        break;
                    case 2:
                        set += "Martedì ";
                        break;
                    case 3:
                        set += "Mercoledì ";
                        break;
                    case 4:
                        set += "Giovedì ";
                        break;
                    case 5:
                        set += "Venerdì ";
                        break;
                    case 6:
                        set += "Sabato ";
                        break;
                    case 7:
                        set += "Domenica ";
                        break;
                    default:
                        break;
                }

        }
        if(this.settimana == null)
            return (gg + " " + mm + " " + aa + " | " + oi + ":00 - " + of + ":00");
        else
            return ("Ogni " + set + "di " + mm + " " + aa + " | " + oi + ":00 - " + of + ":00");
    }


}
