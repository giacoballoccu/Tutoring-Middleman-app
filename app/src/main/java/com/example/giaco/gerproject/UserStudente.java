package com.example.giaco.gerproject;

public class UserStudente {
    private String email;
    private String password;
    private String name;
    private String surname;
    private String phonenumber;

    public UserStudente(){
        email = "";
        password = "";
        name = "";
        surname = "";
        phonenumber = "";
    }

    public UserStudente(String email, String name, String surname, String password, String phonenumber) {
        this.setEmail(email);
        this.setPassword(password);
        this.setName(name);
        this.setSurname(surname);
        this.setPhonenumber(phonenumber);
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

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }
}
