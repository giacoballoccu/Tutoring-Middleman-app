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
        reservation1.setProfessore(factoryT.getUserList().get(1));
        reservation1.setStudente(factoryS.getUserList().get(2));
        reservation1.setData("10/03/2019 12:00-13:00");
        reservation1.setMateria(factoryT.getUserList().get(1).getMateria());
        getReservations().add(reservation1);

        Reservation reservation2 = new Reservation();
        reservation2.setProfessore(factoryT.getUserList().get(0));
        reservation2.setStudente(factoryS.getUserList().get(2));
        reservation2.setData("02/03/2019 09:00-10:00");
        reservation2.setMateria(factoryT.getUserList().get(0).getMateria());
        getReservations().add(reservation2);

    }

    public ArrayList<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(ArrayList<Reservation> reservations) {
        this.reservations = reservations;
    }

    public void addReservation(Reservation r){
        this.reservations.add(r);
    }
}
