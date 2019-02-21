package com.example.giaco.gerproject.Classes;

import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.Collections;

public class UserTutor{
    private String email;
    private String password;
    private String name;
    private String surname;
    private Drawable image;
    private String phone;
    private String materia;
    private String citta;
    private String indirizzo;
   // private ArrayList<Feedback> feedbacks;
    //private int votoTotaleMedio;

    public UserTutor(){
    }

    public UserTutor(String email, String name, String surname, String password, String phone, Drawable image, String materia, String citta, String indirizzo, ArrayList<Feedback> feedbacks, int votoTotaleMedio) {
        this.setEmail(email);
        this.setPassword(password);
        this.setName(name);
        this.setSurname(surname);
        this.setImage(image);
        this.setPhone(phone);
        this.setMateria(materia);
        this.setCitta(citta);
        this.setIndirizzo(indirizzo);
       // this.setFeedbacks(feedbacks);
        //this.setVotoTotaleMedio(votoTotaleMedio);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }



    public void setImage(Drawable image) {
        this.image = image;
    }

    public Drawable getImage() {
        return image;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }


   /* public ArrayList<Feedback> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(ArrayList<Feedback> feedbacks) {
        this.feedbacks = feedbacks;
    }

    public int getVotoTotaleMedio() {
        return votoTotaleMedio;
    }

    public void setVotoTotaleMedio(int votoTotaleMedio) {
        this.votoTotaleMedio = votoTotaleMedio;
    }*/

}
