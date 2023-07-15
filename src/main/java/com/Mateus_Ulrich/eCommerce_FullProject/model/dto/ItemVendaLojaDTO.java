package com.Mateus_Ulrich.eCommerce_FullProject.model.dto;

import com.Mateus_Ulrich.eCommerce_FullProject.model.*;
public class ItemVendaLojaDTO {
    private Long id;
    private Double quantidade;
    private Produto produto;

    public ItemVendaLojaDTO(ItemVendaLoja itemVendaLoja) {
        id = itemVendaLoja.getId();
        quantidade = itemVendaLoja.getQuantidade();
        produto = itemVendaLoja.getProduto();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
}
