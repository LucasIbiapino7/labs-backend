package com.lab.backend.repositories;

import com.lab.backend.model.pesquisas.Publicacao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.nio.channels.FileChannel;
import java.util.Optional;

public interface PublicacaoRepository extends JpaRepository<Publicacao, Integer> {
    Optional<Publicacao> findByExternalId(Integer id);

    @Query("""
        select pub
          from Publicacao pub
          join fetch pub.author prof
          join prof.profileLaboratorios pl
         where pl.id.laboratorio.id = :labId
           and prof.profileType = com.lab.backend.model.enums.ProfileType.PROFESSOR
         ORDER BY pub.ano
        """)
    Page<Publicacao> findByLab(Long labId, Pageable pageable);
}
