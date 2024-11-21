package com.esprit.tic.twin.springproject.controllers;

import com.esprit.tic.twin.springproject.entities.Reservation;
import com.esprit.tic.twin.springproject.entities.Etudiant;
import com.esprit.tic.twin.springproject.services.IReservationService;
import com.esprit.tic.twin.springproject.services.IEtudiantService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/reservations")
public class ReservationRestController {
    IReservationService reservationService;
    IEtudiantService etudiantService;

    // 1. Récupérer toutes les réservations
    @GetMapping
    public List<Reservation> getAllReservations() {
        return reservationService.retrieveAllReservations();
    }

    // 2. Récupérer une réservation par son ID
    @GetMapping("/{id}")
    public Reservation getReservationById(@PathVariable String id) {
        return reservationService.retrieveReservation(id);
    }

    // 3. Ajouter une réservation
    @PostMapping
    public Reservation addReservation(@RequestBody Reservation reservation) {
        return reservationService.addReservation(reservation);
    }

    // 4. Modifier une réservation
    @PutMapping("/{id}")
    public Reservation updateReservation(
            @PathVariable String id,
            @RequestBody Reservation reservation) {
        reservation.setIdReservation(id); // S'assurer que l'ID est défini correctement
        return reservationService.updateReservation(reservation);
    }

    // 5. Supprimer une réservation
    @DeleteMapping("/{id}")
    public void deleteReservation(@PathVariable String id) {
        reservationService.removeReservation(id);
    }

    // 6. Affecter un étudiant à une réservation
    @PutMapping("/affecter-etudiant")
    public Etudiant affecterEtudiantAReservation(
            @RequestParam String nomEt,
            @RequestParam String prenomEt,
            @RequestParam String idReservation) {
        return etudiantService.affecterEtudiantAReservation(nomEt, prenomEt, idReservation);
    }
}
