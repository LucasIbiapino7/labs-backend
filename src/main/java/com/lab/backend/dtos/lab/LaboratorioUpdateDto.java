package com.lab.backend.dtos.lab;

import com.lab.backend.model.enums.GradientAccent;

public class LaboratorioUpdateDto {
    private String nome;
    private String descricaoCurta;
    private String descricaoLonga;
    private GradientAccent gradientAccent;

    public LaboratorioUpdateDto() {
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

    public GradientAccent getGradientAccent() {
        return gradientAccent;
    }

    public void setGradientAccent(GradientAccent gradientAccent) {
        this.gradientAccent = gradientAccent;
    }
}
