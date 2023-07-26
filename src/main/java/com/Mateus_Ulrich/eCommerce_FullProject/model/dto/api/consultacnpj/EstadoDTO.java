package com.Mateus_Ulrich.eCommerce_FullProject.model.dto.api.consultacnpj;

import java.io.Serializable;

public class EstadoDTO implements Serializable {
    private static final long seriaVersionUID = 1L;

    private Long id;
    private String nome;
    private String sigla;
    private Long ibge_id;

    public EstadoDTO(Long id, String name, String sigla, Long ibge_id) {
        this.id = id;
        this.nome = nome;
        this.sigla = sigla;
        this.ibge_id = ibge_id;
    }

    public EstadoDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return nome;
    }

    public void setName(String name) {
        this.nome = name;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public Long getIbge_id() {
        return ibge_id;
    }

    public void setIbge_id(Long ibge_id) {
        this.ibge_id = ibge_id;
    }
}
