package com.example.giaco.gerproject.Classes;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.content.res.ResourcesCompat;

import com.example.giaco.gerproject.R;

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
        this.setHours("0");
        this.setPhone(phone);
        this.setImage(image);
    }


    public UserStudente(String email, String name, String surname, String password, String phone) {
        this.setEmail(email);
        this.setPassword(password);
        this.setName(name);
        this.setSurname(surname);
        this.setHours("0");
        this.setPhone(phone);
        userImg.setImageDrawable(ResourcesCompat.getDrawable(context.getResources(), R.drawable.altroancora, null));
        this.setImage().setImageDrawable(ResourcesCompat.getDrawable(context.getResources(), R.drawable.altroancora, null));
        this.setImage(R.drawable.emptyimg);
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

    public void setImage(Drawable image) {
        this.image = image;
    }

    public Drawable getImage() {
        return image;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    protected UserStudente(Parcel in) {
        email = in.readString();
        password = in.readString();
        name = in.readString();
        surname = in.readString();
        hours = in.readString();
        phone = in.readString();
        Bitmap bitmap = (Bitmap) ((BitmapDrawable) image).getBitmap();

        in.writeParcelable(bitmap, 1);
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