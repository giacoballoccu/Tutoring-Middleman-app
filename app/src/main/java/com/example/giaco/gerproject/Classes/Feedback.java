package com.example.giaco.gerproject.Classes;

import java.util.ArrayList;

public class Feedback {
    private String titolo;
    private String descrizione;
    private String autore;
    private UserTutor professore;
    private int votoChiarezza;
    private int votoDisponibilità;
    private int votoCompetenza;
    private int votoMedio;

    public Feedback(){
        this.titolo = "";
        this.descrizione = "";
        this.autore = "";
        this.setProfessore(new UserTutor());
        this.votoChiarezza = 0;
        this.votoCompetenza = 0;
        this.votoDisponibilità = 0;
        this.votoMedio = 0;

    }

    public Feedback(String titolo, String descrizione, String autore, UserTutor professore, int votoChiarezza, int votoCompetenza, int votoDisponibilità){
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.autore = autore;
        this.setProfessore(professore);
        this.votoChiarezza = votoChiarezza;
        this.votoDisponibilità = votoDisponibilità;
        this.votoCompetenza = votoCompetenza;
        this.votoMedio = (votoChiarezza + votoDisponibilità + votoCompetenza) / 3;
    }
    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getAutore() {
        return autore;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }


    public int getVotoChiarezza() {
        return votoChiarezza;
    }

    public void setVotoChiarezza(int votoChiarezza) {
        this.votoChiarezza = votoChiarezza;
    }

    public int getVotoDisponibilità() {
        return votoDisponibilità;
    }

    public void setVotoDisponibilità(int votoDisponibilità) {
        this.votoDisponibilità = votoDisponibilità;
    }

    public int getVotoCompetenza() {
        return votoCompetenza;
    }

    public void setVotoCompetenza(int votoCompetenza) {
        this.votoCompetenza = votoCompetenza;
    }

    public int getVotoMedio() {
        return votoMedio;
    }

    public void setVotoMedio(int votoMedio) {
        this.votoMedio = votoMedio;
    }

    public UserTutor getProfessore() {
        return professore;
    }

    public void setProfessore(UserTutor professore) {
        this.professore = professore;
    }


}
