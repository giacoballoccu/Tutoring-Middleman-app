package com.example.giaco.gerproject.Classes;

import com.example.giaco.gerproject.Classes.UserStudente;
import com.example.giaco.gerproject.Classes.UserTutor;

import java.util.Calendar;

public class Reservation {
    private UserStudente studente;
    private UserTutor professore;
    private String data;
    private String materia;

            public Reservation(){

            }

            public Reservation(UserStudente studente, UserTutor professore, String data, String materia){
                this.setStudente(studente);
                this.setProfessore(professore);
                this.setData(data);
                this.setMateria(materia);
    }

    public UserStudente getStudente() {
        return studente;
    }

    public void setStudente(UserStudente studente) {
        this.studente = studente;
    }

    public UserTutor getProfessore() {
        return professore;
    }

    public void setProfessore(UserTutor professore) {
        this.professore = professore;
    }



    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
