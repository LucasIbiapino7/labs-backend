package com.lab.backend.services;

import com.lab.backend.dtos.profile.ProfileMinDto;
import com.lab.backend.dtos.profile.ProfileUpdateDto;
import com.lab.backend.model.Profile;
import com.lab.backend.model.enums.ProfileType;
import com.lab.backend.repositories.ProfileRepository;
import com.lab.backend.services.exceptions.ForbiddenException;
import com.lab.backend.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private AuthService authService;

    public ProfileMinDto findById(Long id) {
        Profile result = profileRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso nao encontrado!"));
        return new ProfileMinDto(result);
    }

    public ProfileMinDto getMe() {
        Profile profile = authService.getOrCreateProfile();
        return new ProfileMinDto(profile);
    }

    @Transactional
    public ProfileMinDto update(ProfileUpdateDto dto) {
        Profile profile = authService.getOrCreateProfile();
        dtoToEntity(dto, profile);
        profile = profileRepository.save(profile);
        return new ProfileMinDto(profile);
    }

    private void dtoToEntity(ProfileUpdateDto dto, Profile profile) {
        profile.setNome(dto.getNome());
        profile.setBio(dto.getBio());
        profile.setLinkLattes(((dto.getLinkLattes()) != null) ? dto.getLinkLattes() : "");
        profile.setLinkGithub(((dto.getLinkGithub()) != null) ? dto.getLinkGithub() : "");
        profile.setLinkLinkedin(((dto.getLinkLinkedin()) != null) ? dto.getLinkLinkedin() : "");
        profile.setIdLattes(((dto.getIdLattes()) != null) ? dto.getIdLattes() : "");
    }

    public Page<ProfileMinDto> findByName(String nome, Pageable pageable) {
        if (!authService.hasGlobalRole("ROLE_ADMIN")) {
            throw new ForbiddenException("Acesso negado.");
        }
        Profile profile = authService.getOrCreateProfile();
        Page<Profile> profiles = profileRepository.findByName(nome, profile.getId(), pageable);
        return profiles.map(ProfileMinDto::new);
    }

    @Transactional
    public ProfileMinDto updateProfileType(Long profileId, ProfileType newType) {
        if (!authService.hasGlobalRole("ROLE_ADMIN")) {
            throw new ForbiddenException("Acesso negado.");
        }
        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(() -> new ResourceNotFoundException("Perfil não encontrado."));
        if (profile.getProfileType() == newType) {
            return new ProfileMinDto(profile);
        }
        profile.setProfileType(newType);
        profile = profileRepository.save(profile);
        return new ProfileMinDto(profile);
    }
}
