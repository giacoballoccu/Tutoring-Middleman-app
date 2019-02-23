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
        conv1.setContenuto(MessageFactory.getInstance());
        conv1.setMittente(UserStudenteFactory.getInstance().getUserList().get(0).getEmail());
        conv1.setDestinatario(UserTutorFactory.getInstance().getUserList().get(0).getEmail());
    }



}
