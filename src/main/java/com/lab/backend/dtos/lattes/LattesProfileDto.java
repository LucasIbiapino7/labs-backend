package com.lab.backend.dtos.lattes;

import java.util.List;
import java.util.Objects;

public class LattesProfileDto {

    private List<ProducaoDto> producoes;
    private List<OrientacaoDto> orientacoes;
    private List<PremioDto> premios;
    private List<PatenteDto> patentes;

    public List<ProducaoDto> getProducoes() {
        return producoes;
    }

    public void setProducoes(List<ProducaoDto> producoes) {
        this.producoes = producoes;
    }

    public List<OrientacaoDto> getOrientacoes() {
        return orientacoes;
    }

    public void setOrientacoes(List<OrientacaoDto> orientacoes) {
        this.orientacoes = orientacoes;
    }

    public List<PremioDto> getPremios() {
        return premios;
    }

    public void setPremios(List<PremioDto> premios) {
        this.premios = premios;
    }

    public List<PatenteDto> getPatentes() {
        return patentes;
    }

    public void setPatentes(List<PatenteDto> patentes) {
        this.patentes = patentes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LattesProfileDto that = (LattesProfileDto) o;

        if (!Objects.equals(producoes, that.producoes)) return false;
        if (!Objects.equals(orientacoes, that.orientacoes)) return false;
        if (!Objects.equals(premios, that.premios)) return false;
        return Objects.equals(patentes, that.patentes);
    }

    @Override
    public int hashCode() {
        int result = producoes != null ? producoes.hashCode() : 0;
        result = 31 * result + (orientacoes != null ? orientacoes.hashCode() : 0);
        result = 31 * result + (premios != null ? premios.hashCode() : 0);
        result = 31 * result + (patentes != null ? patentes.hashCode() : 0);
        return result;
    }
}
