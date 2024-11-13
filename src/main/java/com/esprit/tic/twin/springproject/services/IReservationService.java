package com.esprit.tic.twin.springproject.services;

import com.esprit.tic.twin.springproject.entities.Reservation;
import java.util.List;

public interface IReservationService {
    List<Reservation> retrieveAllReservations();
    Reservation addReservation(Reservation r);
    Reservation updateReservation(Reservation r);
    Reservation retrieveReservation(String idReservation);
    void removeReservation(String idReservation);
}
