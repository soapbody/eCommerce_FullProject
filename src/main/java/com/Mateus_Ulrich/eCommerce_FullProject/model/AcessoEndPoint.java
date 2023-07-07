package com.Mateus_Ulrich.eCommerce_FullProject.model;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Table(name = "tabela_acesso_end_point",
        uniqueConstraints = @UniqueConstraint(name = "nome_end_point_unique", columnNames = "nome_end_point"))

public class AcessoEndPoint implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome_end_point")
    private String nomeEndPoint;
    @Column(name = "qtd_acesso_end_point")
    private Integer qtdAcessoEndPoint;

    public String getNomeEndPoint() {
        return nomeEndPoint;
    }

    public void setNomeEndPoint(String nomeEndPoint) {
        this.nomeEndPoint = nomeEndPoint;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQtdAcessoEndPoint() {
        return qtdAcessoEndPoint;
    }

    public void setQtdAcessoEndPoint(Integer qtdAcessoEndPoint) {
        this.qtdAcessoEndPoint = qtdAcessoEndPoint;
    }
}
