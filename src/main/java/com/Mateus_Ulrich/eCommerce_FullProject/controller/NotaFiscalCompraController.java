package com.Mateus_Ulrich.eCommerce_FullProject.controller;

import com.Mateus_Ulrich.eCommerce_FullProject.exceptions.CustomException;
import com.Mateus_Ulrich.eCommerce_FullProject.model.NotaFiscalCompra;
import com.Mateus_Ulrich.eCommerce_FullProject.model.NotaFiscalVenda;
import com.Mateus_Ulrich.eCommerce_FullProject.repository.NotaFiscalCompraRepository;
import com.Mateus_Ulrich.eCommerce_FullProject.repository.NotaFiscalVendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

@RestController

public class NotaFiscalCompraController {
    @Autowired
    private NotaFiscalCompraRepository notaFiscalCompraRepository;
    @Autowired
    private NotaFiscalVendaRepository notaFiscalVendaRepository;
    @ResponseBody /*Poder dar um retorno da API*/
    @PostMapping(value = "**/salvarNotaFiscalCompra") /*Mapeando a url para receber JSON*/
    public ResponseEntity<NotaFiscalCompra> salvarNotaFiscalCompra(@RequestBody @Valid NotaFiscalCompra notaFiscalCompra) throws CustomException { /*Recebe o JSON e converte pra Objeto*/
        if(notaFiscalCompra.getId() == null) {
            if (notaFiscalCompra.getDescricaoObs() != null) {
                boolean existe = notaFiscalCompraRepository.existeNotaComDescricao(notaFiscalCompra.getDescricaoObs().toUpperCase().trim());
                if (existe) {
                    throw new CustomException("Já existe Nota de compra com essa mesma descrição: " + notaFiscalCompra.getDescricaoObs());

                }
            }

        }
        if (notaFiscalCompra.getPessoa() == null || notaFiscalCompra.getPessoa().getId() <= 0) {
            throw new CustomException("A Pessoa Jurídica da nota fiscal deve ser informada.");
        }
        if(notaFiscalCompra.getEmpresa() == null || notaFiscalCompra.getEmpresa().getId() <= 0) {
            throw new CustomException("A Empresa responsável deve ser informada.");

        }
        if (notaFiscalCompra.getContaPagar() == null || notaFiscalCompra.getContaPagar().getId() <= 0) {
            throw new CustomException("A conta a pagar da nota deve ser informada");
        }

        NotaFiscalCompra notaFiscalCompraSalva = notaFiscalCompraRepository.save(notaFiscalCompra);

        return new ResponseEntity<NotaFiscalCompra>(notaFiscalCompraSalva, HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping(value = "**/buscarNotaFiscalCompraPorDesc/{desc}")
    public ResponseEntity<List<NotaFiscalCompra>> buscarNotaFiscalCompraPorDesc(@PathVariable("desc") String desc) throws CustomException {

        List<NotaFiscalCompra> notaFiscalCompra = notaFiscalCompraRepository.buscarNotaDesc(desc.toUpperCase().trim());
        if(notaFiscalCompra == null) {
            throw new CustomException("Não existe Nota com essa descrição: " + desc);
        }


        return new ResponseEntity<List<NotaFiscalCompra>>(notaFiscalCompra, HttpStatus.OK);


    }

    @ResponseBody
    @GetMapping(value = "**/buscarNotaFiscalCompraPorId/{id}")
    public ResponseEntity<NotaFiscalCompra> buscarNotaFiscalCompraPorId(@PathVariable("id") Long id) throws CustomException {

        NotaFiscalCompra notaFiscalCompra = notaFiscalCompraRepository.findById(id).orElse(null);
        if (notaFiscalCompra == null) {
            // Lida com o caso em que a nota fiscal de compra não foi encontrada
            throw new CustomException("Nota fiscal de compra não encontrada");
        }


        return new ResponseEntity<NotaFiscalCompra>(notaFiscalCompra,HttpStatus.OK);
    }
    @ResponseBody
    @GetMapping(value = "**/buscarNotaFiscalDaVenda/{id}")
    public ResponseEntity<NotaFiscalVenda> buscarNotaFiscalDaVenda(@PathVariable("id") Long id) throws CustomException {

        NotaFiscalVenda notaFiscalVenda = notaFiscalVendaRepository.buscaNotaPorVenda(id);
        if (notaFiscalVenda == null) {
            // Lida com o caso em que a nota fiscal de compra não foi encontrada
            throw new CustomException("Nota fiscal de compra não encontrada");
        }


        return new ResponseEntity<NotaFiscalVenda>(notaFiscalVenda,HttpStatus.OK);
    }

    @ResponseBody
    @DeleteMapping(value = "**/deleteNotaCompraPorId/{id}")
    public ResponseEntity<?> deleteNotaCompraPorId(@PathVariable("id") Long id) {
        notaFiscalCompraRepository.deleteItemNotaFiscalCompra(id);
        notaFiscalCompraRepository.deleteById(id);

        return new ResponseEntity("Nota Removida",HttpStatus.OK);
    }
}
