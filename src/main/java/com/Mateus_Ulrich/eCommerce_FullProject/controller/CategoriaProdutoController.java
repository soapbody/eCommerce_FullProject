package com.Mateus_Ulrich.eCommerce_FullProject.controller;

import com.Mateus_Ulrich.eCommerce_FullProject.exceptions.CustomException;
import com.Mateus_Ulrich.eCommerce_FullProject.model.CategoriaProduto;
import com.Mateus_Ulrich.eCommerce_FullProject.model.dto.CategoriaProdutoDTO;
import com.Mateus_Ulrich.eCommerce_FullProject.repository.CategoriaProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoriaProdutoController {
    @Autowired
    private CategoriaProdutoRepository categoriaProdutoRepository;
    @ResponseBody
    @PostMapping(value = "**/salvarCategoria")
    public ResponseEntity<CategoriaProdutoDTO> salvarCategoria(@RequestBody CategoriaProduto categoriaProduto) throws CustomException {
            if(categoriaProduto.getEmpresa() == null || (categoriaProduto.getEmpresa().getId() == null)) {
                throw new CustomException("A Empresa deve ser informada.");
            }
            if (categoriaProduto.getId() == null && categoriaProdutoRepository.existeCategoria(categoriaProduto.getNomeDesc().toUpperCase().trim())) {
                throw new CustomException("Não pode cadastrar categoria com o mesmo nome.");
            }

        CategoriaProduto categoriaSalva = categoriaProdutoRepository.save(categoriaProduto);
        CategoriaProdutoDTO categoriaProdutoDTO = new CategoriaProdutoDTO();
        categoriaProdutoDTO.setId(categoriaSalva.getId());
        categoriaProdutoDTO.setNomeDesc(categoriaSalva.getNomeDesc());
        categoriaProdutoDTO.setEmpresa(categoriaSalva.getEmpresa().getId().toString());
        return new ResponseEntity<CategoriaProdutoDTO>(categoriaProdutoDTO, HttpStatus.OK);
    }

    @ResponseBody
    @DeleteMapping(value = "**/deleteCategoriaPorId/{id}")
    public ResponseEntity<?> deleteCategoriaPorId(@PathVariable("id") Long id) throws CustomException {
        if(categoriaProdutoRepository.findById(id).isPresent() == false) {
            throw new CustomException("Não existe Categoria Cadastrada com esse ID ou essa Categoria já foi removida.");
        }
        else {
            categoriaProdutoRepository.deleteById(id);
            return new ResponseEntity("Categoria Removida",HttpStatus.OK);
        }

    }

    @ResponseBody
    @GetMapping(value = "**/consultarCategoria/{nomeDesc}")
    public ResponseEntity<List<CategoriaProduto>> consultarCategoria(@PathVariable("nomeDesc") String nomeDesc) {
        List<CategoriaProduto> categorias = categoriaProdutoRepository.buscarCategoriaProduto(nomeDesc.trim().toUpperCase());
        return new ResponseEntity<List<CategoriaProduto>>(categorias, HttpStatus.OK);
    }
}
