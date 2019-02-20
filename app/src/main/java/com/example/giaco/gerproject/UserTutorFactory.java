package com.example.giaco.gerproject;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;

import java.io.File;
import java.util.ArrayList;

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
            if (userList.get(i).getMateria().equals(materia)) {
                return userList.get(i);
            }
        }
        return null;
    }
}