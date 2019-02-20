package com.example.giaco.gerproject;

public class Lesson {
    private String materia;
    private UserTutor professore;

    public Lesson(){
        this.setMateria("");
        this.setProfessore(new UserTutor());
    }

    public Lesson(String materia, UserTutor professore){
        this.setMateria(materia);
        this.setProfessore(professore);
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
}
