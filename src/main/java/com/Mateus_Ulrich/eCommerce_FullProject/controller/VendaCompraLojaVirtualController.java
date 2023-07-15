package com.Mateus_Ulrich.eCommerce_FullProject.controller;

import com.Mateus_Ulrich.eCommerce_FullProject.exceptions.CustomException;
import com.Mateus_Ulrich.eCommerce_FullProject.model.Endereco;
import com.Mateus_Ulrich.eCommerce_FullProject.model.NotaFiscalVenda;
import com.Mateus_Ulrich.eCommerce_FullProject.model.PessoaFisica;
import com.Mateus_Ulrich.eCommerce_FullProject.model.VendaCompraLojaVirtual;
import com.Mateus_Ulrich.eCommerce_FullProject.model.dto.VendaCompraLojaVirtualDTO;
import com.Mateus_Ulrich.eCommerce_FullProject.repository.EnderecoRepository;
import com.Mateus_Ulrich.eCommerce_FullProject.repository.NotaFiscalVendaRepository;
import com.Mateus_Ulrich.eCommerce_FullProject.repository.PessoaFisicaRepository;
import com.Mateus_Ulrich.eCommerce_FullProject.repository.VendaCompraLojaVirtualRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class VendaCompraLojaVirtualController {
    @Autowired
    private VendaCompraLojaVirtualRepository repository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private PessoaController pessoaController;
    @Autowired
    private NotaFiscalVendaRepository notaFiscalVendaRepository;
    @ResponseBody
    @PostMapping(value = "**/salvarVendaLoja")
    public ResponseEntity<VendaCompraLojaVirtualDTO> salvarVendaLoja(@RequestBody @Valid VendaCompraLojaVirtual vendaCompraLojaVirtual) throws CustomException {
        vendaCompraLojaVirtual.getPessoa().setEmpresa(vendaCompraLojaVirtual.getEmpresa());
        PessoaFisica pessoaFisica = pessoaController.salvarPf(vendaCompraLojaVirtual.getPessoa()).getBody();
        vendaCompraLojaVirtual.setPessoa(pessoaFisica);

        vendaCompraLojaVirtual.getEnderecoCobranca().setPessoa(pessoaFisica);
        vendaCompraLojaVirtual.getEnderecoCobranca().setEmpresa(vendaCompraLojaVirtual.getEmpresa());
        Endereco enderecoCobranca = enderecoRepository.save(vendaCompraLojaVirtual.getEnderecoCobranca());
        vendaCompraLojaVirtual.setEnderecoCobranca(enderecoCobranca);

        vendaCompraLojaVirtual.getEnderecoEntrega().setPessoa(pessoaFisica);
        vendaCompraLojaVirtual.getEnderecoEntrega().setEmpresa(vendaCompraLojaVirtual.getEmpresa());
        Endereco enderecoEntrega = enderecoRepository.save(vendaCompraLojaVirtual.getEnderecoEntrega());
        vendaCompraLojaVirtual.setEnderecoEntrega(enderecoEntrega);

        vendaCompraLojaVirtual.getNotaFiscalVenda().setEmpresa(vendaCompraLojaVirtual.getEmpresa());

        /*Salva primeiro a venda e todo os dados*/
        vendaCompraLojaVirtual = repository.saveAndFlush(vendaCompraLojaVirtual);

        /*Associa a venda gravada no banco com a nota fiscal*/
        vendaCompraLojaVirtual.getNotaFiscalVenda().setVendaCompraLojaVirtual(vendaCompraLojaVirtual);


        /*Persiste novamente as nota fiscal novamente pra ficar amarrada na venda*/
        notaFiscalVendaRepository.saveAndFlush(vendaCompraLojaVirtual.getNotaFiscalVenda());

        VendaCompraLojaVirtualDTO compraLojaVirtualDTO = new VendaCompraLojaVirtualDTO();
        compraLojaVirtualDTO.setValorTotal(vendaCompraLojaVirtual.getValorTotal());

        return new ResponseEntity<VendaCompraLojaVirtualDTO>(compraLojaVirtualDTO, HttpStatus.OK);
    }



}
