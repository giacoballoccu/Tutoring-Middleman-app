package com.example.giaco.gerproject.Classes;
import java.util.ArrayList;

public class MessageFactory extends Message{
    private static MessageFactory singleton;

    private ArrayList<Message> messaggi = new ArrayList<>();

    public static MessageFactory getInstance() {
        if (singleton == null) {
            singleton = new MessageFactory();
        }
        return singleton;
    }

    public MessageFactory(){
        UserStudenteFactory factoryStudente = UserStudenteFactory.getInstance();
        UserTutorFactory factoryTutor = UserTutorFactory.getInstance();

        Message message1 = new Message();
        message1.setContenuto("Bella zio, che mi puoi dare delle ripetizioni?");
        message1.setMittente(factoryStudente.getUserList().get(2));
        message1.setDestinatario(factoryTutor.getUserList().get(0));
        messaggi.add(message1);

        Message message2 = new Message();
        message2.setContenuto("Inanzitutto stai al tuo posto...");
        message2.setMittente(factoryTutor.getUserList().get(0));
        message2.setDestinatario(factoryStudente.getUserList().get(2));
        messaggi.add(message2);

        Message message3 = new Message();
        message3.setContenuto("Buonasera, volevo chiederle delle ripetizioni gratuite :) ");
        message3.setMittente(factoryStudente.getUserList().get(1));
        message3.setDestinatario(factoryTutor.getUserList().get(1));
        messaggi.add(message3);
    }

    public ArrayList<Message> getMessaggi(){ return messaggi;}

    public Message getMessaggioByMittente(String email){
        Message msgToReturn = new Message();
        for(Message m : messaggi){
            if(m.getMittente().equals(email))
                msgToReturn = m;
        }
        return msgToReturn;
    }

    public Message getMessaggioByDestinatario(String email){
        Message msgToReturn = new Message();
        for(Message m : messaggi){
            if(m.getDestinatario().equals(email))
                msgToReturn = m;
        }
        return msgToReturn;
    }

}
