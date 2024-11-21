package com.esprit.tic.twin.springproject.services;

import com.esprit.tic.twin.springproject.entities.Bloc;
import com.esprit.tic.twin.springproject.entities.Chambre;
import com.esprit.tic.twin.springproject.repositories.BlocRepository;
import com.esprit.tic.twin.springproject.repositories.ChambreRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BlocServiceImpl implements IBlocService{
    BlocRepository blocRepository;
    ChambreRepository chambreRepository;
    @Override
    public List<Bloc> retrieveAllBlocs() {
        return blocRepository.findAll();
    }

    @Override
    public Bloc addBloc(Bloc b) {
        return blocRepository.save(b);
    }

    @Override
    public Bloc updateBloc(Bloc b) {
        return blocRepository.save(b);
    }

    @Override
    public Bloc retrieveBloc(Long idBloc) {
        return blocRepository.findById(idBloc).orElse(null);
    }

    @Override
    public void removeBloc(Long idBloc) {
        blocRepository.deleteById(idBloc);
    }
    @Override
    public Bloc affecterChambresABloc(List<Long> numChambre, String nomBloc) {
        // Récupérer le bloc par son nom
        Bloc bloc = blocRepository.findByNomBloc(nomBloc);
        if (bloc == null) {
            throw new RuntimeException("Bloc non trouvé avec le nom : " + nomBloc);
        }

        // Récupérer les chambres par leurs numéros
        List<Chambre> chambres = chambreRepository.findByNumeroChambreIn(numChambre);
        if (chambres.isEmpty()) {
            throw new RuntimeException("Aucune chambre trouvée pour les numéros donnés.");
        }

        // Affecter chaque chambre au bloc
        for (Chambre chambre : chambres) {
            chambre.setBloc(bloc);
        }

        // Sauvegarder les chambres mises à jour
        chambreRepository.saveAll(chambres);

        // Retourner le bloc mis à jour
        return bloc;
    }

    @Override
    public List<Bloc> retrieveBlocsJPQL(String s) {
        return blocRepository.retrieveBlocs(s);
    }

    @Override
    public List<Bloc> retrieveBlocsKeywords(String s) {
        return blocRepository.findByFoyerUniversiteNomUniversite(s);
    }
}
