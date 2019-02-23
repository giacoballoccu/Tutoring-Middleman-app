package com.example.giaco.gerproject.Classes;

import java.util.ArrayList;

public class ConversationFactory extends Conversation {
    private static ConversationFactory singleton;

    private ArrayList<Conversation> conversazioni = new ArrayList<>();

    public static ConversationFactory getInstance() {
        if (singleton == null) {
            singleton = new ConversationFactory();
        }
        return singleton;
    }

    public ConversationFactory(){
        Conversation conv1 = new Conversation();
        conv1.setMessaggiDes(MessageFactory.getInstance().getDomande());
        conv1.setMessaggiMit(MessageFactory.getInstance().getRisposte());
        conv1.setMittente(UserStudenteFactory.getInstance().getUserList().get(0).getEmail());
        conv1.setDestinatario(UserTutorFactory.getInstance().getUserList().get(0).getEmail());
        conversazioni.add(conv1);

        Conversation conv2 = new Conversation();
        conv2.setMessaggiDes(MessageFactory.getInstance().getDomande());
        conv2.setMessaggiMit(MessageFactory.getInstance().getRisposte());
        conv2.setMittente(UserStudenteFactory.getInstance().getUserList().get(0).getEmail());
        conv2.setDestinatario(UserTutorFactory.getInstance().getUserList().get(0).getEmail());
        conversazioni.add(conv2);

        Conversation conv3 = new Conversation();
        conv3.setMessaggiDes(MessageFactory.getInstance().getDomande());
        conv3.setMessaggiMit(MessageFactory.getInstance().getRisposte());
        conv3.setMittente(UserStudenteFactory.getInstance().getUserList().get(0).getEmail());
        conv3.setDestinatario(UserTutorFactory.getInstance().getUserList().get(0).getEmail());
        conversazioni.add(conv3);
    }

    public ArrayList<Conversation> getConversazioni(){
        return conversazioni;
    }

    public String checkDestinatario (String mail){
        String nome = null;
        for(Conversation c : conversazioni) {
            if (c.getMittente().equals(mail))
                nome = c.getDestinatario();
        }
        return nome;
    }

    public ArrayList<Message> getMessaggiByMittente(String mittente){
        ArrayList<Message> lista = new ArrayList<>();
        for(Conversation c : conversazioni){
                if(c.getMittente().equals(mittente))
                    lista = c.getMessaggiMit();
        }
        return lista;
    }

    public ArrayList<Message> getMessaggiByDestinatario(String destinatario){
        ArrayList<Message> lista = new ArrayList<>();
        for(Conversation c : conversazioni){
            if(c.getMittente().equals(destinatario))
                lista = c.getMessaggiDes();
        }
        return lista;
    }

    public Conversation getConversazioneByMittente (String mittente){
        Conversation conversazione = new Conversation();
        for(Conversation c : conversazioni){
            if(conversazione.getMittente().equals(mittente))
                conversazione = c;
        }
        return conversazione;
    }

    public Conversation getConversazioneByDestinatario (String destinatario){
        Conversation conversazione = new Conversation();
        for(Conversation c : conversazioni){
            if(conversazione.getDestinatario().equals(destinatario))
                conversazione = c;
        }
        return conversazione;
    }
}
