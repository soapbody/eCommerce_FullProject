package com.Mateus_Ulrich.eCommerce_FullProject.model.dto;

import java.io.Serializable;

public class AtividadePrincipalDTO implements Serializable {
    private static final long seriaVersionUID = 1L;
    private String id;
    private String secao;
    private String divisao;
    private String grupo;
    private String classe;
    private String subclasse;
    private String descricao;

    public AtividadePrincipalDTO(String id, String secao, String divisao, String grupo, String classe, String subclasse, String descricao) {
        this.id = id;
        this.secao = secao;
        this.divisao = divisao;
        this.grupo = grupo;
        this.classe = classe;
        this.subclasse = subclasse;
        this.descricao = descricao;
    }

    public AtividadePrincipalDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSecao() {
        return secao;
    }

    public void setSecao(String secao) {
        this.secao = secao;
    }

    public String getDivisao() {
        return divisao;
    }

    public void setDivisao(String divisao) {
        this.divisao = divisao;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public String getSubclasse() {
        return subclasse;
    }

    public void setSubclasse(String subclasse) {
        this.subclasse = subclasse;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
