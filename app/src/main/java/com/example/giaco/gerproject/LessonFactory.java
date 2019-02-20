package com.example.giaco.gerproject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class LessonFactory {
    private static LessonFactory singleton;
    private static UserTutorFactory factory = UserTutorFactory.getInstance();

    private ArrayList<Lesson> lessons = new ArrayList<>();

    public static LessonFactory getInstance() {
        if (singleton == null) {
            singleton = new LessonFactory();
        }
        return singleton;
    }


    LessonFactory(){
        Lesson lesson1 = new Lesson();
        lesson1.setMateria("Informatica");
        lesson1.setProfessore(factory.getUserByEmail("enricoCarlo@gmail.com"));
        getLessons().add(lesson1);

        Lesson lesson2 = new Lesson();
        lesson2.setMateria("Matematica");
        lesson2.setProfessore(factory.getUserByEmail("antonino@gmail.com"));
        getLessons().add(lesson2);

    }

    public ArrayList<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(ArrayList<Reservation> reservations) {
        this.lessons = lessons;
    }
}
