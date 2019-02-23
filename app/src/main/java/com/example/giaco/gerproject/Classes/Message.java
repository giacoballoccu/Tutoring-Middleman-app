package com.example.giaco.gerproject.Classes;

public class Message {
    private String contenuto;
    private User mittente;
    private User destinatario;

    public Message() {
        this.contenuto = "Messaggio";
        this.mittente = new UserStudente();
        this.mittente = new UserTutor();
    }

    public Message(String contenuto, User mittente, User destinatario){
        this.contenuto = contenuto;
        this.mittente = mittente;
        this.destinatario = destinatario;
    }

    public void setContenuto(String contenuto){
        this.contenuto = contenuto;
    }
    public String getContenuto(){
        return this.contenuto;
    }

    public void setMittente(User mittente) {
        this.mittente = mittente;
    }

    public User getMittente(){
        return this.mittente;
    }

    public void setDestinatario(User destinatario){
        this.destinatario = destinatario;
    }

    public User getDestinatario(){
        return this.destinatario;
    }
}
