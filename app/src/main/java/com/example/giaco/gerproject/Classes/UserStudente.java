package com.example.giaco.gerproject.Classes;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.content.res.ResourcesCompat;

import com.example.giaco.gerproject.ApplicationContextProvider;
import com.example.giaco.gerproject.R;

public class UserStudente{
    private String email;
    private String password;
    private String name;
    private String surname;
    private Drawable image;
    private String hours;
    private String phone;

    public UserStudente(){
    }

    public UserStudente(String email, String name, String surname, String password, String phone, Drawable image) {
        this.setEmail(email);
        this.setPassword(password);
        this.setName(name);
        this.setSurname(surname);
        this.setImage(image);
        this.setHours("0");
        this.setPhone(phone);
    }

    public UserStudente(String email, String name, String surname, String password, String phone) {
        this.setEmail(email);
        this.setPassword(password);
        this.setName(name);
        this.setSurname(surname);
        Context context = ApplicationContextProvider.getContext();
        Drawable img1 = ResourcesCompat.getDrawable(context.getResources(), R.drawable.emptyimg, null);
        this.setImage(img1);
        this.setHours("0");
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


    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }


}