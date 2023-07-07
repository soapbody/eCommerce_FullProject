package com.Mateus_Ulrich.eCommerce_FullProject.model.dto;

import java.io.Serializable;

public class PorteDTO implements Serializable {
    private static final long seriaVersionUID = 1L;
    private String id;
    private String descricao;

    public PorteDTO(String id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public PorteDTO() {
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
