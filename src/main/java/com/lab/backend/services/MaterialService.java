package com.lab.backend.services;

import com.lab.backend.dtos.materiais.InsertMaterialDto;
import com.lab.backend.dtos.materiais.MaterialDto;
import com.lab.backend.dtos.materiais.UpdateMaterialDto;
import com.lab.backend.model.Laboratorio;
import com.lab.backend.model.Material;
import com.lab.backend.model.Profile;
import com.lab.backend.model.enums.LabRole;
import com.lab.backend.model.enums.MaterialType;
import com.lab.backend.model.enums.Visibilidade;
import com.lab.backend.repositories.EventoRepository;
import com.lab.backend.repositories.LaboratorioRepository;
import com.lab.backend.repositories.MaterialRepository;
import com.lab.backend.repositories.ProfileLaboratorioRepository;
import com.lab.backend.services.exceptions.DatabaseException;
import com.lab.backend.services.exceptions.ForbiddenException;
import com.lab.backend.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

    @Transactional
    public MaterialDto insert(Long labId, InsertMaterialDto dto) {
        Laboratorio laboratorio = laboratorioRepository.findById(labId).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado!"));
        Profile profile = authService.getOrCreateProfile();
        boolean labAdmin = profileLaboratorioRepository.existsByIdLaboratorioIdAndIdProfileIdAndLabRoleIn(
                labId,
                profile.getId(),
                List.of(LabRole.ADMIN, LabRole.OWNER));
        if (!labAdmin){
            throw new ForbiddenException("Você não tem acesso!");
        }
        Material material = new Material();
        material.setTitulo(dto.getTitulo());
        material.setDescricao(dto.getDescricao());
        material.setLink(dto.getLink());
        material.setTipo(dto.getTipo());
        material.setVisibilidade(dto.getVisibilidade());
        material.setLaboratorio(laboratorio);
        material = materialRepository.save(material);
        return new MaterialDto(material);
    }

    @Transactional
    public MaterialDto update(Long labId, Long materialId, UpdateMaterialDto dto) {
        Laboratorio laboratorio = laboratorioRepository.findById(labId).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado!"));
        Material material = materialRepository.findById(materialId).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado!"));
        Profile profile = authService.getOrCreateProfile();
        boolean labAdmin = profileLaboratorioRepository.existsByIdLaboratorioIdAndIdProfileIdAndLabRoleIn(
                labId,
                profile.getId(),
                List.of(LabRole.ADMIN, LabRole.OWNER));
        if (!labAdmin){
            throw new ForbiddenException("Você não tem acesso!");
        }
        material.setTitulo(dto.getTitulo());
        material.setDescricao(dto.getDescricao());
        material.setLink(dto.getLink());
        material.setTipo(dto.getTipo());
        material.setVisibilidade(dto.getVisibilidade());
        material.setLaboratorio(laboratorio);
        material = materialRepository.save(material);
        return new MaterialDto(material);
    }

    public void delete(Long eventoId, Long labId) {
        Profile profile = authService.getOrCreateProfile();
        boolean labAdmin = profileLaboratorioRepository.existsByIdLaboratorioIdAndIdProfileIdAndLabRoleIn(
                labId,
                profile.getId(),
                List.of(LabRole.ADMIN, LabRole.OWNER));
        if (!labAdmin){
            throw new ForbiddenException("Você não tem acesso!");
        }
        if (!materialRepository.existsById(eventoId)){
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
        try {
            materialRepository.deleteById(eventoId);
        }catch (DataIntegrityViolationException e){
            throw new DatabaseException("Falha de integridade referencial");
        }
    }
}
