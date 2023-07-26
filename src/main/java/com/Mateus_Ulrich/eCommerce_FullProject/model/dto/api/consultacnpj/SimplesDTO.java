package com.Mateus_Ulrich.eCommerce_FullProject.model.dto.api.consultacnpj;

import java.io.Serializable;
import java.util.Date;

public class SimplesDTO implements Serializable {
    private static final long seriaVersionUID = 1L;

    private String simples;
    private String data_opcao_simples;
    private String data_exclusao_simples;
    private String mei;
    private String data_opcao_mei;
    private String data_exclusao_mei;
    private Date atualizado_em;

    public SimplesDTO(String simples, String data_opcao_simples, String data_exclusao_simples, String mei, String data_opcao_mei, String data_exclusao_mei, Date atualizado_em) {
        this.simples = simples;
        this.data_opcao_simples = data_opcao_simples;
        this.data_exclusao_simples = data_exclusao_simples;
        this.mei = mei;
        this.data_opcao_mei = data_opcao_mei;
        this.data_exclusao_mei = data_exclusao_mei;
        this.atualizado_em = atualizado_em;
    }

    public SimplesDTO() {
    }

    public String getSimples() {
        return simples;
    }

    public void setSimples(String simples) {
        this.simples = simples;
    }

    public String getData_opcao_simples() {
        return data_opcao_simples;
    }

    public void setData_opcao_simples(String data_opcao_simples) {
        this.data_opcao_simples = data_opcao_simples;
    }

    public String getData_exclusao_simples() {
        return data_exclusao_simples;
    }

    public void setData_exclusao_simples(String data_exclusao_simples) {
        this.data_exclusao_simples = data_exclusao_simples;
    }

    public String getMei() {
        return mei;
    }

    public void setMei(String mei) {
        this.mei = mei;
    }

    public String getData_opcao_mei() {
        return data_opcao_mei;
    }

    public void setData_opcao_mei(String data_opcao_mei) {
        this.data_opcao_mei = data_opcao_mei;
    }

    public String getData_exclusao_mei() {
        return data_exclusao_mei;
    }

    public void setData_exclusao_mei(String data_exclusao_mei) {
        this.data_exclusao_mei = data_exclusao_mei;
    }

    public Date getAtualizado_em() {
        return atualizado_em;
    }

    public void setAtualizado_em(Date atualizado_em) {
        this.atualizado_em = atualizado_em;
    }
}
