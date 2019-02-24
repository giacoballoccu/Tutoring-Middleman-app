package com.example.giaco.gerproject.Classes;

public class Disponibilita {
    private int giorno;
    private int mese;
    private int anno;
    private int oraInizio;
    private int oraFine;
    private int minutoInizio;
    private int minutoFine;

    public Disponibilita(int gg, int mm, int aa, int oi, int of, int mi, int mf){
        this.giorno = gg;
        this.mese = mm;
        this.anno = aa;
        this.oraInizio = oi;
        this.oraFine = of;
        this.minutoInizio = mi;
        this.minutoFine = mf;
    }

    public Disponibilita(){
        this.giorno = 1;
        this.mese = 1;
        this.anno = 2019;
        this.oraInizio = 10;
        this.oraFine = 11;
        this.minutoInizio = 30;
        this.minutoFine = 30;
    }
    }

    public int getGiorno() {
        return giorno;
    }

    public void setGiorno(int giorno) {
        this.giorno = giorno;
    }

    public int getMese() {
        return mese;
    }

    public void setMese(int mese) {
        this.mese = mese;
    }

    public int getAnno() {
        return anno;
    }

    public void setAnno(int anno) {
        this.anno = anno;
    }

    public int getOraInizio() {
        return oraInizio;
    }

    public void setOraInizio(int oraInizio) {
        this.oraInizio = oraInizio;
    }

    public int getOraFine() {
        return oraFine;
    }

    public void setOraFine(int oraFine) {
        this.oraFine = oraFine;
    }

    public int getMinutoInizio() {
        return minutoInizio;
    }

    public void setMinutoInizio(int minutoInizio) {
        this.minutoInizio = minutoInizio;
    }

    public int getMinutoFine() {
        return minutoFine;
    }

    public void setMinutoFine(int minutoFine) {
        this.minutoFine = minutoFine;
    }

    public String toString(){
        String gg, mm;
        if(this.giorno < 10)
            gg = "0" + Integer.toString(this.giorno);
        if(this.giorno > 30 && this.mese == 4 || this.mese ==6 || this.mese ==9 || this.mese ==11)
            this.mese = 30;
        if(this.giorno > 30 and this.mese == 2)
            this.giorno = 28;
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
            default:
                mm = "Gennaio";
                break;
        }
    }
}
