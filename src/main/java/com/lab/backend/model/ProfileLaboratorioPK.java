package com.lab.backend.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.util.Objects;

@Embeddable
public class ProfileLaboratorioPK {
    @ManyToOne
    @JoinColumn(name = "lab_id")
    private Laboratorio laboratorio;
    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;

    public ProfileLaboratorioPK() {
    }

    public ProfileLaboratorioPK(Laboratorio laboratorio, Profile profile) {
        this.laboratorio = laboratorio;
        this.profile = profile;
    }

    public Laboratorio getLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(Laboratorio laboratorio) {
        this.laboratorio = laboratorio;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProfileLaboratorioPK that = (ProfileLaboratorioPK) o;

        if (!Objects.equals(laboratorio, that.laboratorio)) return false;
        return Objects.equals(profile, that.profile);
    }

    @Override
    public int hashCode() {
        int result = laboratorio != null ? laboratorio.hashCode() : 0;
        result = 31 * result + (profile != null ? profile.hashCode() : 0);
        return result;
    }
}
