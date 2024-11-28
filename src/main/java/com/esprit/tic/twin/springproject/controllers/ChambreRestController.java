package com.esprit.tic.twin.springproject.controllers;

import com.esprit.tic.twin.springproject.entities.Chambre;
import com.esprit.tic.twin.springproject.entities.TypeChambre;
import com.esprit.tic.twin.springproject.services.IChambreService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/chambres")
public class ChambreRestController {
    IChambreService chambreService;

    @GetMapping
    public List<Chambre> getAllChambres() {
        return chambreService.retrieveAllChambres();
    }


    @GetMapping("/{id}")
    public Chambre getChambreById(@PathVariable Long id) {
        return chambreService.retrieveChambre(id);
    }


    @PostMapping
    public Chambre addChambre(@RequestBody Chambre chambre) {
        return chambreService.addChambre(chambre);
    }

    @PutMapping("/{id}")
    public Chambre updateChambre(@PathVariable Long id, @RequestBody Chambre chambre) {
        chambre.setIdChambre(id);  // S'assurer que l'ID est correctement défini
        return chambreService.updateChambre(chambre);
    }


    @DeleteMapping("/{id}")
    public void deleteChambre(@PathVariable Long id) {
        chambreService.removeChambre(id);
    }
    @GetMapping("/retrieve-blocs-keywords/{nom-bloc}/{type-chambre}")
    public List<Chambre> retrieveBlocsByNameAndTypeChambre(@PathVariable("nom-bloc") String s, @PathVariable("type-chambre") TypeChambre t) {
        return chambreService.getChambresParNomBlocAndTypeChambre(s, t);
    }
}
