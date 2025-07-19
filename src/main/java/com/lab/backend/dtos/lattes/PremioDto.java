package com.lab.backend.dtos.lattes;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PremioDto {
    private Integer id;
    @JsonProperty("nomePremio")
    private String nomePremio;
    private String entidade;
    private Integer ano;

    public PremioDto() {
    }

    public Integer getId() {
        return id;
    }

    public String getNomePremio() {
        return nomePremio;
    }

    public String getEntidade() {
        return entidade;
    }

    public Integer getAno() {
        return ano;
    }
}
