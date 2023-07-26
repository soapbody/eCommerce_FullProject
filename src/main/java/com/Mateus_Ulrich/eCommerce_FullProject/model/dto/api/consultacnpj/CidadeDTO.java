package com.Mateus_Ulrich.eCommerce_FullProject.model.dto.api.consultacnpj;

import java.io.Serializable;

public class CidadeDTO implements Serializable {
    private static final long seriaVersionUID = 1L;
    private Long id;
    private String nome;
    private Long ibge_id;
    private String siafi_id;

    public CidadeDTO(Long id, String nome, Long ibge_id, String siafi_id) {
        this.id = id;
        this.nome = nome;
        this.ibge_id = ibge_id;
        this.siafi_id = siafi_id;
    }

    public CidadeDTO() {
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

    public Long getIbge_id() {
        return ibge_id;
    }

    public void setIbge_id(Long ibge_id) {
        this.ibge_id = ibge_id;
    }

    public String getSiafi_id() {
        return siafi_id;
    }

    public void setSiafi_id(String siafi_id) {
        this.siafi_id = siafi_id;
    }
}
