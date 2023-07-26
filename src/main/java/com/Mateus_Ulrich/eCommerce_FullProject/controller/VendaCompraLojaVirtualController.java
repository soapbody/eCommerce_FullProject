package com.Mateus_Ulrich.eCommerce_FullProject.controller;

import com.Mateus_Ulrich.eCommerce_FullProject.enums.StatusContaReceber;
import com.Mateus_Ulrich.eCommerce_FullProject.exceptions.CustomException;
import com.Mateus_Ulrich.eCommerce_FullProject.model.*;
import com.Mateus_Ulrich.eCommerce_FullProject.model.dto.VendaCompraLojaVirtualDTO;
import com.Mateus_Ulrich.eCommerce_FullProject.repository.*;

import javax.mail.MessagingException;
import javax.validation.Valid;

import com.Mateus_Ulrich.eCommerce_FullProject.service.ServiceSendEmail;
import com.Mateus_Ulrich.eCommerce_FullProject.service.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@RestController
public class VendaCompraLojaVirtualController {
    @Autowired
    private VendaCompraLojaVirtualRepository vendaCompraLojaVirtualRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private PessoaController pessoaController;
    @Autowired
    private NotaFiscalVendaRepository notaFiscalVendaRepository;
    @Autowired
    private StatusRastreioRepository statusRastreioRepository;
    @Autowired
    private ContaReceberRepository contaReceberRepository;
    @Autowired
    private VendaService vendaService;
    @Autowired
    private ServiceSendEmail serviceSendEmail;
    @ResponseBody
    @PostMapping(value = "**/salvarVendaLoja")
    public ResponseEntity<VendaCompraLojaVirtualDTO> salvarVendaLoja(@RequestBody @Valid VendaCompraLojaVirtual vendaCompraLojaVirtual) throws CustomException, MessagingException, UnsupportedEncodingException {
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

        for(int i = 0; i < vendaCompraLojaVirtual.getItemVendaLojaList().size(); i++) {
            vendaCompraLojaVirtual.getItemVendaLojaList().get(i).setEmpresa(vendaCompraLojaVirtual.getEmpresa());
            vendaCompraLojaVirtual.getItemVendaLojaList().get(i).setVendaCompraLojaVirtual(vendaCompraLojaVirtual);
        }

        /*Salva primeiro a venda e todos os dados*/
        vendaCompraLojaVirtual = vendaCompraLojaVirtualRepository.saveAndFlush(vendaCompraLojaVirtual);

        StatusRastreio statusRastreio = new StatusRastreio();
        statusRastreio.setCentroDistribuicao("loja local");
        statusRastreio.setCidade("local");
        statusRastreio.setEmpresa(vendaCompraLojaVirtual.getEmpresa());
        statusRastreio.setEstado("local");
        statusRastreio.setStatus("Inicio Compra");
        statusRastreio.setVendaCompraLojaVirtual(vendaCompraLojaVirtual);

        statusRastreioRepository.save(statusRastreio);

        /*Associa a venda gravada no banco com a nota fiscal*/
        vendaCompraLojaVirtual.getNotaFiscalVenda().setVendaCompraLojaVirtual(vendaCompraLojaVirtual);

        /*Persiste novamente as nota fiscal novamente pra ficar amarrada na venda*/
        notaFiscalVendaRepository.saveAndFlush(vendaCompraLojaVirtual.getNotaFiscalVenda());

        VendaCompraLojaVirtualDTO compraLojaVirtualDTO = new VendaCompraLojaVirtualDTO(vendaCompraLojaVirtual);

        ContaReceber contaReceber = new ContaReceber();
        contaReceber.setDescricao("Venda da Loja Virtual Numero: " + vendaCompraLojaVirtual.getId());
        contaReceber.setDtPagamento(Calendar.getInstance().getTime());
        contaReceber.setDtVencimento(Calendar.getInstance().getTime());
        contaReceber.setEmpresa(vendaCompraLojaVirtual.getEmpresa());
        contaReceber.setPessoa(vendaCompraLojaVirtual.getPessoa());
        contaReceber.setStatus(StatusContaReceber.QUITADA);
        contaReceber.setValorDesconto(vendaCompraLojaVirtual.getValorDesconto());
        contaReceber.setValorTotal(vendaCompraLojaVirtual.getValorTotal());

        contaReceberRepository.saveAndFlush(contaReceber);

        StringBuilder sb = new StringBuilder();
        sb.append("Olá, ").append(pessoaFisica.getNome()).append("</br>");
        sb.append("Voce realizou a compra de Número: ").append(vendaCompraLojaVirtual.getId()).append("</br>");
        sb.append("Na Loja ").append(vendaCompraLojaVirtual.getEmpresa().getNomeFantasia());
        serviceSendEmail.enviarEmailHtml("Compra Finalizada", sb.toString(), pessoaFisica.getEmail());

        StringBuilder sb2 = new StringBuilder();
        sb2.append("Voce realizou uma venda, Número: " ).append(vendaCompraLojaVirtual.getId());
        serviceSendEmail.enviarEmailHtml("Venda Finalizada", sb2.toString(), vendaCompraLojaVirtual.getEmpresa().getEmail());

        return new ResponseEntity<VendaCompraLojaVirtualDTO>(compraLojaVirtualDTO, HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping(value = "**/consultaVendaId/{id}")
    public ResponseEntity<VendaCompraLojaVirtualDTO> consultaVendaId(@PathVariable(value = "id") Long id) {
        VendaCompraLojaVirtual vendaCompraLojaVirtual = vendaCompraLojaVirtualRepository.findByIdExclusao(id);

        if (vendaCompraLojaVirtual == null) {
            vendaCompraLojaVirtual = new VendaCompraLojaVirtual();
        }

        VendaCompraLojaVirtualDTO vendaCompraLojaVirtualDTO = new VendaCompraLojaVirtualDTO(vendaCompraLojaVirtual);
        return new ResponseEntity<VendaCompraLojaVirtualDTO>(vendaCompraLojaVirtualDTO, HttpStatus.OK);
    }
    @ResponseBody
    @GetMapping(value = "**/consultaVendaPorCliente/{id}")
    public ResponseEntity<List<VendaCompraLojaVirtualDTO>> consultaVendaPorCliente(@PathVariable(value = "id") Long id) {
        List<VendaCompraLojaVirtual> lista = vendaCompraLojaVirtualRepository.vendaPorCliente(id);

        if (lista == null) {
            lista = new ArrayList<VendaCompraLojaVirtual>();

        }
        List<VendaCompraLojaVirtualDTO> dtos = lista.stream().map(VendaCompraLojaVirtualDTO::new).collect(Collectors.toList());
        return new ResponseEntity<List<VendaCompraLojaVirtualDTO>>(dtos, HttpStatus.OK);
    }

    @ResponseBody
    @DeleteMapping(value = "**/deleteVendaTotalBanco/{idVenda}")
    public ResponseEntity<String> deleteVendaTotalBanco(@PathVariable(value = "idVenda") Long idVenda) {
        vendaService.exclusaoTotalVendaBanco(idVenda);
        return new ResponseEntity<String>("Venda Excluida", HttpStatus.OK);
    }
    @ResponseBody
    @DeleteMapping(value = "**/deleteVendaTotalBanco2/{idVenda}")
    public ResponseEntity<String> deleteVendaTotalBanco2(@PathVariable(value = "idVenda") Long idVenda) {
        vendaService.exclusaoTotalVendaBanco2(idVenda);
        return new ResponseEntity<String>("Venda Excluida", HttpStatus.OK);
    }

    @ResponseBody
    @PutMapping(value = "**/ativarRegistroVendaBanco/{idVenda}")
    public ResponseEntity<String> ativarRegistroVendaBanco(@PathVariable(value = "idVenda") Long idVenda) {
        vendaService.ativarRegistroVendaBanco(idVenda);
        return new ResponseEntity<String>("Venda Ativada com Sucesso", HttpStatus.OK);
    }
    @ResponseBody
    @GetMapping(value = "**/consultaVendaDinamica/{valor}/{consulta}")
    public ResponseEntity<List<VendaCompraLojaVirtualDTO>> consultaVendaDinamica(@PathVariable(value = "valor") String valor, @PathVariable(value = "consulta") String consulta) {
        List<VendaCompraLojaVirtual> vendaCompraLojaVirtualList = null;
        if (consulta.equalsIgnoreCase("POR_ID_PROD")) {
            vendaCompraLojaVirtualList = vendaCompraLojaVirtualRepository.vendaPorProduto(Long.parseLong(valor));
        }
        else if (consulta.equalsIgnoreCase("POR_NOME_PROD")) {
            vendaCompraLojaVirtualList = vendaCompraLojaVirtualRepository.vendaPorNomeProduto(valor.toUpperCase().trim());
        }
        else if (consulta.equalsIgnoreCase("POR_NOME_CLIENTE")) {
            vendaCompraLojaVirtualList = vendaCompraLojaVirtualRepository.vendaPorNomeCliente(valor.toUpperCase().trim());
        }
        else if (consulta.equalsIgnoreCase("POR_ENDERECO_COBRANCA")) {
            vendaCompraLojaVirtualList = vendaCompraLojaVirtualRepository.vendaPorEnderecoCobranca(valor.toUpperCase().trim());
        }
        else if (consulta.equalsIgnoreCase("POR_ENDERECO_ENTREGA")) {
            vendaCompraLojaVirtualList = vendaCompraLojaVirtualRepository.vendaPorEnderecoEntrega(valor.toUpperCase().trim());
        }
        if (vendaCompraLojaVirtualList == null) {
            vendaCompraLojaVirtualList = new ArrayList<>();
        }
        List<VendaCompraLojaVirtualDTO> vendaCompraLojaVirtualDTOs = new ArrayList<VendaCompraLojaVirtualDTO>();
        for (VendaCompraLojaVirtual vendaCompraLojaVirtual : vendaCompraLojaVirtualList) {
            VendaCompraLojaVirtualDTO vendaCompraLojaVirtualDTO = new VendaCompraLojaVirtualDTO(vendaCompraLojaVirtual);
            vendaCompraLojaVirtualDTOs.add(vendaCompraLojaVirtualDTO);
        }
        return new ResponseEntity<List<VendaCompraLojaVirtualDTO>>(vendaCompraLojaVirtualDTOs, HttpStatus.OK);
    }
    @ResponseBody
    @GetMapping(value = "**/consultaVendaDinamicaPorData/{data1}/{data2}")
    public ResponseEntity<List<VendaCompraLojaVirtualDTO>> consultaVendaDinamicaPorData(@PathVariable(value = "data1")@DateTimeFormat(pattern = "yyyy-MM-dd") Date data1, @PathVariable(value = "data2") @DateTimeFormat(pattern = "yyyy-MM-dd")Date data2) {
        List<VendaCompraLojaVirtual> vendaCompraLojaVirtualList = new ArrayList<>();
        vendaCompraLojaVirtualList = vendaCompraLojaVirtualRepository.consultaVendaFaixaData(data1, data2);
        List<VendaCompraLojaVirtualDTO> vendaCompraLojaVirtualDTOs = new ArrayList<VendaCompraLojaVirtualDTO>();
        for (VendaCompraLojaVirtual vendaCompraLojaVirtual : vendaCompraLojaVirtualList) {
            VendaCompraLojaVirtualDTO vendaCompraLojaVirtualDTO = new VendaCompraLojaVirtualDTO(vendaCompraLojaVirtual);
            vendaCompraLojaVirtualDTOs.add(vendaCompraLojaVirtualDTO);
        }
        return new ResponseEntity<List<VendaCompraLojaVirtualDTO>>(vendaCompraLojaVirtualDTOs, HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping(value = "**/consultaVendaCpfCnpj/{value}")
    public ResponseEntity<List<VendaCompraLojaVirtualDTO>> consultaVendaCpfCnpj(@PathVariable(value = "value") String value) {
        List<VendaCompraLojaVirtual> vendaCompraLojaVirtualList = new ArrayList<>();
        vendaCompraLojaVirtualList = vendaCompraLojaVirtualRepository.consultaVendaCpfCnpj(value);
        List<VendaCompraLojaVirtualDTO> vendaCompraLojaVirtualDTOs = new ArrayList<VendaCompraLojaVirtualDTO>();
        for (VendaCompraLojaVirtual vendaCompraLojaVirtual : vendaCompraLojaVirtualList) {
            VendaCompraLojaVirtualDTO vendaCompraLojaVirtualDTO = new VendaCompraLojaVirtualDTO(vendaCompraLojaVirtual);
            vendaCompraLojaVirtualDTOs.add(vendaCompraLojaVirtualDTO);
        }
        return new ResponseEntity<List<VendaCompraLojaVirtualDTO>>(vendaCompraLojaVirtualDTOs, HttpStatus.OK);
    }





}
