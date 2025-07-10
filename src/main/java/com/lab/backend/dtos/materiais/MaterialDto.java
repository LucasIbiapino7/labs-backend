package com.lab.backend.dtos.materiais;

import com.lab.backend.model.Material;
import com.lab.backend.model.enums.MaterialType;
import com.lab.backend.model.enums.Visibilidade;

public class MaterialDto {
    private Long id;
    private String titulo;
    private String descricao;
    private String link;
    private MaterialType tipo;
    private Visibilidade visibilidade;

    public MaterialDto() {
    }

    public MaterialDto(Material entity) {
        id = entity.getId();
        titulo = entity.getTitulo();
        descricao = entity.getDescricao();
        link = entity.getLink();
        tipo = entity.getTipo();
        visibilidade = entity.getVisibilidade();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public MaterialType getTipo() {
        return tipo;
    }

    public void setTipo(MaterialType tipo) {
        this.tipo = tipo;
    }

    public Visibilidade getVisibilidade() {
        return visibilidade;
    }

    public void setVisibilidade(Visibilidade visibilidade) {
        this.visibilidade = visibilidade;
    }
}
