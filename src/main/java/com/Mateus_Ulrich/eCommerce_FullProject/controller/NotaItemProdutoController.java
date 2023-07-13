package com.Mateus_Ulrich.eCommerce_FullProject.controller;

import com.Mateus_Ulrich.eCommerce_FullProject.exceptions.CustomException;
import com.Mateus_Ulrich.eCommerce_FullProject.model.NotaItemProduto;
import com.Mateus_Ulrich.eCommerce_FullProject.repository.NotaItemProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@RestController
public class NotaItemProdutoController {
    @Autowired
    private NotaItemProdutoRepository notaItemProdutoRepository;

    @ResponseBody /*Poder dar um retorno da API*/
    @PostMapping(value = "**/salvarNotaItemProduto") /*Mapeando a url para receber JSON*/
    public ResponseEntity<NotaItemProduto> salvarNotaItemProduto(@RequestBody @Valid NotaItemProduto notaItemProduto) throws CustomException { /*Recebe o JSON e converte pra Objeto*/
        if(notaItemProduto.getId() == null) {
            if (notaItemProduto.getProduto() == null || notaItemProduto.getProduto().getId() <= 0) {
                throw new CustomException("O Produto deve ser informado");
            }
            if (notaItemProduto.getNotaFiscalCompra() == null || notaItemProduto.getNotaFiscalCompra().getId() <= 0) {
                throw new CustomException("A Nota fical deve ser informada");
            }
            if (notaItemProduto.getEmpresa() == null || notaItemProduto.getEmpresa().getId() <= 0) {
                throw new CustomException("A Empresa deve ser informada");
            }
            List<NotaItemProduto> notaExistente = notaItemProdutoRepository.buscarItemPorProdutoENota(notaItemProduto.getProduto().getId(), notaItemProduto.getNotaFiscalCompra().getId());
            if (!notaExistente.isEmpty()) {
                throw new CustomException("Já existe este produto cadastrado para esta Nota");
            }

        }

        NotaItemProduto notaItemSalva = notaItemProdutoRepository.save(notaItemProduto);
        //notaItemSalva = notaItemProdutoRepository.findById(notaItemProduto.getId()).get();

        return new ResponseEntity<NotaItemProduto>(notaItemSalva, HttpStatus.OK);
    }

    @ResponseBody /*Poder dar um retorno da API*/
    @PostMapping(value = "**/dsdsd") /*Mapeando a url para receber JSON*/
    public ResponseEntity<NotaItemProduto> dsdsd(@RequestBody NotaItemProduto notaItemProduto) { /*Recebe o JSON e converte pra Objeto*/

        notaItemProdutoRepository.deleteById(notaItemProduto.getId());

        return new ResponseEntity("Marca Removido",HttpStatus.OK);
    }


    //@Secured({ "ROLE_GERENTE", "ROLE_ADMIN" })
    @ResponseBody
    @DeleteMapping(value = "**/dsdsds/{id}")
    public ResponseEntity<NotaItemProduto> dsdsd(@PathVariable("id") Long id) {

        notaItemProdutoRepository.deleteById(id);

        return new ResponseEntity("Marca Removida",HttpStatus.OK);
    }



    @ResponseBody
    @GetMapping(value = "**/buscarMassdsPorId/{id}")
    public ResponseEntity<NotaItemProduto> buscarMassdsPorId(@PathVariable("id") Long id) throws CustomException {

        NotaItemProduto notaItemProduto = notaItemProdutoRepository.findById(id).orElse(null);
        if(notaItemProduto == null) {
            throw new CustomException("Não existe Marca com o ID: " + id);
        }

        return new ResponseEntity<NotaItemProduto>(notaItemProduto,HttpStatus.OK);

    }


}
