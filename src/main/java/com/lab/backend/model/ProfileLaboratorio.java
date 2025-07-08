package com.lab.backend.model;

import com.lab.backend.model.enums.LabRole;
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
    @Column(name = "lab_role")
    private LabRole labRole;
    private Boolean admin;

    public ProfileLaboratorio() {
    }

    public ProfileLaboratorio(ProfileLaboratorioPK id, Boolean ativo, LabRole labRole, Boolean admin) {
        this.id = id;
        this.ativo = ativo;
        this.labRole = labRole;
        this.admin = admin;
    }

    public Laboratorio getLaboratorio(){
        return id.getLaboratorio();
    }

    public void setId(ProfileLaboratorioPK id) {
        this.id = id;
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

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public LabRole getLabRole() {
        return labRole;
    }

    public void setLabRole(LabRole labRole) {
        this.labRole = labRole;
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
