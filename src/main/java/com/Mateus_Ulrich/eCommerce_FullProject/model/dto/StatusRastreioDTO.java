package com.Mateus_Ulrich.eCommerce_FullProject.model.dto;

import com.Mateus_Ulrich.eCommerce_FullProject.model.StatusRastreio;

public class StatusRastreioDTO {
    private Long id;
    private Long idVenda;
    private String centroDistribuicao;

    private String cidade;

    private String estado;

    private String status;

    public StatusRastreioDTO(StatusRastreio statusRastreio) {
        id = statusRastreio.getId();
        centroDistribuicao = statusRastreio.getCentroDistribuicao();
        cidade = statusRastreio.getCidade();
        estado = statusRastreio.getEstado();
        status = statusRastreio.getStatus();
        idVenda = statusRastreio.getVendaCompraLojaVirtual().getId();
    }

    public Long getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(Long idVenda) {
        this.idVenda = idVenda;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCentroDistribuicao() {
        return centroDistribuicao;
    }

    public void setCentroDistribuicao(String centroDistribuicao) {
        this.centroDistribuicao = centroDistribuicao;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
