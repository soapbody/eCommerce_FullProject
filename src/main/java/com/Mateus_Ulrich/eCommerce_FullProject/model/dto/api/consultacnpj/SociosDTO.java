package com.Mateus_Ulrich.eCommerce_FullProject.model.dto.api.consultacnpj;

import java.io.Serializable;
import java.util.Date;

public class SociosDTO implements Serializable {
    private static final long seriaVersionUID = 1L;

    private String cpf_cnpj_socio;
    private String nome;
    private String tipo;
    private String name;
    private String data_entrada;
    private String cpf_representante_legal;
    private String nome_representante;
    private String faixa_etaria;
    private Date atualizado_em;
    private QualificacaoSocioDTO qualificacao_socio;
    private QualificacaoRepresentanteDTO qualificacao_representante;
    private PaisDTO pais;

    public SociosDTO() {
    }

    public SociosDTO(String cpf_cnpj_socio, String nome, String tipo, String name, String data_entrada, String cpf_representante_legal, String nome_representante, String faixa_etaria, Date atualizado_em, QualificacaoSocioDTO qualificacao_socio, QualificacaoRepresentanteDTO qualificacao_representante, PaisDTO pais) {
        this.cpf_cnpj_socio = cpf_cnpj_socio;
        this.nome = nome;
        this.tipo = tipo;
        this.name = name;
        this.data_entrada = data_entrada;
        this.cpf_representante_legal = cpf_representante_legal;
        this.nome_representante = nome_representante;
        this.faixa_etaria = faixa_etaria;
        this.atualizado_em = atualizado_em;
        this.qualificacao_socio = qualificacao_socio;
        this.qualificacao_representante = qualificacao_representante;
        this.pais = pais;
    }

    public String getCpf_cnpj_socio() {
        return cpf_cnpj_socio;
    }

    public void setCpf_cnpj_socio(String cpf_cnpj_socio) {
        this.cpf_cnpj_socio = cpf_cnpj_socio;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getData_entrada() {
        return data_entrada;
    }

    public void setData_entrada(String data_entrada) {
        this.data_entrada = data_entrada;
    }

    public String getCpf_representante_legal() {
        return cpf_representante_legal;
    }

    public void setCpf_representante_legal(String cpf_representante_legal) {
        this.cpf_representante_legal = cpf_representante_legal;
    }

    public String getNome_representante() {
        return nome_representante;
    }

    public void setNome_representante(String nome_representante) {
        this.nome_representante = nome_representante;
    }

    public String getFaixa_etaria() {
        return faixa_etaria;
    }

    public void setFaixa_etaria(String faixa_etaria) {
        this.faixa_etaria = faixa_etaria;
    }

    public Date getAtualizado_em() {
        return atualizado_em;
    }

    public void setAtualizado_em(Date atualizado_em) {
        this.atualizado_em = atualizado_em;
    }

    public QualificacaoSocioDTO getQualificacao_socio() {
        return qualificacao_socio;
    }

    public void setQualificacao_socio(QualificacaoSocioDTO qualificacao_socio) {
        this.qualificacao_socio = qualificacao_socio;
    }

    public QualificacaoRepresentanteDTO getQualificacao_representante() {
        return qualificacao_representante;
    }

    public void setQualificacao_representante(QualificacaoRepresentanteDTO qualificacao_representante) {
        this.qualificacao_representante = qualificacao_representante;
    }

    public PaisDTO getPais() {
        return pais;
    }

    public void setPais(PaisDTO pais) {
        this.pais = pais;
    }
}
