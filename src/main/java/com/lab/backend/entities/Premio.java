//package com.lab.backend.entities;
//
//import jakarta.persistence.*;
//import org.hibernate.annotations.Immutable;
//
//@Immutable
//@Entity
//@Table(name = "premio")
//public class Premio {
//
//    @Id
//    @Column(name = "id_premio")
//    private Integer id;
//    @Column(name = "nome_premio")
//    private String nomePremio;
//    @Column(name = "entidade")
//    private String entidade;
//    @Column(name = "ano")
//    private String ano;
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "id_docente")
//    private Docente docente;
//
//    public Integer getId() {
//        return id;
//    }
//
//    public String getNomePremio() {
//        return nomePremio;
//    }
//
//    public String getEntidade() {
//        return entidade;
//    }
//
//    public String getAno() {
//        return ano;
//    }
//
//    public Docente getDocente() {
//        return docente;
//    }
//}