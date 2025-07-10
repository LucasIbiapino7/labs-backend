package com.lab.backend.services;

import com.lab.backend.model.Profile;
import com.lab.backend.model.enums.ProfileType;
import com.lab.backend.repositories.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class AuthService {

    @Autowired
    private ProfileRepository profileRepository;

    public Profile getOrCreateProfile(){
        Jwt jwt = (Jwt) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        String sub = jwt.getSubject();
        String name = jwt.getClaimAsString("name");

        Profile profile = profileRepository.findBySub(sub).orElseGet(() -> {
            Profile newProfile = new Profile();
            newProfile.setNome(name);
            newProfile.setSub(sub);
            newProfile.setBio("");
            newProfile.setLinkLattes("");
            newProfile.setLinkGithub("");
            newProfile.setLinkLinkedin("");
            newProfile.setIdLattes("");
            newProfile.setPhotoUrl(null);
            newProfile.setProfileType(ProfileType.ALUNO);
            return profileRepository.save(newProfile);
        });

        return profile;
    }

    public Long currentProfileIdOrNull() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !(auth.getPrincipal() instanceof Jwt jwt)) {
            return null;
        }
        return profileRepository.findIdBySub(jwt.getSubject())
                .orElse(null);
    }

    // 	recupera todas as roles globais do usuário como Set<String
    public Set<String> currentAuthorities() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) return Collections.emptySet();
        return auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet());
    }
    // Testa se o usuário possui uma role específica.
    public boolean hasGlobalRole(String role) {
        String roleWithPrefix = role.startsWith("ROLE_") ? role : "ROLE_" + role;
        return currentAuthorities().contains(roleWithPrefix);
    }

    private Optional<Jwt> currentJwt() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return (auth != null && auth.getPrincipal() instanceof Jwt jwt)
                ? Optional.of(jwt)
                : Optional.empty();
    }
}
