package com.example.giaco.gerproject.Classes;

import java.util.ArrayList;

public class ReservationRequestFactory {
    private static ReservationRequestFactory singleton;
    UserStudenteFactory factoryS = UserStudenteFactory.getInstance();
    UserTutorFactory factoryT = UserTutorFactory.getInstance();

    private ArrayList<ReservationRequest> reservations = new ArrayList<>();

    public static ReservationRequestFactory getInstance() {
        if (singleton == null) {
            singleton = new ReservationRequestFactory();
        }
        return singleton;
    }

    public ArrayList<ReservationRequest> getReservations() {
        return reservations;
    }

    public void setReservations(ArrayList<ReservationRequest> reservations) {
        this.reservations = reservations;
    }

    public void addReservation(ReservationRequest r){
        this.reservations.add(r);
    }

}