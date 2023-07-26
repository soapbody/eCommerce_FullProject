package com.Mateus_Ulrich.eCommerce_FullProject.model.dto.api.consultacnpj;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class EstabelecimentoDTO implements Serializable {
    private static final long seriaVersionUID = 1L;

    private String cnpj;
    private List<AtividadesSecundariasDTO> atividades_secundarias;
    private String cnpj_raiz;
    private String cnpj_ordem;
    private String cnpj_digito_verificador;
    private String tipo;
    private String nome_fantasia;
    private String situacao_cadastral;
    private Date data_situacao_cadastral;
    private Date data_inicio_atividade;
    private String nome_cidade_exterior;
    private String tipo_logradouro;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cep;
    private String ddd1;
    private String telefone1;
    private String ddd2;
    private String telefone2;
    private String ddd_fax;
    private String fax;
    private String email;
    private String situacao_especial;
    private Date data_situacao_especial;
    private String atualizado_em;
    private PaisDTO pais;
    private EstadoDTO estado;
    private CidadeDTO cidade;
    private String motivo_situacao_cadastral;
    private List<InscricoesEstaduaisDTO> inscricoes_estaduais;

    public EstabelecimentoDTO() {
    }

    public EstabelecimentoDTO(String cnpj, List<AtividadesSecundariasDTO> atividades_secundarias, String cnpj_raiz, String cnpj_ordem, String cnpj_digito_verificador, String tipo, String nome_fantasia, String situacao_cadastral, Date data_situacao_cadastral, Date data_inicio_atividade, String nome_cidade_exterior, String tipo_logradouro, String logradouro, String numero, String complemento, String bairro, String cep, String ddd1, String telefone1, String ddd2, String telefone2, String ddd_fax, String fax, String email, String situacao_especial, Date data_situacao_especial, String atualizado_em, PaisDTO pais, EstadoDTO estado, CidadeDTO cidade, String motivo_situacao_cadastral, List<InscricoesEstaduaisDTO> inscricoes_estaduais) {
        this.cnpj = cnpj;
        this.atividades_secundarias = atividades_secundarias;
        this.cnpj_raiz = cnpj_raiz;
        this.cnpj_ordem = cnpj_ordem;
        this.cnpj_digito_verificador = cnpj_digito_verificador;
        this.tipo = tipo;
        this.nome_fantasia = nome_fantasia;
        this.situacao_cadastral = situacao_cadastral;
        this.data_situacao_cadastral = data_situacao_cadastral;
        this.data_inicio_atividade = data_inicio_atividade;
        this.nome_cidade_exterior = nome_cidade_exterior;
        this.tipo_logradouro = tipo_logradouro;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cep = cep;
        this.ddd1 = ddd1;
        this.telefone1 = telefone1;
        this.ddd2 = ddd2;
        this.telefone2 = telefone2;
        this.ddd_fax = ddd_fax;
        this.fax = fax;
        this.email = email;
        this.situacao_especial = situacao_especial;
        this.data_situacao_especial = data_situacao_especial;
        this.atualizado_em = atualizado_em;
        this.pais = pais;
        this.estado = estado;
        this.cidade = cidade;
        this.motivo_situacao_cadastral = motivo_situacao_cadastral;
        this.inscricoes_estaduais = inscricoes_estaduais;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public List<AtividadesSecundariasDTO> getAtividades_secundarias() {
        return atividades_secundarias;
    }

    public void setAtividades_secundarias(List<AtividadesSecundariasDTO> atividades_secundarias) {
        this.atividades_secundarias = atividades_secundarias;
    }

    public String getCnpj_raiz() {
        return cnpj_raiz;
    }

    public void setCnpj_raiz(String cnpj_raiz) {
        this.cnpj_raiz = cnpj_raiz;
    }

    public String getCnpj_ordem() {
        return cnpj_ordem;
    }

    public void setCnpj_ordem(String cnpj_ordem) {
        this.cnpj_ordem = cnpj_ordem;
    }

    public String getCnpj_digito_verificador() {
        return cnpj_digito_verificador;
    }

    public void setCnpj_digito_verificador(String cnpj_digito_verificador) {
        this.cnpj_digito_verificador = cnpj_digito_verificador;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNome_fantasia() {
        return nome_fantasia;
    }

    public void setNome_fantasia(String nome_fantasia) {
        this.nome_fantasia = nome_fantasia;
    }

    public String getSituacao_cadastral() {
        return situacao_cadastral;
    }

    public void setSituacao_cadastral(String situacao_cadastral) {
        this.situacao_cadastral = situacao_cadastral;
    }

    public Date getData_situacao_cadastral() {
        return data_situacao_cadastral;
    }

    public void setData_situacao_cadastral(Date data_situacao_cadastral) {
        this.data_situacao_cadastral = data_situacao_cadastral;
    }

    public Date getData_inicio_atividade() {
        return data_inicio_atividade;
    }

    public void setData_inicio_atividade(Date data_inicio_atividade) {
        this.data_inicio_atividade = data_inicio_atividade;
    }

    public String getNome_cidade_exterior() {
        return nome_cidade_exterior;
    }

    public void setNome_cidade_exterior(String nome_cidade_exterior) {
        this.nome_cidade_exterior = nome_cidade_exterior;
    }

    public String getTipo_logradouro() {
        return tipo_logradouro;
    }

    public void setTipo_logradouro(String tipo_logradouro) {
        this.tipo_logradouro = tipo_logradouro;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getDdd1() {
        return ddd1;
    }

    public void setDdd1(String ddd1) {
        this.ddd1 = ddd1;
    }

    public String getTelefone1() {
        return telefone1;
    }

    public void setTelefone1(String telefone1) {
        this.telefone1 = telefone1;
    }

    public String getDdd2() {
        return ddd2;
    }

    public void setDdd2(String ddd2) {
        this.ddd2 = ddd2;
    }

    public String getTelefone2() {
        return telefone2;
    }

    public void setTelefone2(String telefone2) {
        this.telefone2 = telefone2;
    }

    public String getDdd_fax() {
        return ddd_fax;
    }

    public void setDdd_fax(String ddd_fax) {
        this.ddd_fax = ddd_fax;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSituacao_especial() {
        return situacao_especial;
    }

    public void setSituacao_especial(String situacao_especial) {
        this.situacao_especial = situacao_especial;
    }

    public Date getData_situacao_especial() {
        return data_situacao_especial;
    }

    public void setData_situacao_especial(Date data_situacao_especial) {
        this.data_situacao_especial = data_situacao_especial;
    }

    public String getAtualizado_em() {
        return atualizado_em;
    }

    public void setAtualizado_em(String atualizado_em) {
        this.atualizado_em = atualizado_em;
    }

    public PaisDTO getPais() {
        return pais;
    }

    public void setPais(PaisDTO pais) {
        this.pais = pais;
    }

    public EstadoDTO getEstado() {
        return estado;
    }

    public void setEstado(EstadoDTO estado) {
        this.estado = estado;
    }

    public CidadeDTO getCidade() {
        return cidade;
    }

    public void setCidade(CidadeDTO cidade) {
        this.cidade = cidade;
    }

    public String getMotivo_situacao_cadastral() {
        return motivo_situacao_cadastral;
    }

    public void setMotivo_situacao_cadastral(String motivo_situacao_cadastral) {
        this.motivo_situacao_cadastral = motivo_situacao_cadastral;
    }

    public List<InscricoesEstaduaisDTO> getInscricoes_estaduais() {
        return inscricoes_estaduais;
    }

    public void setInscricoes_estaduais(List<InscricoesEstaduaisDTO> inscricoes_estaduais) {
        this.inscricoes_estaduais = inscricoes_estaduais;
    }
}
