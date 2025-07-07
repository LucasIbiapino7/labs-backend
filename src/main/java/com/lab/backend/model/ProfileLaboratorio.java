package com.lab.backend.model;

import com.lab.backend.model.enums.ProfileType;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "tb_profile_laboratorio")
public class ProfileLaboratorio {
    @EmbeddedId
    private ProfileLaboratorioPK id = new ProfileLaboratorioPK();
    private Boolean ativo;
    @Enumerated(EnumType.STRING)
    private ProfileType profileType;
    private Boolean admin;

    public ProfileLaboratorio() {
    }

    public ProfileLaboratorio(Laboratorio laboratorio, Profile profile, Boolean ativo, ProfileType profileType, Boolean admin) {
        id.setLaboratorio(laboratorio);
        id.setProfile(profile);
        this.ativo = ativo;
        this.profileType = profileType;
        this.admin = admin;
    }

    public Laboratorio getLaboratorio(){
        return id.getLaboratorio();
    }

    public void setLaboratorio(Laboratorio laboratorio){
        id.setLaboratorio(laboratorio);
    }

    public Profile getProfile(){
        return id.getProfile();
    }

    public void setProfile(Profile profile){
        id.setProfile(profile);
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public ProfileType getProfileType() {
        return profileType;
    }

    public void setProfileType(ProfileType profileType) {
        this.profileType = profileType;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProfileLaboratorio that = (ProfileLaboratorio) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
