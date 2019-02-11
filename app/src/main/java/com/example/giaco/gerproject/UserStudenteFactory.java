package com.example.giaco.gerproject;

import java.util.ArrayList;

public class UserStudenteFactory {
    private static UserStudenteFactory singleton;
    private ArrayList<UserStudente> userList = new ArrayList<>();

    public static UserStudenteFactory getInstance() {
        if (singleton == null) {
            singleton = new UserStudenteFactory();
        }
        return singleton;
    }

    UserStudenteFactory() {

        UserStudente user1 = new UserStudente();
        user1.setEmail("giannithunder@gmail.com");
        user1.setName("Gianni");
        user1.setSurname("Thunder");
        user1.setPassword("12345");
        user1.setPhonenumber("3456789012");

        getUserList().add(user1);


        UserStudente user2 = new UserStudente();
        user1.setEmail("pietropranu@gmail.com");
        user1.setName("Pietro");
        user1.setSurname("Pranu");
        user1.setPassword("12345");
        user1.setPhonenumber("0123456789");

        getUserList().add(user2);
    }

    public void addUserToFactory(String email, String name, String surname, String password, String phonenumber) {
        UserStudente user = new UserStudente(email, name, surname, password, phonenumber);
        getUserList().add(user);
    }

    public ArrayList<UserStudente> getUserList() {
        return userList;
    }

    public void setUserList(ArrayList<UserStudente> userList) {
        this.userList = userList;
    }

    public UserStudente getUserByEmail(String email) {
        try {
            for (int i = 0; i < userList.size(); i++) {
                if (userList.get(i).getEmail().equals(email)) {
                    return userList.get(i);
                }
            }
        } catch (Exception UserNotFound) {
            System.out.print(UserNotFound);
        }
        return null; //It's it right?
    }

    public Boolean isEmailInUserList(String email) {
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }
}

