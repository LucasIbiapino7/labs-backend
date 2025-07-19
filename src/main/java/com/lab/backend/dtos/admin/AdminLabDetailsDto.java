package com.lab.backend.dtos.admin;

public class AdminLabDetailsDto {
    private Long id;
    private String nome;
    private String descricaoCurta;
    private String descricaoLonga;
    private Long ownerId;
    private String ownerNome;
    private String ownerEmail;
    private Integer totalMembros;
    private String gradientAccent;
    private String logoUrl;

    public AdminLabDetailsDto() {
    }

    public AdminLabDetailsDto(Long id, String nome, String descricaoCurta, String descricaoLonga, Long ownerId, String ownerNome, String ownerEmail, Integer totalMembros, String gradientAccent, String logoUrl) {
        this.id = id;
        this.nome = nome;
        this.descricaoCurta = descricaoCurta;
        this.descricaoLonga = descricaoLonga;
        this.ownerId = ownerId;
        this.ownerNome = ownerNome;
        this.ownerEmail = ownerEmail;
        this.totalMembros = totalMembros;
        this.gradientAccent = gradientAccent;
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

    public String getDescricaoLonga() {
        return descricaoLonga;
    }

    public void setDescricaoLonga(String descricaoLonga) {
        this.descricaoLonga = descricaoLonga;
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

    public String getGradientAccent() {
        return gradientAccent;
    }

    public void setGradientAccent(String gradientAccent) {
        this.gradientAccent = gradientAccent;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }
}
