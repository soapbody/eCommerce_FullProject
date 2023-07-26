package com.Mateus_Ulrich.eCommerce_FullProject.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.util.Date;

public class RelatorioProdutoCompradoNotaFiscalDTO {
    private String nomeProduto = "";
    @JsonFormat(pattern = "yyyy-MM-dd")
    //@NotEmpty(message = "Informe a data inicial")
    private Date dataInicial;
    @JsonFormat(pattern = "yyyy-MM-dd")
    //@NotEmpty(message = "Informe a data final")
    private Date dataFinal;
    private Long codigoNota;
    private Long codigoProduto;
    private BigDecimal valorVendaProduto;
    private Long quantidadeComprada;
    private String nomeFornecedor = "";
    private Date dataCompra;

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public Date getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(Date dataInicial) {
        this.dataInicial = dataInicial;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

    public Long getCodigoNota() {
        return codigoNota;
    }

    public void setCodigoNota(Long codigoNota) {
        this.codigoNota = codigoNota;
    }

    public Long getCodigoProduto() {
        return codigoProduto;
    }

    public void setCodigoProduto(Long codigoProduto) {
        this.codigoProduto = codigoProduto;
    }

    public BigDecimal getValorVendaProduto() {
        return valorVendaProduto;
    }

    public void setValorVendaProduto(BigDecimal valorVendaProduto) {
        this.valorVendaProduto = valorVendaProduto;
    }

    public Long getQuantidadeComprada() {
        return quantidadeComprada;
    }

    public void setQuantidadeComprada(Long quantidadeComprada) {
        this.quantidadeComprada = quantidadeComprada;
    }

    public String getNomeFornecedor() {
        return nomeFornecedor;
    }

    public void setNomeFornecedor(String nomeFornecedor) {
        this.nomeFornecedor = nomeFornecedor;
    }

    public Date getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(Date dataCompra) {
        this.dataCompra = dataCompra;
    }
}
