package com.example.giaco.gerproject.Classes;

public class Conversation {
    private Message contenuto;
    private String mittente;
    private String destinatario;

    public Conversation() {
        this.contenuto = new Message();
        this.mittente = "Default_M";
        this.destinatario = "Default_D";
    }

    public Conversation(Message contenuto, String mittente, String destinatario) {
        this.contenuto = contenuto;
        this.mittente = mittente;
        this.destinatario = destinatario;
    }

    public void setContenuto(Message contenuto) {
        this.contenuto = contenuto;
    }

    public Message getContenuto() {
        return this.contenuto;
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
