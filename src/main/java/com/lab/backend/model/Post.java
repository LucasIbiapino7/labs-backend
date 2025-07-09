package com.lab.backend.model;

import com.lab.backend.model.enums.Visibilidade;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "tb_post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    @Column(columnDefinition = "TEXT")
    private String conteudo;
    private LocalDateTime instante;
    @Enumerated(EnumType.STRING)
    private Visibilidade visibilidade;
    @ManyToOne
    @JoinColumn(name = "lab_id")
    private Laboratorio laboratorio;
    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;

    public Post() {
    }

    public Post(Long id, String titulo, String conteudo, LocalDateTime intante, Visibilidade visibilidade, Laboratorio laboratorio, Profile profile) {
        this.id = id;
        this.titulo = titulo;
        this.conteudo = conteudo;
        this.instante = intante;
        this.visibilidade = visibilidade;
        this.laboratorio = laboratorio;
        this.profile = profile;
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

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public LocalDateTime getInstante() {
        return instante;
    }

    public void setInstante(LocalDateTime instante) {
        this.instante = instante;
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

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Post post = (Post) o;

        return Objects.equals(id, post.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
