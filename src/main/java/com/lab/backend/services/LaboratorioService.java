package com.lab.backend.services;

import com.lab.backend.dtos.lab.*;
import com.lab.backend.dtos.profile.ProfileMemberDto;
import com.lab.backend.dtos.profile.ProfileMinDto;
import com.lab.backend.model.Laboratorio;
import com.lab.backend.model.Profile;
import com.lab.backend.model.ProfileLaboratorio;
import com.lab.backend.model.ProfileLaboratorioPK;
import com.lab.backend.model.enums.GradientAccent;
import com.lab.backend.model.enums.LabRole;
import com.lab.backend.repositories.LaboratorioRepository;
import com.lab.backend.repositories.ProfileLaboratorioRepository;
import com.lab.backend.repositories.ProfileRepository;
import com.lab.backend.services.exceptions.DatabaseException;
import com.lab.backend.services.exceptions.ForbiddenException;
import com.lab.backend.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LaboratorioService {

    @Autowired
    private LaboratorioRepository laboratorioRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private ProfileLaboratorioRepository profileLaboratorioRepository;

    @Autowired
    private AuthService authService;

    public LaboratorioInfosDto findById(Long id) {
        Profile profile = authService.getOrCreateProfile();
        Laboratorio laboratorio = laboratorioRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado!"));
        if (!profileLaboratorioRepository.existsByIdLaboratorioIdAndIdProfileIdAndAdminTrue(laboratorio.getId(), profile.getId())){
            throw new ForbiddenException("Voce não tem acesso!");
        }
        return new LaboratorioInfosDto(laboratorio);
    }

    @Transactional
    public LaboratorioInfosDto insert(LaboratorioCreateDto dto) {
        Laboratorio laboratorio = new Laboratorio();
        laboratorio.setNome(dto.getNome());
        laboratorio.setDescricaoCurta("");
        laboratorio.setDescricaoLonga("");
        laboratorio.setLogoUrl("");
        laboratorio.setGradientAccent(GradientAccent.GREEN);
        laboratorio = laboratorioRepository.save(laboratorio);
        return new LaboratorioInfosDto(laboratorio);
    }

    @Transactional
    public LaboratorioInfosDto update(Long id, LaboratorioUpdateDto dto) {
        Profile profile = authService.getOrCreateProfile(); // Pega o user do contexto
        Laboratorio laboratorio = laboratorioRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado!"));
        // verifica se o usuario e admin desse lab
        if (!profileLaboratorioRepository.existsByIdLaboratorioIdAndIdProfileIdAndAdminTrue(laboratorio.getId(), profile.getId())){
            throw new ForbiddenException("Voce não tem acesso!");
        }
        laboratorio.setNome(dto.getNome());
        laboratorio.setDescricaoCurta(dto.getDescricaoCurta());
        laboratorio.setDescricaoLonga(dto.getDescricaoLonga());
        laboratorio.setGradientAccent(dto.getGradientAccent());
        laboratorio = laboratorioRepository.save(laboratorio);
        return new LaboratorioInfosDto(laboratorio);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id){
        if (!laboratorioRepository.existsById(id)){
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
        try {
            laboratorioRepository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DatabaseException("Falha de integridade referencial");
        }
    }

    @Transactional
    public void updateLabRoleOwner(Long labId, Long profileId) {
        Laboratorio laboratorio = laboratorioRepository.findById(labId).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado!"));
        Profile profile = profileRepository.findById(profileId).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado!"));

        ProfileLaboratorioPK pk = new ProfileLaboratorioPK(laboratorio, profile);

        ProfileLaboratorio relacionamento = profileLaboratorioRepository.findById(pk)
                .orElseGet(() -> {// Cria se nao existe
                    ProfileLaboratorio novo = new ProfileLaboratorio();
                    novo.setId(pk);
                    novo.setAtivo(true);
                    return novo;
                });
        relacionamento.setAdmin(true);
        relacionamento.setLabRole(LabRole.OWNER);
        profileLaboratorioRepository.save(relacionamento);
    }

    @Transactional
    public void addMember(LabAddMemberDto dto, Long labId) {
        Laboratorio laboratorio = laboratorioRepository.findById(labId).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado!"));
        Profile profile = profileRepository.findById(dto.getUserId()).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado!"));

        Profile current = authService.getOrCreateProfile();
        boolean labAdmin = profileLaboratorioRepository.existsByIdLaboratorioIdAndIdProfileIdAndLabRoleIn(
                        labId,
                        current.getId(),
                        List.of(LabRole.ADMIN, LabRole.OWNER));

        boolean globalAdmin = authService.hasGlobalRole("ROLE_ADMIN");

        if (!labAdmin && !globalAdmin) {
            throw new ForbiddenException("Você não tem acesso!");
        }

        ProfileLaboratorioPK pk = new ProfileLaboratorioPK(laboratorio, profile);

        ProfileLaboratorio relacionamento = profileLaboratorioRepository.findById(pk)
                .orElseGet(() -> {// Cria se nao existe
                    ProfileLaboratorio novo = new ProfileLaboratorio();
                    novo.setId(pk);
                    novo.setAtivo(true);
                    return novo;
                });
        LabRole labRole = (dto.getLabRole() != null) ? dto.getLabRole() : LabRole.MEMBER;
        relacionamento.setLabRole(labRole);
        relacionamento.setAdmin(labRole.equals(LabRole.ADMIN));
        profileLaboratorioRepository.save(relacionamento);
    }

    @Transactional(readOnly = true)
    public LabSummaryDto summary(Long labId) {
        Laboratorio laboratorio = laboratorioRepository.findById(labId).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado!"));

        Long profileId = authService.currentProfileIdOrNull(); // Null se for visitante (sem token)

        boolean isMember = false;
        boolean isAdmin = false;
        boolean isOwner = false;

        if (profileId != null){
            LabRole role = profileLaboratorioRepository.findRole(labId, profileId)
                    .orElse(null);
            isMember = role != null;
            isAdmin = role == LabRole.ADMIN || role == LabRole.OWNER;
            isOwner = role == LabRole.OWNER;;
        }

        return new LabSummaryDto(laboratorio.getId(), laboratorio.getNome(),
                laboratorio.getGradientAccent().name(), laboratorio.getLogoUrl(), isMember, isAdmin, isOwner);

    }

    @Transactional(readOnly = true)
    public Page<ProfileMemberDto> getAllMembers(Long labId, String nome, Pageable pageable) {
        Laboratorio laboratorio = laboratorioRepository.findById(labId).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado!"));
        Page<ProfileMemberDto> members = profileLaboratorioRepository.getAllMembers(labId, nome, pageable);
        return members;
    }
}
