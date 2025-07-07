package com.lab.backend.dtos.lab;

import com.lab.backend.model.Laboratorio;
import com.lab.backend.model.enums.GradientAccent;

public class LaboratorioInfosDto {
    private Long id;
    private String nome;
    private String descricaoCurta;
    private String descricaoLonga;
    private String logoUrl;
    private GradientAccent gradientAccent;     // enum, não String

    public LaboratorioInfosDto() { }

    public LaboratorioInfosDto(Laboratorio entity) {
        this.id             = entity.getId();
        this.nome           = entity.getNome();
        this.descricaoCurta = entity.getDescricaoCurta();
        this.descricaoLonga = entity.getDescricaoLonga();
        this.logoUrl        = entity.getLogoUrl();
        this.gradientAccent = entity.getGradientAccent();   // sem toString()
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

    public String getDescricaoLonga() {
        return descricaoLonga;
    }

    public void setDescricaoLonga(String descricaoLonga) {
        this.descricaoLonga = descricaoLonga;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public GradientAccent getGradientAccent() {
        return gradientAccent;
    }

    public void setGradientAccent(GradientAccent gradientAccent) {
        this.gradientAccent = gradientAccent;
    }
}
