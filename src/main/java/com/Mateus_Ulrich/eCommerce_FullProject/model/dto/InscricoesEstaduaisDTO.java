package com.Mateus_Ulrich.eCommerce_FullProject.model.dto;

import java.io.Serializable;
import java.sql.Date;

public class InscricoesEstaduaisDTO implements Serializable {
    private static final long seriaVersionUID = 1L;

    private String inscricao_estadual;
    private Boolean ativo;
    private Date atualizado_em;
    private EstadoDTO estado;

    public InscricoesEstaduaisDTO(String inscricao_estadual, Boolean ativo, Date atualizado_em, EstadoDTO estado) {
        this.inscricao_estadual = inscricao_estadual;
        this.ativo = ativo;
        this.atualizado_em = atualizado_em;
        this.estado = estado;
    }

    public InscricoesEstaduaisDTO() {
    }

    public String getInscricao_estadual() {
        return inscricao_estadual;
    }

    public void setInscricao_estadual(String inscricao_estadual) {
        this.inscricao_estadual = inscricao_estadual;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Date getAtualizado_em() {
        return atualizado_em;
    }

    public void setAtualizado_em(Date atualizado_em) {
        this.atualizado_em = atualizado_em;
    }

    public EstadoDTO getEstado() {
        return estado;
    }

    public void setEstado(EstadoDTO estado) {
        this.estado = estado;
    }
}
