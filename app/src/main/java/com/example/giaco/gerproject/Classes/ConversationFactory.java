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
}
