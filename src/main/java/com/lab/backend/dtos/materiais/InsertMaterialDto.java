package com.lab.backend.dtos.materiais;

import com.lab.backend.model.Material;
import com.lab.backend.model.enums.MaterialType;
import com.lab.backend.model.enums.Visibilidade;
import jakarta.validation.constraints.NotBlank;

public class InsertMaterialDto {
    @NotBlank(message = "campo requerido")
    private String titulo;
    @NotBlank(message = "campo requerido")
    private String descricao;
    @NotBlank(message = "campo requerido")
    private String link;
    private MaterialType tipo;
    private Visibilidade visibilidade;

    public InsertMaterialDto() {
    }

    public InsertMaterialDto(Material entity) {
        titulo = entity.getTitulo();
        descricao = entity.getDescricao();
        link = entity.getLink();
        tipo = entity.getTipo();
        visibilidade = entity.getVisibilidade();
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
