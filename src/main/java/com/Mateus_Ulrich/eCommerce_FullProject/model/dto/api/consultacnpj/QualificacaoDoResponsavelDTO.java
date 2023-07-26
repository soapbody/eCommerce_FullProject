package com.Mateus_Ulrich.eCommerce_FullProject.model.dto.api.consultacnpj;

import java.io.Serializable;

public class QualificacaoDoResponsavelDTO implements Serializable {
    private static final long seriaVersionUID = 1L;
    private Long id;
    private String descricao;

    public QualificacaoDoResponsavelDTO(Long id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public QualificacaoDoResponsavelDTO() {
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
