package com.esprit.tic.twin.springproject.services;

import com.esprit.tic.twin.springproject.entities.Foyer;
import com.esprit.tic.twin.springproject.entities.Universite;
import com.esprit.tic.twin.springproject.repositories.FoyerRepository;
import com.esprit.tic.twin.springproject.repositories.UniversiteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FoyerServiceImpl implements IFoyerService {
    FoyerRepository foyerRepository;
UniversiteRepository universiteRepository;
    @Override
    public List<Foyer> retrieveAllFoyers() {
        return foyerRepository.findAll();
    }

    @Override
    public Foyer addFoyer(Foyer f) {
        return foyerRepository.save(f);
    }

    @Override
    public Foyer updateFoyer(Foyer f) {
        return foyerRepository.save(f);
    }

    @Override
    public Foyer retrieveFoyer(Long idFoyer) {
        return foyerRepository.findById(idFoyer).orElse(null);
    }

    @Override
    public void removeFoyer(Long idFoyer) {
        foyerRepository.deleteById(idFoyer);
    }

    public List<Foyer> retrieveFoyerByNomUniversity(String nomUniversity) { return foyerRepository.findByUniversiteNomUniversite(nomUniversity); }
    @Override
    public Foyer addFoyerWithBloc(Foyer f) {
        if (f.getUniversite() != null && f.getUniversite().getIdUniversite() != null) {
            Universite universite = universiteRepository.findById(f.getUniversite().getIdUniversite())
                    .orElseThrow(() -> new IllegalArgumentException("Universite introuvable"));
            f.setUniversite(universite);
        }
        if (f.getBlocs() != null) {
            f.getBlocs().forEach(bloc -> bloc.setFoyer(f));
        }
        return foyerRepository.save(f);
    }
}
