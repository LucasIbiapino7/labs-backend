package com.lab.backend.dtos.lattes;

public class OrientacaoDto {
    private Integer id;
    private String tipo;
    private String titulo;
    private Integer ano;
    private String discente;

    public OrientacaoDto() {
    }

    public Integer getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public String getTitulo() {
        return titulo;
    }

    public Integer getAno() {
        return ano;
    }

    public String getDiscente() {
        return discente;
    }
}
