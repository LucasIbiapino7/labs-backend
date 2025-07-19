package com.lab.backend.services;

import com.lab.backend.dtos.admin.AdminLabListDto;
import com.lab.backend.dtos.lab.*;
import com.lab.backend.dtos.lattes.PesquisaDto;
import com.lab.backend.dtos.lattes.PesquisaTipo;
import com.lab.backend.dtos.profile.LabMemberAdminDto;
import com.lab.backend.dtos.profile.ProfileMemberDto;
import com.lab.backend.dtos.profile.ProfileMinDto;
import com.lab.backend.model.Laboratorio;
import com.lab.backend.model.Profile;
import com.lab.backend.model.ProfileLaboratorio;
import com.lab.backend.model.ProfileLaboratorioPK;
import com.lab.backend.model.enums.GradientAccent;
import com.lab.backend.model.enums.LabRole;
import com.lab.backend.repositories.*;
import com.lab.backend.services.exceptions.DatabaseException;
import com.lab.backend.services.exceptions.ForbiddenException;
import com.lab.backend.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

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
    @Autowired
    private PublicacaoRepository publicacaoRepository;
    @Autowired
    private OrientacaoRepository orientacaoRepository;
    @Autowired
    private PremioRepository premioRepository;
    @Autowired
    private PatenteRepository patenteRepository;

    public LaboratorioInfosDto findById(Long id) {
        Profile profile = authService.getOrCreateProfile();
        Laboratorio laboratorio = laboratorioRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado!"));
        boolean labAdmin = profileLaboratorioRepository.existsByIdLaboratorioIdAndIdProfileIdAndLabRoleIn(
                id,
                profile.getId(),
                List.of(LabRole.ADMIN, LabRole.OWNER));
        if (!labAdmin){
            throw new ForbiddenException("Voce não tem acesso!");
        }
        return new LaboratorioInfosDto(laboratorio);
    }

    @Transactional
    public LaboratorioInfosDto insert(LaboratorioCreateDto dto) {
        Laboratorio laboratorio = new Laboratorio();
        laboratorio.setNome(dto.getNome());
        laboratorio.setDescricaoCurta(dto.getDescricaoCurta());
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
        boolean labAdmin = profileLaboratorioRepository.existsByIdLaboratorioIdAndIdProfileIdAndLabRoleIn(
                id,
                profile.getId(),
                List.of(LabRole.ADMIN, LabRole.OWNER));
        if (!labAdmin){
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
        Laboratorio laboratorio = laboratorioRepository.findById(labId)
                .orElseThrow(() -> new ResourceNotFoundException("Laboratório não encontrado!"));
        Profile novoOwner = profileRepository.findById(profileId)
                .orElseThrow(() -> new ResourceNotFoundException("Profile não encontrado!"));

        // Busca owners atuais
        List<ProfileLaboratorio> ownersAtuais = profileLaboratorioRepository.findOwners(labId);

        boolean jaEOwner = ownersAtuais.stream()
                .anyMatch(rel -> rel.getProfile().getId().equals(profileId));
        if (jaEOwner) {
            return;
        }

        // Rebaixa owners antigos para MEMBER (mantém ativo e remove admin)
        for (ProfileLaboratorio antigoOwner : ownersAtuais) {
            antigoOwner.setLabRole(LabRole.MEMBER);
            antigoOwner.setAdmin(false);
            profileLaboratorioRepository.save(antigoOwner);
        }

        ProfileLaboratorioPK pk = new ProfileLaboratorioPK(laboratorio, novoOwner);
        ProfileLaboratorio relacionamento = profileLaboratorioRepository.findById(pk)
                .orElseGet(() -> {
                    ProfileLaboratorio novo = new ProfileLaboratorio();
                    novo.setId(pk);
                    novo.setAtivo(true);
                    return novo;
                });

        relacionamento.setLabRole(LabRole.OWNER);
        relacionamento.setAdmin(true);
        relacionamento.setAtivo(true);
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
                        List.of(LabRole.OWNER));

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

    public Page<ProfileMinDto> listCandidates(Long labId, String nome, Pageable pageable) {
        Profile profile = authService.getOrCreateProfile();
        boolean isOwner = profileLaboratorioRepository.existsByIdLaboratorioIdAndIdProfileIdAndLabRoleIn(
                labId,
                profile.getId(),
                List.of(LabRole.OWNER)); // APENAS O OWNER
        boolean globalAdmin = authService.hasGlobalRole("ROLE_ADMIN");
        if (!isOwner && !globalAdmin) {
            throw new ForbiddenException("Você não tem acesso!");
        }
        Page<Profile> list = profileLaboratorioRepository.findCandidatesForLab(labId, nome, pageable);
        return list.map(ProfileMinDto::new);
    }

    public Page<LabCardDto> list(Pageable pageable) {
        return laboratorioRepository.findAllPublic(pageable);
    }


    public Page<PesquisaDto> findByLabAndTipo(Long labId, PesquisaTipo tipo, Pageable pageable) {
        if (tipo == null) {
            throw new IllegalArgumentException("O parâmetro 'tipo' é obrigatório.");
        }
        return switch (tipo) {
            case PUBLICACAO -> publicacaoRepository
                    .findByLab(labId, pageable)
                    .map(PesquisaDto::new);

            case ORIENTACAO -> orientacaoRepository
                    .findByLab(labId, pageable)
                    .map(PesquisaDto::new);

            case PREMIO -> premioRepository
                    .findByLab(labId, pageable)
                    .map(PesquisaDto::new);

            case PATENTE -> patenteRepository
                    .findByLab(labId, pageable)
                    .map(PesquisaDto::new);
        };
    }

    public Page<LabMemberAdminDto> allMembersAdmin(Long labId, String nome, Pageable pageable) {
        Profile profile = authService.getOrCreateProfile();
        boolean isOwner = profileLaboratorioRepository.existsByIdLaboratorioIdAndIdProfileIdAndLabRoleIn(
                labId,
                profile.getId(),
                List.of(LabRole.OWNER));
        boolean globalAdmin = authService.hasGlobalRole("ROLE_ADMIN");
        if (!isOwner && !globalAdmin) {
            throw new ForbiddenException("Você não tem acesso!");
        }
        Page<LabMemberAdminDto> page = profileLaboratorioRepository.findAdminViewMembers(labId, nome, pageable);

        long activeAdmins = profileLaboratorioRepository.countActiveAdmins(labId); // só ADMIN (não OWNER)

        page.forEach(dto -> {
            LabRole role   = dto.getLabRole();
            boolean ativo  = dto.isAtivo();
            boolean owner  = role == LabRole.OWNER;
            boolean admin  = role == LabRole.ADMIN;
            boolean member = role == LabRole.MEMBER;

            // Promote: só faz sentido para MEMBER ativo
            dto.setCanPromote(ativo && member);

            // Demote: só para ADMIN ativo, e se houver >1 admin ativo (não conta owner)
            dto.setCanDemote(ativo && admin && activeAdmins > 1);

            // Desativar: qualquer um ativo que não seja OWNER
            dto.setCanDeactivate(ativo && !owner);

            // Reativar: qualquer um inativo
            dto.setCanReactivate(!ativo);

            // Owner nunca sofre ações
            if (owner) {
                dto.setCanPromote(false);
                dto.setCanDemote(false);
                dto.setCanDeactivate(false);
            }
        });

        return page;
    }

    @Transactional
    public void deactivateMember(Long labId, Long memberProfileId) {
        Laboratorio lab = laboratorioRepository.findById(labId).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado!"));
        Profile current = authService.getOrCreateProfile();
        boolean isOwner = profileLaboratorioRepository
                .existsByIdLaboratorioIdAndIdProfileIdAndLabRoleIn(
                        labId, current.getId(), List.of(LabRole.OWNER));
        boolean isGlobalAdmin = authService.hasGlobalRole("ROLE_ADMIN");
        if (!isOwner && !isGlobalAdmin) {
            throw new ForbiddenException("Você não tem acesso!");
        }
        Profile target = profileRepository.findById(memberProfileId).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado!"));
        ProfileLaboratorioPK pk = new ProfileLaboratorioPK(lab, target);
        ProfileLaboratorio rel = profileLaboratorioRepository.findById(pk)
                .orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado!"));
        if (rel.getLabRole() == LabRole.OWNER) {
            throw new ForbiddenException("Não é possível desativar o OWNER.");
        }
        if (current.getId().equals(memberProfileId) && !isGlobalAdmin) {
            throw new ForbiddenException("Você não pode se desativar.");
        }
        if (Boolean.FALSE.equals(rel.getAtivo())) {
            return;
        }
        rel.setAtivo(false);
        profileLaboratorioRepository.save(rel);
    }

    @Transactional
    public void reactivateMember(Long labId, Long memberProfileId) {
        Laboratorio lab = laboratorioRepository.findById(labId).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado!"));
        Profile current = authService.getOrCreateProfile();
        boolean isOwner = profileLaboratorioRepository
                .existsByIdLaboratorioIdAndIdProfileIdAndLabRoleIn(
                        labId, current.getId(), List.of(LabRole.OWNER));
        boolean isGlobalAdmin = authService.hasGlobalRole("ROLE_ADMIN");
        if (!isOwner && !isGlobalAdmin) {
            throw new ForbiddenException("Você não tem acesso!");
        }
        Profile target = profileRepository.findById(memberProfileId).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado!"));
        ProfileLaboratorioPK pk = new ProfileLaboratorioPK(lab, target);
        ProfileLaboratorio rel = profileLaboratorioRepository.findById(pk)
                .orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado!"));
        if (Boolean.TRUE.equals(rel.getAtivo())) {
            return;
        }
        rel.setAtivo(true);
        profileLaboratorioRepository.save(rel);
    }

    @Transactional(readOnly = true)
    public Page<AdminLabListDto> listLabsAdminView(Pageable pageable) {
        if (!authService.hasGlobalRole("ROLE_ADMIN")) {
            throw new ForbiddenException("Acesso negado.");
        }

        Page<Laboratorio> page = laboratorioRepository.searchAll(null, pageable);

        if (page.isEmpty()) {
            return Page.empty(pageable);
        }

        List<Long> labIds = page.getContent().stream()
                .map(Laboratorio::getId)
                .toList();

        // Para cada lab: ownerId e count
        Map<Long, Long> ownerByLab = new HashMap<>();
        Map<Long, Integer> memberCountByLab = new HashMap<>();

        for (Long labId : labIds) {
            Long ownerId = laboratorioRepository.findOwnerId(labId);
            ownerByLab.put(labId, ownerId);

            long count = laboratorioRepository.countMembers(labId);
            memberCountByLab.put(labId, (int) count);
        }

        List<Long> ownerIds = ownerByLab.values().stream()
                .filter(Objects::nonNull)
                .distinct()
                .toList();

        Map<Long, Profile> ownersMap = ownerIds.isEmpty()
                ? Collections.emptyMap()
                : profileRepository.findAllById(ownerIds).stream()
                .collect(Collectors.toMap(Profile::getId, p -> p));

        // Monta DTOs
        List<AdminLabListDto> dtoList = page.getContent().stream()
                .map(lab -> {
                    Long ownerId = ownerByLab.get(lab.getId());
                    Profile owner = ownerId != null ? ownersMap.get(ownerId) : null;
                    return new AdminLabListDto(
                            lab.getId(),
                            lab.getNome(),
                            lab.getDescricaoCurta(),
                            ownerId,
                            owner != null ? owner.getNome() : null,
                            owner != null ? owner.getSub() : null,
                            memberCountByLab.getOrDefault(lab.getId(), 0),
                            lab.getLogoUrl()
                    );
                })
                .toList();

        return new PageImpl<>(dtoList, pageable, page.getTotalElements());
    }
}
