package com.example.giaco.gerproject;

import java.util.ArrayList;

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
        user1.setImgSrc("");
        user1.setPassword("12345");
        user1.setPhone("1234567890");

        getUserList().add(user1);

        UserTutor user2 = new UserTutor();
        user2.setEmail("antonino@gmail.com");
        user2.setName("Antonino");
        user2.setSurname("Cufaniello");
        user2.setImgSrc("");
        user2.setPassword("12345");
        user2.setPhone("0987654321");

        getUserList().add(user2);
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
            if (userList.get(i).getMateria().equals(materia)) {
                return userList.get(i);
            }
        }
        return null;
    }
}