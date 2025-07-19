package com.lab.backend.repositories;

import com.lab.backend.model.pesquisas.Orientacao;
import com.lab.backend.model.pesquisas.Premio;
import com.lab.backend.model.pesquisas.Publicacao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PremioRepository extends JpaRepository<Premio, Integer> {
    Optional<Premio> findByExternalId(Integer id);

    @Query("""
        select pre
          from Premio pre
          join fetch pre.author prof
          join prof.profileLaboratorios pl
         where pl.id.laboratorio.id = :labId
           and prof.profileType = com.lab.backend.model.enums.ProfileType.PROFESSOR
         ORDER BY pre.ano
        """)
    Page<Premio> findByLab(Long labId, Pageable pageable);
}
