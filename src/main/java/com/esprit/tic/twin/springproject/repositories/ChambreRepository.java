package com.esprit.tic.twin.springproject.repositories;

import com.esprit.tic.twin.springproject.entities.Chambre;
import com.esprit.tic.twin.springproject.entities.TypeChambre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChambreRepository extends JpaRepository<Chambre, Long> {
    List<Chambre> findByBlocNomBlocAndTypeC(String s, TypeChambre x);
    List<Chambre> findByReservationsEstValide(Boolean x);
    List<Chambre> findByBlocCapaciteBlocGreaterThanAndBlocNomBloc(Long x, String nom);
    List<Chambre> findByNumeroChambreIn(List<Long> numChambres);
    @Query("SELECT c FROM Chambre c WHERE c.bloc.nomBloc = :nombloc AND c.typeC = :typechambre")
    List<Chambre> retrieveChambres(@Param("nombloc") String s, @Param("typechambre") TypeChambre x);

    @Query("SELECT c FROM Chambre c JOIN c.reservations r WHERE r.estValide = :estValide")
    List<Chambre> retreiveChambresValide(@Param("estValide") Boolean x);

    @Query("SELECT c FROM Chambre c WHERE c.bloc.capaciteBloc > :capaciteBloc AND c.bloc.nomBloc = :nomBloc")
    List<Chambre> retreiveChambres(@Param("capaciteBloc") Long x, @Param("nomBloc") String nom);
}
