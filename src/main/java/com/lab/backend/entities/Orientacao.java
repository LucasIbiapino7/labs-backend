//package com.lab.backend.entities;
//
//import jakarta.persistence.*;
//import org.hibernate.annotations.Immutable;
//
//@Immutable
//@Entity
//@Table(name = "orientacao")
//public class Orientacao {
//    @Id
//    @Column(name = "id_orientacao")
//    private Integer id;
//    @Column(name = "tipo")
//    private String tipo;// "MESTRADO", "DOUTORADO"
//    @Column(name = "titulo")
//    private String titulo;
//    @Column(name = "ano")
//    private Integer ano;
//    @Column(name = "discente")
//    private String discente;
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "id_docente")
//    private Docente docente;
//
//    public Integer getId() {
//        return id;
//    }
//
//    public String getTipo() {
//        return tipo;
//    }
//
//    public String getTitulo() {
//        return titulo;
//    }
//
//    public Integer getAno() {
//        return ano;
//    }
//
//    public String getDiscente() {
//        return discente;
//    }
//
//    public Docente getDocente() {
//        return docente;
//    }
//}
