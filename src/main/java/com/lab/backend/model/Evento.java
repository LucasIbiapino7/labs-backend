package com.lab.backend.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "tb_evento")
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    @Column(columnDefinition = "TEXT")
    private String descricao;
    @Column(name = "data_evento")
    private LocalDateTime dataEvento;
    private LocalDateTime instante;
    private String local;
    @ManyToOne
    @JoinColumn(name = "lab_id")
    private Laboratorio laboratorio;
    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;

    public Evento() {
    }

    public Evento(Long id, String titulo, String descricao, LocalDateTime date, LocalDateTime instante, String local, Laboratorio laboratorio, Profile profile) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.instante = instante;
        this.local = local;
        this.laboratorio = laboratorio;
        this.profile = profile;
        this.dataEvento = date;
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

    public LocalDateTime getDataEvento() {
        return dataEvento;
    }

    public void setDataEvento(LocalDateTime dataEvento) {
        this.dataEvento = dataEvento;
    }

    public LocalDateTime getInstante() {
        return instante;
    }

    public void setInstante(LocalDateTime instante) {
        this.instante = instante;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
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

        Evento evento = (Evento) o;

        return Objects.equals(id, evento.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
