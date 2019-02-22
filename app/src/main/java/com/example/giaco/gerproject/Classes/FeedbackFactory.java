package com.example.giaco.gerproject.Classes;

import java.util.ArrayList;

public class FeedbackFactory {
    private static FeedbackFactory singleton;

    private ArrayList<Feedback> feedbacks = new ArrayList<>();

    public static FeedbackFactory getInstance() {
        if (singleton == null) {
            singleton = new FeedbackFactory();
        }
        return singleton;
    }


    FeedbackFactory(){
        UserStudenteFactory factory = UserStudenteFactory.getInstance();
        UserTutorFactory factoryTutor = UserTutorFactory.getInstance();

        Feedback feedback1 = new Feedback();
        feedback1.setTitolo("Professionalità");
        feedback1.setDescrizione("Talmente professionale che a volte risulta arrogante");
        feedback1.setAutore(factory.getUserList().get(2).getEmail());
        feedback1.setProfessore(factoryTutor.getUserList().get(2));
        feedback1.setVotoChiarezza(4);
        feedback1.setVotoCompetenza(3);
        feedback1.setVotoDisponibilità(2);
        feedback1.setVotoMedio((feedback1.getVotoChiarezza() + feedback1.getVotoCompetenza() + feedback1.getVotoDisponibilità())/3);


        feedbacks.add(feedback1);

        Feedback feedback2 = new Feedback();
        feedback2.setTitolo("La simpatia non basta");
        feedback2.setDescrizione("Molto scarso ma simpatico");
        feedback2.setAutore(factory.getUserList().get(1).getEmail());
        feedback2.setProfessore(factoryTutor.getUserList().get(2));
        feedback2.setVotoChiarezza(1);
        feedback2.setVotoCompetenza(2);
        feedback2.setVotoDisponibilità(5);
        feedback2.setVotoMedio((feedback2.getVotoChiarezza() + feedback2.getVotoCompetenza() + feedback2.getVotoDisponibilità())/3);


        feedbacks.add(feedback2);

        Feedback feedback3 = new Feedback();
        feedback3.setTitolo("Fortissimo");
        feedback3.setDescrizione("Mi ha aiutato a passare l'esame");
        feedback3.setAutore(factory.getUserList().get(2).getEmail());
        feedback3.setProfessore(factoryTutor.getUserList().get(1));
        feedback3.setVotoChiarezza(5);
        feedback3.setVotoCompetenza(5);
        feedback3.setVotoDisponibilità(4);
        feedback3.setVotoMedio((feedback3.getVotoChiarezza() + feedback3.getVotoCompetenza() + feedback3.getVotoDisponibilità())/3);

        feedbacks.add(feedback3);
    }

    public ArrayList<Feedback> getFeedbacks() {
        return feedbacks;
    }
    public void setFeedbacks(ArrayList<Feedback> feedbacks) {
        this.feedbacks = feedbacks;
    }

    public ArrayList<Feedback> getFeedbackByTutorMail (String tutorMail){
        ArrayList<Feedback> feedbackList = new ArrayList<Feedback>();
        for (int i = 0; i < feedbacks.size(); i++) {
            if (feedbacks.get(i).getProfessore().getEmail().equals(tutorMail)) {
                feedbackList.add(feedbacks.get(i));
            }
        }
        return feedbackList;
    }

    public int getVotoTotaleMedio (ArrayList<Feedback> feedbacks){
        int votoMedio = 0;
        for(int i = 0; i < feedbacks.size(); i++){
            votoMedio = votoMedio + feedbacks.get(i).getVotoMedio();
        }
        return votoMedio/(feedbacks.size());
    }
}
