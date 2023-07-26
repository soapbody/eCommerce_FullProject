package com.Mateus_Ulrich.eCommerce_FullProject.model.dto.api.consultacnpj;

import java.io.Serializable;

public class NaturezaJuridicaDTO implements Serializable {
    private static final long seriaVersionUID = 1L;

    private String id;
    private String descricao;

    public NaturezaJuridicaDTO(String id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }


    public NaturezaJuridicaDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
