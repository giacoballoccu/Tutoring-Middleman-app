package com.example.giaco.gerproject.Classes;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

public class UserStudente implements Parcelable {
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
        this.setImage(null);
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

    protected UserStudente(Parcel in) {
        email = in.readString();
        password = in.readString();
        name = in.readString();
        surname = in.readString();
        //image = (Drawable) in.readValue(Drawable.class.getClassLoader());
        hours = in.readString();
        phone = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(email);
        dest.writeString(password);
        dest.writeString(name);
        dest.writeString(surname);
        //dest.writeValue(image);
        dest.writeString(hours);
        dest.writeString(phone);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<UserStudente> CREATOR = new Parcelable.Creator<UserStudente>() {
        @Override
        public UserStudente createFromParcel(Parcel in) {
            return new UserStudente(in);
        }

        @Override
        public UserStudente[] newArray(int size) {
            return new UserStudente[size];
        }
    };
}