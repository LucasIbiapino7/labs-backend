package com.lab.backend.dtos.lab;

public class LabSummaryDto {
    private Long id;
    private String nome;
    private String bannerGradient;
    private String logoUrl;
    private boolean isMember;
    private boolean isAdmin;
    private boolean isOwner;

    public LabSummaryDto() {
    }

    public LabSummaryDto(Long id, String nome, String bannerGradient, String logoUrl, boolean isMember, boolean isAdmin, boolean isOwner) {
        this.id = id;
        this.nome = nome;
        this.bannerGradient = bannerGradient;
        this.logoUrl = logoUrl;
        this.isMember = isMember;
        this.isAdmin = isAdmin;
        this.isOwner = isOwner;
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

    public String getBannerGradient() {
        return bannerGradient;
    }

    public void setBannerGradient(String bannerGradient) {
        this.bannerGradient = bannerGradient;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public boolean isMember() {
        return isMember;
    }

    public void setMember(boolean member) {
        isMember = member;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public boolean isOwner() {
        return isOwner;
    }

    public void setOwner(boolean owner) {
        isOwner = owner;
    }
}
