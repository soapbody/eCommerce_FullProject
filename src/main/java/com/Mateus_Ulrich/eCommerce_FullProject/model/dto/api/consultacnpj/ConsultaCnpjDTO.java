package com.Mateus_Ulrich.eCommerce_FullProject.model.dto.api.consultacnpj;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ConsultaCnpjDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String cnpj_raiz;
    private String razao_social;
    private String responsavel_federativo;
    private Date atualizado_em;
    private PorteDTO porte;
    private NaturezaJuridicaDTO natureza_juridica;
    private QualificacaoDoResponsavelDTO qualificacao_do_responsavel;
    private List<SociosDTO> socios;
    private SimplesDTO simples;
    private EstabelecimentoDTO estabelecimento;

    public ConsultaCnpjDTO() {
    }

    public ConsultaCnpjDTO(String cnpj_raiz, String razao_social, String responsavel_federativo, Date atualizado_em, PorteDTO porte, NaturezaJuridicaDTO natureza_juridica, QualificacaoDoResponsavelDTO qualificacao_do_responsavel, List<SociosDTO> socios, SimplesDTO simples, EstabelecimentoDTO estabelecimento) {
        this.cnpj_raiz = cnpj_raiz;
        this.razao_social = razao_social;
        this.responsavel_federativo = responsavel_federativo;
        this.atualizado_em = atualizado_em;
        this.porte = porte;
        this.natureza_juridica = natureza_juridica;
        this.qualificacao_do_responsavel = qualificacao_do_responsavel;
        this.socios = socios;
        this.simples = simples;
        this.estabelecimento = estabelecimento;
    }

    public String getCnpj_raiz() {
        return cnpj_raiz;
    }

    public void setCnpj_raiz(String cnpj_raiz) {
        this.cnpj_raiz = cnpj_raiz;
    }

    public String getRazao_social() {
        return razao_social;
    }

    public void setRazao_social(String razao_social) {
        this.razao_social = razao_social;
    }

    public String getResponsavel_federativo() {
        return responsavel_federativo;
    }

    public void setResponsavel_federativo(String responsavel_federativo) {
        this.responsavel_federativo = responsavel_federativo;
    }

    public Date getAtualizado_em() {
        return atualizado_em;
    }

    public void setAtualizado_em(Date atualizado_em) {
        this.atualizado_em = atualizado_em;
    }

    public PorteDTO getPorte() {
        return porte;
    }

    public void setPorte(PorteDTO porte) {
        this.porte = porte;
    }

    public NaturezaJuridicaDTO getNatureza_juridica() {
        return natureza_juridica;
    }

    public void setNatureza_juridica(NaturezaJuridicaDTO natureza_juridica) {
        this.natureza_juridica = natureza_juridica;
    }

    public QualificacaoDoResponsavelDTO getQualificacao_do_responsavel() {
        return qualificacao_do_responsavel;
    }

    public void setQualificacao_do_responsavel(QualificacaoDoResponsavelDTO qualificacao_do_responsavel) {
        this.qualificacao_do_responsavel = qualificacao_do_responsavel;
    }

    public List<SociosDTO> getSocios() {
        return socios;
    }

    public void setSocios(List<SociosDTO> socios) {
        this.socios = socios;
    }

    public SimplesDTO getSimples() {
        return simples;
    }

    public void setSimples(SimplesDTO simples) {
        this.simples = simples;
    }

    public EstabelecimentoDTO getEstabelecimento() {
        return estabelecimento;
    }

    public void setEstabelecimento(EstabelecimentoDTO estabelecimento) {
        this.estabelecimento = estabelecimento;
    }
}
