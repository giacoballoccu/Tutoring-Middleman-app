package com.example.giaco.gerproject.Classes;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;

import com.example.giaco.gerproject.ApplicationContextProvider;
import com.example.giaco.gerproject.R;

public class UserStudente extends User{
    private String hours;


    public UserStudente(){
    }

    public UserStudente(String email, String name, String surname, String password, String phone, Drawable image) {
        super(email, name, surname, password, phone, image);
        this.setHours("0");
    }

    public UserStudente(String email, String name, String surname, String password, String phone) { //Immagine default
        super(email, name, surname, password, phone);
        this.setHours("0");
        Context context = ApplicationContextProvider.getContext();
        Drawable img1 = ResourcesCompat.getDrawable(context.getResources(), R.drawable.studente_default, null);
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

}