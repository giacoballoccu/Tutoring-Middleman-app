package com.example.giaco.gerproject.Classes;

public class ReservationRequest {
    private UserStudente studente;
    private UserTutor professore;
    private String data;
    private int ore;
    private String materia;
    private int ora_inizio;
    private int ora_fine;

    public ReservationRequest(){

    }

    public ReservationRequest(UserStudente studente, UserTutor professore, String data,int ore, String materia, int ora_inizio, int ora_fine){
        this.setStudente(studente);
        this.setProfessore(professore);
        this.setData(data);
        this.setMateria(materia);
        this.setOre(ore);
        this.setOra_inizio(ora_inizio);
        this.setOra_fine(ora_fine);
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public int getOre() {
        return ore;
    }

    public void setOre(int ore) {
        this.ore = ore;
    }

    public int getOra_inizio() {
        return ora_inizio;
    }

    public void setOra_inizio(int ora_inizio) {
        this.ora_inizio = ora_inizio;
    }

    public int getOra_fine() {
        return ora_fine;
    }

    public void setOra_fine(int ora_fine) {
        this.ora_fine = ora_fine;
    }
}
