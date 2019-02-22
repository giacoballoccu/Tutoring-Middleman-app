package com.example.giaco.gerproject.Classes;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;

import com.example.giaco.gerproject.ApplicationContextProvider;
import com.example.giaco.gerproject.R;

import java.util.ArrayList;
import java.util.Calendar;

public class UserTutor extends User{
    private String materia;
    private String citta;
    private String indirizzo;
    private ArrayList<String> disponibilitaData;
    private ArrayList<Feedback> feedbacks;
    private int votoTotaleMedio;

    public UserTutor(){
    }

    public UserTutor(String email, String name, String surname, String password, String phone, Drawable image, String materia, String citta, String indirizzo, ArrayList<Feedback> feedbacks, int votoTotaleMedio) {
        super(email, name, surname, password, phone, image);
        this.setMateria(materia);
        this.setCitta(citta);
        this.setIndirizzo(indirizzo);
        this.setDisponibilitaData(new ArrayList<String>());
        this.setFeedbacks(feedbacks);
        this.setVotoTotaleMedio(votoTotaleMedio);
    }

    /*Tutor senza foto profilo*/
    public UserTutor(String email, String name, String surname, String password, String phone, String materia, String citta, String indirizzo, ArrayList<Feedback> feedbacks, int votoTotaleMedio) {
        super(email, name, surname, password, phone);
        this.setMateria(materia);
        this.setCitta(citta);
        this.setIndirizzo(indirizzo);
        this.setDisponibilitaData(new ArrayList<String>());
        this.setFeedbacks(feedbacks);
        this.setVotoTotaleMedio(votoTotaleMedio);
        Context context = ApplicationContextProvider.getContext();
        Drawable img1 = ResourcesCompat.getDrawable(context.getResources(), R.drawable.tutor_default, null);
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
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

    public ArrayList<Feedback> getFeedbacks() {
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
    }


    public ArrayList<String> getDisponibilitaData() {
        return disponibilitaData;
    }

    public void setDisponibilitaData(ArrayList<String> disponibilitaData) {
        this.disponibilitaData = disponibilitaData;
    }

    public int removeData(String data){
        for(int i=0; i<this.getDisponibilitaData().size(); i++){
            if(this.getDisponibilitaData().get(i).equals(data)){
               getDisponibilitaData().remove(getDisponibilitaData().get(i));
            }
        }
        return -1;
    }
}
