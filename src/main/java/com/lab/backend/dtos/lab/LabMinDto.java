package com.lab.backend.dtos.lab;

import com.lab.backend.model.Laboratorio;

public class LabMinDto {
    private Long id;
    private String nome;

    public LabMinDto() {
    }

    public LabMinDto(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public LabMinDto(Laboratorio entity) {
        id = entity.getId();
        nome = entity.getNome();
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
}
