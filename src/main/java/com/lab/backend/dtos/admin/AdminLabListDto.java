package com.lab.backend.dtos.admin;

public class AdminLabListDto {
    private Long id;
    private String nome;
    private String descricaoCurta;
    private Long ownerId;
    private String ownerNome;
    private String ownerEmail;
    private Integer totalMembros;
    private String logoUrl;

    public AdminLabListDto() {
    }

    public AdminLabListDto(Long id, String nome, String descricaoCurta, Long ownerId, String ownerNome, String ownerEmail, Integer totalMembros, String logoUrl) {
        this.id = id;
        this.nome = nome;
        this.descricaoCurta = descricaoCurta;
        this.ownerId = ownerId;
        this.ownerNome = ownerNome;
        this.ownerEmail = ownerEmail;
        this.totalMembros = totalMembros == null ? 0 : totalMembros.intValue();
        this.logoUrl = logoUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricaoCurta() {
        return descricaoCurta;
    }

    public void setDescricaoCurta(String descricaoCurta) {
        this.descricaoCurta = descricaoCurta;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public String getOwnerNome() {
        return ownerNome;
    }

    public void setOwnerNome(String ownerNome) {
        this.ownerNome = ownerNome;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    public Integer getTotalMembros() {
        return totalMembros;
    }

    public void setTotalMembros(Integer totalMembros) {
        this.totalMembros = totalMembros;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }
}
