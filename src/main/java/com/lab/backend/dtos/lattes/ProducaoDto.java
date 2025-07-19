package com.lab.backend.dtos.lattes;

public class ProducaoDto {
    private Integer id;
    private String tipo;
    private String titulo;
    private Integer ano;
    private String doi;
    private String issnIsbnSigla; // Pode ser null

    public ProducaoDto() {
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

    public String getDoi() {
        return doi;
    }

    public String getIssnIsbnSigla() {
        return issnIsbnSigla;
    }
}
