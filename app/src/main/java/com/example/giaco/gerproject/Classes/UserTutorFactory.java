package com.example.giaco.gerproject.Classes;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;

import java.util.ArrayList;

import com.example.giaco.gerproject.ApplicationContextProvider;
import com.example.giaco.gerproject.R;

public class UserTutorFactory {
    private static UserTutorFactory singleton;
    private ArrayList<UserTutor> userList = new ArrayList<>();

    public static UserTutorFactory getInstance() {
        if (singleton == null) {
            singleton = new UserTutorFactory();
        }
        return singleton;
    }


    UserTutorFactory() {

       // FeedbackFactory feedbackFactory = FeedbackFactory.getInstance();

        /*Disponibilità orarie dei tutor*/
        String data;
        Context context = ApplicationContextProvider.getContext();

        ArrayList<String> disponibilitaDataT1 = new ArrayList<>();
        data = "10/03/2019 11:00-12:00";
        disponibilitaDataT1.add(data);
        data = "10/03/2019 09:00-10:00";
        disponibilitaDataT1.add(data);
        data = "01/03/2019 13:00-14:00";
        disponibilitaDataT1.add(data);

        ArrayList<String> disponibilitaDataT2 = new ArrayList<>();
        data = "02/03/2019 8:30-9:30";
        disponibilitaDataT2.add(data);
        data = "30/02/2019 10:00-11:00";
        disponibilitaDataT2.add(data);
        data = "02/03/2019 13:00-14:00";
        disponibilitaDataT2.add(data);

        ArrayList<String> disponibilitaDataT3 = new ArrayList<>();
        data = "01/03/2019 15:00-16:00";
        disponibilitaDataT3.add(data);
        data = "31/02/2019 17:00-18:00";
        disponibilitaDataT3.add(data);
        data = "1/03/2019 18:00-18:00";
        disponibilitaDataT3.add(data);
        data = "10/03/2019 17:00-18:00";
        disponibilitaDataT3.add(data);
        data = "31/03/2019 17:00-18:00";
        disponibilitaDataT3.add(data);
        data = "15/06/2019 17:00-18:00";
        disponibilitaDataT3.add(data);
        data = "16/06/2019 17:00-18:00";
        disponibilitaDataT3.add(data);
        data = "15/08/2019 00:00-23:59";
        disponibilitaDataT3.add(data);


        UserTutor user1 = new UserTutor();
        user1.setEmail("enricoCarlo@gmail.com");
        user1.setName("Enrico");
        user1.setSurname("Carlo");
        Drawable img1 = ResourcesCompat.getDrawable(context.getResources(), R.drawable.tutor3, null);
        user1.setImage(img1);
        user1.setPassword("12345");
        user1.setPhone("1234567890");
        user1.setMateria("Informatica");
        user1.setCitta("Cagliari");
        user1.setIndirizzo("Via roma 37");
        user1.setFeedbacks(new ArrayList<Feedback>());
        user1.setVotoTotaleMedio(0);
        user1.setDisponibilitaData(disponibilitaDataT1);
        //user1.setFeedbacks(feedbackFactory.getFeedbackByTutorMail(user1.getEmail()));
        //user1.setVotoTotaleMedio(feedbackFactory.getVotoTotaleMedio(feedbackFactory.getFeedbackByTutorMail(user1.getEmail())));

        getUserList().add(user1);

        UserTutor user2 = new UserTutor();
        user2.setEmail("antonino@gmail.com");
        user2.setName("Antonino");
        user2.setSurname("Cufaniello");
        Drawable img2 = ResourcesCompat.getDrawable(context.getResources(), R.drawable.tutor1, null);
        user2.setImage(img2);
        user2.setPassword("12345");
        user2.setPhone("0987654321");
        user2.setMateria("Matematica");
        user2.setCitta("Cagliari");
        user2.setIndirizzo("Via Marengo 45");
        user2.setFeedbacks(new ArrayList<Feedback>());
        user2.setVotoTotaleMedio(0);
        user2.setDisponibilitaData(disponibilitaDataT2);
        //user2.setFeedbacks(feedbackFactory.getFeedbackByTutorMail(user2.getEmail()));
        //user2.setVotoTotaleMedio(feedbackFactory.getVotoTotaleMedio(feedbackFactory.getFeedbackByTutorMail(user2.getEmail())));

        getUserList().add(user2);

        UserTutor user3 = new UserTutor();
        user3.setEmail("mail");
        user3.setName("Gigi");
        user3.setSurname("Finizio");
        Drawable img3 = ResourcesCompat.getDrawable(context.getResources(), R.drawable.tutor2, null);
        user3.setImage(img3);
        user3.setPassword("12345");
        user3.setPhone("1234567821");
        user3.setMateria("Fisica");
        user3.setCitta("Cagliari");
        user3.setIndirizzo("Via Marengo 45");
        user3.setFeedbacks(new ArrayList<Feedback>());
        user3.setVotoTotaleMedio(0);
        user3.setDisponibilitaData(disponibilitaDataT3);
        //user3.setFeedbacks(feedbackFactory.getFeedbackByTutorMail(user3.getEmail()));
        //user3.setVotoTotaleMedio(feedbackFactory.getVotoTotaleMedio(feedbackFactory.getFeedbackByTutorMail(user3.getEmail())));

        getUserList().add(user3);
    }

    public ArrayList<UserTutor> getUserList() {
        return userList;
    }

    public void setUserList(ArrayList<UserTutor> userList) {
        this.userList = userList;
    }

    public UserTutor getUserByEmail(String email) {
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getEmail().equals(email)) {
                return userList.get(i);
            }
        }
        return null;
    }

    public UserTutor getUserByMateria(String materia) {
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getMateria().equalsIgnoreCase(materia)) {
                return userList.get(i);
            }
        }
        return null;
    }

    public ArrayList<UserTutor> getUsersByMateria(String materia) {
        ArrayList<UserTutor> listToReturn = new ArrayList<>();

        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getMateria().equalsIgnoreCase(materia)) {
                listToReturn.add(userList.get(i));
            }
        }
        return listToReturn;
    }

    public ArrayList<UserTutor> getUsersByCitta(String citta) {
        ArrayList<UserTutor> listToReturn = new ArrayList<>();

        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getMateria().equalsIgnoreCase(citta)) {
               listToReturn.add(userList.get(i));
            }
        }
        return listToReturn;
    }

    public Boolean isEmailInUserList(String email) {
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }


    /*public ArrayList<UserTutor> sortedByFeedback (ArrayList<UserTutor> userList){
        ArrayList<UserTutor> sortedList = new ArrayList<>();
        sortedList = userList;
        Collections.sort(sortedList, new Comparator<UserTutor>() {
            @Override
            public int compare(UserTutor u1, UserTutor u2) {
                if (u1.getVotoTotaleMedio() < u2.getVotoTotaleMedio()) {
                    return -1;
                } else if (u1.getVotoTotaleMedio() < u2.getVotoTotaleMedio()) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });
        return sortedList;
    }
*/

}