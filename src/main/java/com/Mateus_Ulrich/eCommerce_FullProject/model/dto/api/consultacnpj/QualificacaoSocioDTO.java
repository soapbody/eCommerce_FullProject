package com.Mateus_Ulrich.eCommerce_FullProject.model.dto.api.consultacnpj;

import java.io.Serializable;

public class QualificacaoSocioDTO implements Serializable {
    private static final long seriaVersionUID = 1L;

    private Long id;
    private String descricao;

    public QualificacaoSocioDTO(Long id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public QualificacaoSocioDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
