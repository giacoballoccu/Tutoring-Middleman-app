package com.example.giaco.gerproject.Classes;

import java.util.ArrayList;

public class Conversation {
    private ArrayList<Message> messaggiMit;
    private ArrayList<Message> messaggiDes;
    private String mittente;
    private String destinatario;

    public Conversation() {
        this.messaggiMit = new ArrayList<>();
        this.messaggiDes = new ArrayList<>();
        this.mittente = "Default_M";
        this.destinatario = "Default_D";
    }

    public Conversation(ArrayList<Message> messaggiMit, ArrayList<Message> messaggiDes, String mittente, String destinatario) {
        this.messaggiMit = messaggiMit;
        this.messaggiDes = messaggiDes;
        this.mittente = mittente;
        this.destinatario = destinatario;
    }

    public void setMessaggiMit(ArrayList<Message> lista){
        this.messaggiMit = lista;
    }

    public ArrayList<Message> getMessaggiMit(){
        return this.messaggiMit;
    }

    public void setMessaggiDes(ArrayList<Message> lista){
        this.messaggiDes = lista;
    }

    public ArrayList<Message> getMessaggiDes(){
        return this.messaggiDes;
    }

    public void setMittente(String mittente) {
        this.mittente = mittente;
    }

    public String getMittente() {
        return this.mittente;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public String getDestinatario(){
        return this.destinatario;
    }
}
