package com.lab.backend.repositories;

import com.lab.backend.dtos.lab.LabCardDto;
import com.lab.backend.model.Laboratorio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LaboratorioRepository extends JpaRepository<Laboratorio, Long> {

    @Query("""
       select distinct new com.lab.backend.dtos.lab.LabCardDto(lab, p)
       from Laboratorio lab
       join lab.profileLaboratorios pl
       join pl.id.profile p
       where pl.labRole = com.lab.backend.model.enums.LabRole.OWNER
       order by lab.nome
""")
    Page<LabCardDto> findAllPublic(Pageable pageable);
}
