package com.lab.backend.utils;

import com.lab.backend.dtos.lattes.OrientacaoDto;
import com.lab.backend.dtos.lattes.PatenteDto;
import com.lab.backend.dtos.lattes.PremioDto;
import com.lab.backend.dtos.lattes.ProducaoDto;
import com.lab.backend.model.Profile;
import com.lab.backend.model.enums.OrientacaoNivel;
import com.lab.backend.model.enums.PublicationType;
import com.lab.backend.model.pesquisas.Orientacao;
import com.lab.backend.model.pesquisas.Patente;
import com.lab.backend.model.pesquisas.Premio;
import com.lab.backend.model.pesquisas.Publicacao;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class LattesMapper {

    public Publicacao toPublicacao(ProducaoDto dto, Profile profile) {
        if (dto == null || profile == null) {
            return null;
        }

        Publicacao p = new Publicacao();
        p.setAuthor(profile);
        p.setTipo(dto.getTipo());
        p.setTitulo(dto.getTitulo());
        p.setAno(dto.getAno());
        p.setDoi(dto.getDoi());
        p.setIssnIsbnSigla(dto.getIssnIsbnSigla());
        p.setExternalId(dto.getId());
        return p;
    }

    public List<Publicacao> toPublicacaoList(List<ProducaoDto> dtos, Profile profile) {
        if (dtos == null) return Collections.emptyList();
        return dtos.stream()
                .map(dto -> toPublicacao(dto, profile))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public Orientacao toOrientacao(OrientacaoDto dto, Profile profile) {
        if (dto == null || profile == null) {
            return null;
        }

        Orientacao o = new Orientacao();
        o.setAuthor(profile);
        o.setNivel(dto.getTipo());
        o.setTitulo(dto.getTitulo());
        o.setAno(dto.getAno());
        o.setDiscente(dto.getDiscente());
        o.setExternalId(dto.getId());

        return o;
    }

    public List<Orientacao> toOrientacaoList(List<OrientacaoDto> dtos, Profile profile) {
        if (dtos == null) return Collections.emptyList();
        return dtos.stream()
                .map(dto -> toOrientacao(dto, profile))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }


    public Premio toPremio(PremioDto dto, Profile profile) {
        if (dto == null || profile == null) {
            return null;
        }
        Premio p = new Premio();
        p.setAuthor(profile);
        p.setNomePremio(dto.getNomePremio());
        p.setEntidade(dto.getEntidade());
        p.setAno(dto.getAno());
        p.setExternalId(dto.getId());
        return p;
    }

    public List<Premio> toPremioList(List<PremioDto> dtos, Profile profile) {
        if (dtos == null) return Collections.emptyList();
        return dtos.stream()
                .map(dto -> toPremio(dto, profile))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public Patente toPatente(PatenteDto dto, Profile profile) {
        if (dto == null || profile == null) return null;

        Patente p = new Patente();
        p.setAuthor(profile);
        p.setTipo(dto.getTipo());
        p.setTitulo(dto.getTitulo());
        p.setCodigo(dto.getCodigo());
        p.setDataDeposito(dto.getDataDeposito());
        p.setExternalId(dto.getId());

        return p;
    }

    public List<Patente> toPatenteList(List<PatenteDto> dtos, Profile profile) {
        if (dtos == null) return Collections.emptyList();
        return dtos.stream()
                .map(dto -> toPatente(dto, profile))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}