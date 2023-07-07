package com.Mateus_Ulrich.eCommerce_FullProject.model.dto;

public class QualificacaoRepresentanteDTO {
    private Long id;
    private String descricao;

    public QualificacaoRepresentanteDTO(Long id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public QualificacaoRepresentanteDTO() {
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
