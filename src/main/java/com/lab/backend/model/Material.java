package com.lab.backend.model;

import com.lab.backend.model.enums.MaterialType;
import com.lab.backend.model.enums.Visibilidade;
import jakarta.persistence.*;

@Entity
@Table(name = "tb_material")
public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String material;
    private String link;
    @Enumerated(EnumType.STRING)
    private MaterialType tipo;
    @Enumerated(EnumType.STRING)
    private Visibilidade visibilidade;
    @ManyToOne
    @JoinColumn(name = "lab_id")
    private Laboratorio laboratorio;
}
