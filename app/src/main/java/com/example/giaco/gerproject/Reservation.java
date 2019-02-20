package com.example.giaco.gerproject;

import java.util.Calendar;

public class Reservation {
    private UserStudente studente;
    private UserTutor professore;
    private Calendar data;
    private String materia;

            public Reservation(){

            }

            public Reservation(UserStudente studente, UserTutor professore, Calendar data, String materia){
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

    public Calendar getData() {
        return data;
    }

    public void setData(Calendar data) {
        this.data = data;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }
}
