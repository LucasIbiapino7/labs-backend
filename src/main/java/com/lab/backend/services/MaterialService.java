package com.lab.backend.services;

import com.lab.backend.dtos.materiais.MaterialDto;
import com.lab.backend.model.Laboratorio;
import com.lab.backend.model.Profile;
import com.lab.backend.model.enums.LabRole;
import com.lab.backend.model.enums.MaterialType;
import com.lab.backend.model.enums.Visibilidade;
import com.lab.backend.repositories.EventoRepository;
import com.lab.backend.repositories.LaboratorioRepository;
import com.lab.backend.repositories.MaterialRepository;
import com.lab.backend.repositories.ProfileLaboratorioRepository;
import com.lab.backend.services.exceptions.ForbiddenException;
import com.lab.backend.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MaterialService {

    @Autowired
    private AuthService authService;
    @Autowired
    private MaterialRepository materialRepository;
    @Autowired
    private ProfileLaboratorioRepository profileLaboratorioRepository;
    @Autowired
    private LaboratorioRepository laboratorioRepository;

    @Transactional(readOnly = true)
    public Page<MaterialDto> getMateriaisPublicos(Long labId, String nome, MaterialType tipo, Pageable pageable) {
        Laboratorio laboratorio = laboratorioRepository.findById(labId).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado!"));
        return materialRepository.findByLabAndFilters(labId, List.of(Visibilidade.PUBLICO), nome, tipo, pageable);
    }


    public Page<MaterialDto> getMateriaisPublicosEPrivados(Long labId, String nome, MaterialType tipo, Pageable pageable) {
        Laboratorio laboratorio = laboratorioRepository.findById(labId).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado!"));
        Profile profile = authService.getOrCreateProfile();
        boolean labMember = profileLaboratorioRepository.existsByIdLaboratorioIdAndIdProfileIdAndLabRoleIn(
                labId,
                profile.getId(),
                List.of(LabRole.ADMIN, LabRole.OWNER, LabRole.MEMBER));
        if (!labMember){
            throw new ForbiddenException("Você não tem acesso!");
        }
        return materialRepository.findByLabAndFilters(labId, List.of(Visibilidade.PUBLICO, Visibilidade.PRIVADO), nome, tipo, pageable);
    }
}
