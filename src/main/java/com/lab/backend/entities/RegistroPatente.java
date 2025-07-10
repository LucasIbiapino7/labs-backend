//package com.lab.backend.entities;
//
//import jakarta.persistence.*;
//import org.hibernate.annotations.Immutable;
//
//@Immutable
//@Entity
//@Table(name = "registro_patente")
//public class RegistroPatente {
//
//    @Id
//    @Column(name = "id_registro_patente")
//    private Integer id;
//    @Column(name = "tipo")
//    private String tipo; // "PATENTE", "PROGRAMA"
//    @Column(name = "titulo")
//    private String titulo;
//    @Column(name = "data_deposito")
//    private String dataDeposito; // no dump ta como texto
//    @Column(name = "cod_registro")
//    private String codigo;
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
//    public String getDataDeposito() {
//        return dataDeposito;
//    }
//
//    public String getCodigo() {
//        return codigo;
//    }
//
//    public Docente getDocente() {
//        return docente;
//    }
//}
//
