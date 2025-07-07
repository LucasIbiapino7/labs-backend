package com.lab.backend.repositories;

import com.lab.backend.model.ProfileLaboratorio;
import com.lab.backend.model.ProfileLaboratorioPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProfileLaboratorioRepository extends JpaRepository<ProfileLaboratorio, ProfileLaboratorioPK> {

    boolean existsByIdLaboratorioIdAndIdProfileIdAndAdminTrue(Long labId, Long profileId);

}

