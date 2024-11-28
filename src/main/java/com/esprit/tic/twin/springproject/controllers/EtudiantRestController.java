package com.esprit.tic.twin.springproject.controllers;

import com.esprit.tic.twin.springproject.entities.Etudiant;
import com.esprit.tic.twin.springproject.services.IEtudiantService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/etudiants")
public class EtudiantRestController {
    IEtudiantService etudiantService;


    @GetMapping
    public List<Etudiant> getAllEtudiants() {
        return etudiantService.retrieveAllEtudiants();
    }


    @GetMapping("/{id}")
    public Etudiant getEtudiantById(@PathVariable Long id) {
        return etudiantService.retrieveEtudiant(id);
    }

    @PostMapping
    public Etudiant addEtudiant(@RequestBody Etudiant etudiant) {
        return etudiantService.addEtudiant(etudiant);
    }


    @PutMapping("/{id}")
    public Etudiant updateEtudiant(@PathVariable Long id, @RequestBody Etudiant etudiant) {
        etudiant.setIdEtudiant(id);  // S'assurer que l'ID est correctement défini
        return etudiantService.updateEtudiant(etudiant);
    }


    @DeleteMapping("/{id}")
    public void deleteEtudiant(@PathVariable Long id) {
        etudiantService.removeEtudiant(id);
    }
    @PutMapping("/affecterEtudiantsAReservations/{etudiant-nom}/{etudiant-prenom}/{reservation-id}")
    public void affecterReservationsAEtudiants(@PathVariable("etudiant-nom") String nomEt, @PathVariable("etudiant-prenom") String prenomEt, @PathVariable("reservation-id") String idReservation) {
        etudiantService.affecterEtudiantAReservation(nomEt, prenomEt, idReservation);
    }

    @GetMapping("/calcul-montant/{etudiant-nom}/{etudiant-prenom}")
    public HashMap<String, Float> calculNouveauMontant(@PathVariable("etudiant-nom") String nomEt, @PathVariable("etudiant-prenom") String prenomEt) {
        return etudiantService.calculNouveauMontantInscriptionDesEtudiants(nomEt, prenomEt);
    }
}
