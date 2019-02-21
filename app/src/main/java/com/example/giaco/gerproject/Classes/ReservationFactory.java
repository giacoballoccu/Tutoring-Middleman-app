package com.example.giaco.gerproject.Classes;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class ReservationFactory {
    private static ReservationFactory singleton;
    private static UserStudenteFactory factoryS = UserStudenteFactory.getInstance();
    private static UserTutorFactory factoryT = UserTutorFactory.getInstance();

    private ArrayList<Reservation> reservations = new ArrayList<>();

    public static ReservationFactory getInstance() {
        if (singleton == null) {
            singleton = new ReservationFactory();
        }
        return singleton;
    }


    ReservationFactory(){
        Reservation reservation1 = new Reservation();
        Calendar data1 = new GregorianCalendar();
        data1.set(2019, 12, 10, 12, 00);
        reservation1.setProfessore(factoryT.getUserList().get(1));
        reservation1.setData(data1);
        reservation1.setMateria("Informatica");
        getReservations().add(reservation1);

        Reservation reservation2 = new Reservation();
        Calendar data2 = new GregorianCalendar();
        data2.set(2019, 10, 12, 9, 00);
        reservation2.setProfessore(factoryT.getUserList().get(0));
        reservation2.setData(data2);
        reservation2.setMateria("Matematica");
        getReservations().add(reservation2);

    }

    public ArrayList<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(ArrayList<Reservation> reservations) {
        this.reservations = reservations;
    }
}
