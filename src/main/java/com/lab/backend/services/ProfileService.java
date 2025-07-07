package com.lab.backend.services;

import com.lab.backend.dtos.profile.ProfileMinDto;
import com.lab.backend.dtos.profile.ProfileUpdateDto;
import com.lab.backend.model.Profile;
import com.lab.backend.repositories.ProfileRepository;
import com.lab.backend.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
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
        // Pegar o usuário do contexto (por enquanto é um valor mockado)
        Profile profile = authService.getOrCreateProfile();
        return new ProfileMinDto(profile);
    }

    @Transactional
    public ProfileMinDto update(ProfileUpdateDto dto) {
        // Pegar o usuário do contexto (por enquanto é um valor mockado)
        Profile profile = authService.getOrCreateProfile();
        dtoToEntity(dto, profile);
        profile = profileRepository.save(profile);
        return new ProfileMinDto(profile);
    }

    private void dtoToEntity(ProfileUpdateDto dto, Profile profile) {
        profile.setNome(dto.getNome());
        profile.setBio(dto.getBio());
        profile.setLinkLattes(((dto.getLinkLattes()) != null) ? dto.getLinkLattes() : "");
        profile.setLinkLinkedin(((dto.getLinkGithub()) != null) ? dto.getLinkGithub() : "");
        profile.setLinkLinkedin(((dto.getLinkLinkedin()) != null) ? dto.getLinkLinkedin() : "");
    }
}
