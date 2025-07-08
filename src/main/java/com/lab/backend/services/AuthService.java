package com.lab.backend.services;

import com.lab.backend.model.Profile;
import com.lab.backend.model.enums.ProfileType;
import com.lab.backend.repositories.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

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
            newProfile.setPhotoUrl(null);
            newProfile.setProfileType(ProfileType.ALUNO);
            return profileRepository.save(newProfile);
        });

        return profile;
    }
}
