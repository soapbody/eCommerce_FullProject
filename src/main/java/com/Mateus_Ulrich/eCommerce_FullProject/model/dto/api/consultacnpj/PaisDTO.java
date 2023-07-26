package com.Mateus_Ulrich.eCommerce_FullProject.model.dto.api.consultacnpj;

import java.io.Serializable;

public class PaisDTO implements Serializable {
    private static final long seriaVersionUID = 1L;
    private String id;
    private String iso2;
    private String iso3;
    private String nome;
    private String comex_id;

    public PaisDTO(String id, String iso2, String iso3, String nome, String comex_id) {
        this.id = id;
        this.iso2 = iso2;
        this.iso3 = iso3;
        this.nome = nome;
        this.comex_id = comex_id;
    }

    public PaisDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIso2() {
        return iso2;
    }

    public void setIso2(String iso2) {
        this.iso2 = iso2;
    }

    public String getIso3() {
        return iso3;
    }

    public void setIso3(String iso3) {
        this.iso3 = iso3;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getComex_id() {
        return comex_id;
    }

    public void setComex_id(String comex_id) {
        this.comex_id = comex_id;
    }
}
