package com.Mateus_Ulrich.eCommerce_FullProject.controller;

import com.Mateus_Ulrich.eCommerce_FullProject.model.Acesso;
import com.Mateus_Ulrich.eCommerce_FullProject.model.FormaPagamento;
import com.Mateus_Ulrich.eCommerce_FullProject.repository.FormaPagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class FormaPagamentoController {
    @Autowired
    private FormaPagamentoRepository formaPagamentoRepository;

    @ResponseBody /*Poder dar um retorno da API*/
    @PostMapping(value = "**/salvarFormaPagamento") /*Mapeando a url para receber JSON*/
    public ResponseEntity<FormaPagamento> salvarFormaPagamento(@RequestBody @Valid FormaPagamento formaPagamento) { /*Recebe o JSON e converte pra Objeto*/
        formaPagamento = formaPagamentoRepository.save(formaPagamento);

        return new ResponseEntity<FormaPagamento>(formaPagamento, HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping(value = "**/listaFormaPagamentoPorIdEmpresa/{id}")
    public ResponseEntity<List<FormaPagamento>> listaFormaPagamentoPorIdEmpresa(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<List<FormaPagamento>>(formaPagamentoRepository.findAll(id), HttpStatus.OK);
    }
    @ResponseBody
    @GetMapping(value = "**/listaFormaPagamento")
    public ResponseEntity<List<FormaPagamento>> ListaFormaPagamento() {
        return new ResponseEntity<List<FormaPagamento>>(formaPagamentoRepository.findAll(), HttpStatus.OK);
    }

}
