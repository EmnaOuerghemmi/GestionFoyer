package com.esprit.tic.twin.springproject.services;

import com.esprit.tic.twin.springproject.entities.Etudiant;
import com.esprit.tic.twin.springproject.entities.Tache;
import com.esprit.tic.twin.springproject.repositories.EtudiantRepository;
import com.esprit.tic.twin.springproject.repositories.TacheRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TacheServiceImpl implements ITacheService{
    TacheRepository tacheRepository;
EtudiantRepository etudiantRepository;
    @Override
    public List<Tache> retrieveAllTaches() {
        return tacheRepository.findAll();
    }

    @Override
    public Tache addTache(Tache t) {
        return tacheRepository.save(t);
    }

    @Override
    public Tache updateTache(Tache t) {
        return tacheRepository.save(t);
    }

    @Override
    public Tache retrieveTache(Long idTache) {
        return tacheRepository.findById(idTache).orElse(null);
    }

    @Override
    public void removeTache(Long idTache) {
        tacheRepository.deleteById(idTache);
    }

    @Override
    public List<Tache> addTasksAndAffectToEtudiant(List<Tache> tasks, String nomEt, String prenomEt) {
            Etudiant etudiant = etudiantRepository.findByNomEtAndPrenomEt(nomEt, prenomEt);
            if (etudiant == null) {
                throw new RuntimeException("Étudiant non trouvé avec le nom : " + nomEt + " et le prénom : " + prenomEt);
            }
            for (Tache task : tasks) {
                task.setEtudiantOrdinaire(etudiant);  // Association de chaque tâche à l'étudiant
                tacheRepository.save(task);  // Sauvegarde de la tâche dans la base de données
            }
            return tasks;

    }
}
