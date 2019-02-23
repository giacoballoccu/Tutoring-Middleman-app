package com.example.giaco.gerproject.Classes;

public class Message {
    private String contenuto;

    public Message() {
        this.contenuto = "Messaggio";
    }

    public Message(String contenuto){ this.contenuto = contenuto; }

    public void setContenuto(String contenuto){
        this.contenuto = contenuto;
    }

    public String getContenuto(){
        return this.contenuto;
    }
}
