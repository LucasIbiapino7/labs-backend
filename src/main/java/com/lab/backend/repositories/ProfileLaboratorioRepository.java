package com.lab.backend.repositories;

import com.lab.backend.model.ProfileLaboratorio;
import com.lab.backend.model.ProfileLaboratorioPK;
import com.lab.backend.model.enums.LabRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ProfileLaboratorioRepository extends JpaRepository<ProfileLaboratorio, ProfileLaboratorioPK> {

    boolean existsByIdLaboratorioIdAndIdProfileIdAndLabRoleIn(Long labId, Long profileId, Collection<LabRole> roles);

    boolean existsByIdLaboratorioIdAndIdProfileIdAndAdminTrue(Long labId, Long profileId);

    @Query("""
       select obj.labRole
       from ProfileLaboratorio obj
       where obj.id.laboratorio.id = :labId
         and obj.id.profile.id     = :profileId
   """)
    Optional<LabRole> findRole(Long labId, Long profileId);

}

