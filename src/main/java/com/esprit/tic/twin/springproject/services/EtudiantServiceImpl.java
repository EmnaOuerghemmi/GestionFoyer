package com.esprit.tic.twin.springproject.services;

import com.esprit.tic.twin.springproject.entities.Etudiant;
import com.esprit.tic.twin.springproject.entities.Reservation;
import com.esprit.tic.twin.springproject.entities.Tache;
import com.esprit.tic.twin.springproject.repositories.EtudiantRepository;
import com.esprit.tic.twin.springproject.repositories.ReservationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class EtudiantServiceImpl implements IEtudiantService{
    EtudiantRepository etudiantRepository;
    ReservationRepository reservationRepository;

    @Override
    public List<Etudiant> retrieveAllEtudiants() {
        return etudiantRepository.findAll();
    }

    @Override
    public Etudiant addEtudiant(Etudiant e) {
        return etudiantRepository.save(e);
    }

    @Override
    public Etudiant updateEtudiant(Etudiant e) {
        return etudiantRepository.save(e);
    }

    @Override
    public Etudiant retrieveEtudiant(Long idEtudiant) {
        return etudiantRepository.findById(idEtudiant).orElse(null);
    }

    @Override
    public void removeEtudiant(Long idEtudiant) {
        etudiantRepository.deleteById(idEtudiant);
    }
    @Override
    public Etudiant affecterEtudiantAReservation(String nomEt, String prenomEt, String idReservation) {
        //Same work every time we have many-to-many relation
        Etudiant e = etudiantRepository.findByNomEtAndPrenomEt(nomEt, prenomEt);
        Reservation r = reservationRepository.findByIdReservation(idReservation);

        //Etudiant is the parent cause mappedBy is in reservation so reservation is child!
        Set<Reservation> reservationsMisesAJour = new HashSet<Reservation>();
        if(e.getReservations()!=null) {
            reservationsMisesAJour = e.getReservations();
        }
        reservationsMisesAJour.add(r);
        e.setReservations(reservationsMisesAJour);
        etudiantRepository.save(e);

        return e;
    }

    @Override
    public HashMap<String, Float> calculNouveauMontantInscriptionDesEtudiants(String nomEt, String prenomEt) {
        Etudiant etudiant = etudiantRepository.findByNomEtAndPrenomEt(nomEt, prenomEt);

        Set<Tache> taches = etudiant.getTaches();
        float nouveauMontant = 0;

        for (Tache tache : taches) {
            nouveauMontant += tache.getDuree() * tache.getTarifHoraire();
        }

        HashMap<String, Float> result = new HashMap<>();
        result.put(nomEt + " " + prenomEt, nouveauMontant);

        return result;
    }

}
