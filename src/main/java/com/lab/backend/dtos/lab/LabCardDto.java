package com.lab.backend.dtos.lab;

import com.lab.backend.dtos.profile.ProfileMinDto;
import com.lab.backend.model.Laboratorio;
import com.lab.backend.model.Profile;

public class LabCardDto {
    private Long id;
    private String nome;
    private String descricaoCurta;
    private String logoUrl;
    private ProfileMinDto professorResponsavel;
    private String accentColor;

    public LabCardDto() {
    }

    public LabCardDto(Laboratorio entityLab, Profile entityProfile) {
        id = entityLab.getId();
        nome = entityLab.getNome();
        descricaoCurta = entityLab.getDescricaoCurta();
        logoUrl = entityLab.getLogoUrl();
        professorResponsavel = new ProfileMinDto(entityProfile);
        accentColor = entityLab.getGradientAccent().toString().toLowerCase();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricaoCurta() {
        return descricaoCurta;
    }

    public void setDescricaoCurta(String descricaoCurta) {
        this.descricaoCurta = descricaoCurta;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public ProfileMinDto getProfessorResponsavel() {
        return professorResponsavel;
    }

    public void setProfessorResponsavel(ProfileMinDto professorResponsavel) {
        this.professorResponsavel = professorResponsavel;
    }

    public String getAccentColor() {
        return accentColor;
    }

    public void setAccentColor(String accentColor) {
        this.accentColor = accentColor;
    }
}
