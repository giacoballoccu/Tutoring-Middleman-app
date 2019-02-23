package com.example.giaco.gerproject.Classes;
import java.util.ArrayList;

public class MessageFactory extends Message{
    private static MessageFactory singleton;

    private ArrayList<Message> domande = new ArrayList<>();
    private ArrayList<Message> risposte = new ArrayList<>();

    public static MessageFactory getInstance() {
        if (singleton == null) {
            singleton = new MessageFactory();
        }
        return singleton;
    }

    public MessageFactory(){
        UserStudenteFactory factoryStudente = UserStudenteFactory.getInstance();
        UserTutorFactory factoryTutor = UserTutorFactory.getInstance();

        /*Domande*/
        Message domanda1 = new Message();
        domanda1.setContenuto("Bella zio, che mi puoi dare delle ripetizioni?");
        domande.add(domanda1);

        Message domanda2 = new Message();
        domanda2.setContenuto("SCUSA MI SERVONO RIPETIZIONI ASAP.");
        domande.add(domanda2);

        Message domanda3 = new Message();
        domanda3.setContenuto("Buonasera, volevo chiederle delle ripetizioni gratuite :) ");
        domande.add(domanda3);

        /*Risposte*/
        Message risposta1 = new Message();
        risposta1.setContenuto("Salve, sono disponibile ad aiutarla in cambio di danaro ovviamente.");
        risposte.add(risposta1);

        Message risposta2 = new Message();
        risposta2.setContenuto("Scusa non ne ho molta voglia...");
        risposte.add(risposta2);

        Message risposta3 = new Message();
        risposta3.setContenuto("Certamente; e lei gradirebbe un pesce?");
        risposte.add(risposta3);
    }

    public ArrayList<Message> getDomande(){ return domande;}
    public ArrayList<Message> getRisposte(){ return risposte;}

}
