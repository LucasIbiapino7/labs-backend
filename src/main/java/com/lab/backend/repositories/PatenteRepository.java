package com.lab.backend.repositories;

import com.lab.backend.model.pesquisas.Patente;
import com.lab.backend.model.pesquisas.Publicacao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PatenteRepository extends JpaRepository<Patente, Integer> {
    Optional<Patente> findByExternalId(Integer id);

    @Query("""
        select pat
          from Patente pat
          join fetch pat.author prof
          join prof.profileLaboratorios pl
         where pl.id.laboratorio.id = :labId
           and prof.profileType = com.lab.backend.model.enums.ProfileType.PROFESSOR
        """)
    Page<Patente> findByLab(Long labId, Pageable pageable);
}
