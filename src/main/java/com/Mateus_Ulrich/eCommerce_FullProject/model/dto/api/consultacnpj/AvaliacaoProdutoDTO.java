package com.Mateus_Ulrich.eCommerce_FullProject.model.dto.api.consultacnpj;

import com.Mateus_Ulrich.eCommerce_FullProject.model.PessoaFisica;
import com.Mateus_Ulrich.eCommerce_FullProject.model.PessoaJuridica;
import com.Mateus_Ulrich.eCommerce_FullProject.model.Produto;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class AvaliacaoProdutoDTO {

    private Long id;
    private Integer nota;
    private String descricao;

    private PessoaFisica pessoa;
    private Produto produto;
    private PessoaJuridica empresa;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNota() {
        return nota;
    }

    public void setNota(Integer nota) {
        this.nota = nota;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public PessoaFisica getPessoa() {
        return pessoa;
    }

    public void setPessoa(PessoaFisica pessoa) {
        this.pessoa = pessoa;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public PessoaJuridica getEmpresa() {
        return empresa;
    }

    public void setEmpresa(PessoaJuridica empresa) {
        this.empresa = empresa;
    }
}
