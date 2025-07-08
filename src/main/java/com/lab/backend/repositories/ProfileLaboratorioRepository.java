package com.lab.backend.repositories;

import com.lab.backend.model.ProfileLaboratorio;
import com.lab.backend.model.ProfileLaboratorioPK;
import com.lab.backend.model.enums.LabRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface ProfileLaboratorioRepository extends JpaRepository<ProfileLaboratorio, ProfileLaboratorioPK> {

    boolean existsByIdLaboratorioIdAndIdProfileIdAndLabRoleIn(Long labId, Long profileId, Collection<LabRole> roles);

    boolean existsByIdLaboratorioIdAndIdProfileIdAndAdminTrue(Long labId, Long profileId);

}

