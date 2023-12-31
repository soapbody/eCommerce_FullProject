package com.Mateus_Ulrich.eCommerce_FullProject.model.dto;

import com.Mateus_Ulrich.eCommerce_FullProject.model.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class VendaCompraLojaVirtualDTO {
    private Long id;
    private BigDecimal valorTotal;
    private PessoaFisica pessoa;
    private Endereco enderecoEntrega;
    private Endereco getEnderecoCobranca;
    private BigDecimal valorDesconto;
    private BigDecimal valorFrete;
    private Date dataVenda;
    private List<ItemVendaLojaDTO> itemVendaLojaDTOS = new ArrayList<ItemVendaLojaDTO>();

    public VendaCompraLojaVirtualDTO(VendaCompraLojaVirtual vendaCompraLojaVirtual) {
        id = vendaCompraLojaVirtual.getId();
        valorTotal = vendaCompraLojaVirtual.getValorTotal();
        pessoa = vendaCompraLojaVirtual.getPessoa();
        enderecoEntrega = vendaCompraLojaVirtual.getEnderecoEntrega();
        getEnderecoCobranca = vendaCompraLojaVirtual.getEnderecoCobranca();
        valorDesconto = vendaCompraLojaVirtual.getValorDesconto();
        valorFrete = vendaCompraLojaVirtual.getValorFret();
        itemVendaLojaDTOS = new ArrayList<>();
        dataVenda = vendaCompraLojaVirtual.getDataVenda();
        itemVendaLojaDTOS = vendaCompraLojaVirtual.getItemVendaLojaList().stream()
                .map(ItemVendaLojaDTO::new)
                .collect(Collectors.toList());
    }

    public Date getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ItemVendaLojaDTO> getItemVendaLojaDTOS() {
        return itemVendaLojaDTOS;
    }

    public void setItemVendaLojaDTOS(List<ItemVendaLojaDTO> itemVendaLojaDTOS) {
        this.itemVendaLojaDTOS = itemVendaLojaDTOS;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }
    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }
    public void setPessoa(PessoaFisica pessoa) {
        this.pessoa = pessoa;
    }
    public PessoaFisica getPessoa() {
        return pessoa;
    }
    public Endereco getEnderecoEntrega() {
        return enderecoEntrega;
    }

    public void setEnderecoEntrega(Endereco enderecoEntrega) {
        this.enderecoEntrega = enderecoEntrega;
    }

    public Endereco getGetEnderecoCobranca() {
        return getEnderecoCobranca;
    }

    public void setGetEnderecoCobranca(Endereco getEnderecoCobranca) {
        this.getEnderecoCobranca = getEnderecoCobranca;
    }

    public BigDecimal getValorDesconto() {
        return valorDesconto;
    }

    public void setValorDesconto(BigDecimal valorDesconto) {
        this.valorDesconto = valorDesconto;
    }

    public BigDecimal getValorFrete() {
        return valorFrete;
    }

    public void setValorFrete(BigDecimal valorFrete) {
        this.valorFrete = valorFrete;
    }
}
