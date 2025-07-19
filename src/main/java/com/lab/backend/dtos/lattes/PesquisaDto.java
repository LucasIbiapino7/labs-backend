package com.lab.backend.dtos.lattes;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.lab.backend.dtos.profile.ProfileMinDto;
import com.lab.backend.model.pesquisas.Orientacao;
import com.lab.backend.model.pesquisas.Patente;
import com.lab.backend.model.pesquisas.Premio;
import com.lab.backend.model.pesquisas.Publicacao;

import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PesquisaDto {
    // campos sempre presentes
    private Integer id;
    private PesquisaTipo tipo;
    private ProfileMinDto author;
    // genéricos
    private String titulo;
    private Integer ano;
    // Publicação
    private String doi;
    private String issnIsbnSigla;
    // Prêmio
    private String nomePremio;
    private String entidade;
    //Patente
    private String codigo;
    private LocalDate dataDeposito;
    private LocalDate dataConcessao;
    //Orientaçã
    private String nivel;
    private String discente;
    private String instituicao;

    public PesquisaDto(Publicacao p) {
        this.id = p.getId();
        this.tipo = PesquisaTipo.PUBLICACAO;
        this.author = new ProfileMinDto(p.getAuthor());
        this.titulo = p.getTitulo();
        this.ano = p.getAno();
        this.doi = p.getDoi();
        this.issnIsbnSigla = p.getIssnIsbnSigla();
    }

    public PesquisaDto(Premio pr) {
        this.id = pr.getId();
        this.tipo = PesquisaTipo.PREMIO;
        this.author = new ProfileMinDto(pr.getAuthor());
        this.nomePremio = pr.getNomePremio();
        this.entidade = pr.getEntidade();
        this.ano = pr.getAno();
    }

    public PesquisaDto(Patente pa) {
        this.id = pa.getId();
        this.tipo = PesquisaTipo.PATENTE;
        this.author = new ProfileMinDto(pa.getAuthor());
        this.titulo = pa.getTitulo();
        this.codigo = pa.getCodigo();
        this.dataDeposito = pa.getDataDeposito();
        this.dataConcessao = pa.getDataConcessao();
    }

    public PesquisaDto(Orientacao o) {
        this.id= o.getId();
        this.tipo  = PesquisaTipo.ORIENTACAO;
        this.author = new ProfileMinDto(o.getAuthor());
        this.titulo  = o.getTitulo();
        this.ano = o.getAno();
        this.nivel = o.getNivel();
        this.discente = o.getDiscente();
        this.instituicao = o.getInstituicao();
    }

    public Integer getId() {
        return id;
    }

    public PesquisaTipo getTipo() {
        return tipo;
    }

    public ProfileMinDto getAuthor() {
        return author;
    }

    public String getTitulo() {
        return titulo;
    }

    public Integer getAno() {
        return ano;
    }

    public String getDoi() {
        return doi;
    }

    public String getIssnIsbnSigla() {
        return issnIsbnSigla;
    }

    public String getNomePremio() {
        return nomePremio;
    }

    public String getEntidade() {
        return entidade;
    }

    public String getCodigo() {
        return codigo;
    }

    public LocalDate getDataDeposito() {
        return dataDeposito;
    }

    public LocalDate getDataConcessao() {
        return dataConcessao;
    }

    public String getNivel() {
        return nivel;
    }

    public String getDiscente() {
        return discente;
    }

    public String getInstituicao() {
        return instituicao;
    }
}
