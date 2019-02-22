package com.example.giaco.gerproject.Classes;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;

import com.example.giaco.gerproject.ApplicationContextProvider;
import com.example.giaco.gerproject.R;

public abstract class User {
        private String email;
        private String password;
        private String name;
        private String surname;
        private Drawable image;
        private String phone;

        public User(){
        }

        public User(String email, String name, String surname, String password, String phone, Drawable image) { //Costruttore con immagine
            this.setEmail(email);
            this.setPassword(password);
            this.setName(name);
            this.setPhone(phone);
            this.setSurname(surname);
            this.setImage(image);
        }

        public User(String email, String name, String surname, String password, String phone) { //Costruttore generico immagine default
            this.setEmail(email);
            this.setPassword(password);
            this.setName(name);
            this.setSurname(surname);
            this.setPhone(phone);
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


    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }

}
