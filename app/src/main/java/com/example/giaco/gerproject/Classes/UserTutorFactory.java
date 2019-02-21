package com.example.giaco.gerproject.Classes;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.example.giaco.gerproject.ApplicationContextProvider;
import com.example.giaco.gerproject.R;

public class UserTutorFactory {
    private static UserTutorFactory singleton;
    private static FeedbackFactory feedbackFactory = FeedbackFactory.getInstance();
    private ArrayList<UserTutor> userList = new ArrayList<>();

    public static UserTutorFactory getInstance() {
        if (singleton == null) {
            singleton = new UserTutorFactory();
        }
        return singleton;
    }


    UserTutorFactory() {

        UserTutor user1 = new UserTutor();
        user1.setEmail("enricoCarlo@gmail.com");
        user1.setName("Enrico");
        user1.setSurname("Carlo");
        Context context = ApplicationContextProvider.getContext();
        Drawable img1 = ResourcesCompat.getDrawable(context.getResources(), R.drawable.enricocarlo, null);
        user1.setImage(img1);
        user1.setPassword("12345");
        user1.setPhone("1234567890");
        user1.setMateria("Informatica");
        user1.setCitta("Cagliari");
        user1.setIndirizzo("Via roma 37");
        //user1.setFeedbacks(feedbackFactory.getFeedbackByTutorMail(user1.getEmail()));
        //user1.setVotoTotaleMedio(feedbackFactory.getVotoTotaleMedio(feedbackFactory.getFeedbackByTutorMail(user1.getEmail())));

        getUserList().add(user1);

        UserTutor user2 = new UserTutor();
        user2.setEmail("antonino@gmail.com");
        user2.setName("Antonino");
        user2.setSurname("Cufaniello");
        Drawable img2 = ResourcesCompat.getDrawable(context.getResources(), R.drawable.altro, null);
        user2.setImage(img2);
        user2.setPassword("12345");
        user2.setPhone("0987654321");
        user2.setMateria("Matematica");
        user2.setCitta("Cagliari");
        user2.setIndirizzo("Via Marengo 45");
        //user2.setFeedbacks(feedbackFactory.getFeedbackByTutorMail(user2.getEmail()));
        //user2.setVotoTotaleMedio(feedbackFactory.getVotoTotaleMedio(feedbackFactory.getFeedbackByTutorMail(user2.getEmail())));


        getUserList().add(user2);

        UserTutor user3 = new UserTutor();
        user3.setEmail("gigifinizzi@gmail.com");
        user3.setName("Gigi");
        user3.setSurname("Finizio");
        Drawable img3 = ResourcesCompat.getDrawable(context.getResources(), R.drawable.altroancora, null);
        user3.setImage(img3);
        user3.setPassword("12345");
        user3.setPhone("1234567821");
        user3.setMateria("Fisica");
        user3.setCitta("Cagliari");
        user3.setIndirizzo("Via Marengo 45");
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