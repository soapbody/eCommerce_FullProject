package com.Mateus_Ulrich.eCommerce_FullProject.controller;

import com.Mateus_Ulrich.eCommerce_FullProject.model.VendaCompraLojaVirtual;
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
    @ResponseBody
    @PostMapping(value = "**/salvarVendaLoja")
    public ResponseEntity<VendaCompraLojaVirtual> salvarVendaLoja(@RequestBody @Valid VendaCompraLojaVirtual vendaCompraLojaVirtual) {


        vendaCompraLojaVirtual = repository.saveAndFlush(vendaCompraLojaVirtual);


        return new  ResponseEntity<VendaCompraLojaVirtual>(vendaCompraLojaVirtual,HttpStatus.OK);
    }

}
