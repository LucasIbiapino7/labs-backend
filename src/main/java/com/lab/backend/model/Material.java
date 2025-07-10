package com.lab.backend.model;

import com.lab.backend.model.enums.MaterialType;
import com.lab.backend.model.enums.Visibilidade;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "tb_material")
public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String descricao;
    private String link;
    @Enumerated(EnumType.STRING)
    private MaterialType tipo;
    @Enumerated(EnumType.STRING)
    private Visibilidade visibilidade;
    @ManyToOne
    @JoinColumn(name = "lab_id")
    private Laboratorio laboratorio;

    public Material() {
    }

    public Material(Long id, String titulo, String descricao, String link, MaterialType tipo, Visibilidade visibilidade, Laboratorio laboratorio) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.link = link;
        this.tipo = tipo;
        this.visibilidade = visibilidade;
        this.laboratorio = laboratorio;
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

    public Laboratorio getLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(Laboratorio laboratorio) {
        this.laboratorio = laboratorio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Material material = (Material) o;

        return Objects.equals(id, material.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
