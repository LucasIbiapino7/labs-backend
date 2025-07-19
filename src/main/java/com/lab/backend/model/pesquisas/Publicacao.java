package com.lab.backend.model.pesquisas;

import com.lab.backend.model.Profile;
import com.lab.backend.model.enums.PublicationType;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "tb_publicacao")
public class Publicacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id", nullable = false)
    private Profile author;
//    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private String tipo;
    @Column(nullable = false)
    private String titulo;
    private Integer ano;
    private String doi;
    @Column(name = "issn_isbn_sigla")
    private String issnIsbnSigla;
    @Column(name = "external_id", unique = true, nullable = false)
    private Integer externalId;

    public Publicacao() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Profile getAuthor() {
        return author;
    }

    public void setAuthor(Profile author) {
        this.author = author;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public String getDoi() {
        return doi;
    }

    public void setDoi(String doi) {
        this.doi = doi;
    }

    public String getIssnIsbnSigla() {
        return issnIsbnSigla;
    }

    public void setIssnIsbnSigla(String issnIsbnSigla) {
        this.issnIsbnSigla = issnIsbnSigla;
    }

    public Integer getExternalId() {
        return externalId;
    }

    public void setExternalId(Integer externalId) {
        this.externalId = externalId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Publicacao that = (Publicacao) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
