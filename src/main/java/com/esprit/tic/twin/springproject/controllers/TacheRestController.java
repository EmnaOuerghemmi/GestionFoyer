package com.esprit.tic.twin.springproject.controllers;

import com.esprit.tic.twin.springproject.entities.Tache;
import com.esprit.tic.twin.springproject.services.ITacheService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/taches")
@AllArgsConstructor
public class TacheRestController {

    private final ITacheService tacheService;

    @GetMapping
    public List<Tache> getAllTaches() {
        return tacheService.retrieveAllTaches();
    }

    @GetMapping("/{idTache}")
    public Tache getTacheById(@PathVariable Long idTache) {
        return tacheService.retrieveTache(idTache);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Tache createTache(@RequestBody Tache tache) {
        return tacheService.addTache(tache);
    }

    @PutMapping("/{idTache}")
    public Tache updateTache(@PathVariable Long idTache, @RequestBody Tache tache) {
        tache.setIdTache(idTache);
        return tacheService.updateTache(tache);
    }

    @DeleteMapping("/{idTache}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTache(@PathVariable Long idTache) {
        tacheService.removeTache(idTache);
    }

    @PostMapping("/addTasksAndAffectToEtudiant")
    public List<Tache> addTasksAndAffectToEtudiant(
            @RequestBody List<Tache> tasks,
            @RequestParam String nomEt,
            @RequestParam String prenomEt) {
        return tacheService.addTasksAndAffectToEtudiant(tasks, nomEt, prenomEt);
    }
}
