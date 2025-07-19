package com.lab.backend.repositories;

import com.lab.backend.model.pesquisas.Orientacao;
import com.lab.backend.model.pesquisas.Publicacao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface OrientacaoRepository extends JpaRepository<Orientacao, Integer> {
    Optional<Orientacao> findByExternalId(Integer id);

    @Query("""
        select o
          from Orientacao o
          join fetch o.author prof
          join prof.profileLaboratorios pl
         where pl.id.laboratorio.id = :labId
           and prof.profileType = com.lab.backend.model.enums.ProfileType.PROFESSOR
         ORDER BY o.ano
    """)
    Page<Orientacao> findByLab(Long labId, Pageable pageable);
}
