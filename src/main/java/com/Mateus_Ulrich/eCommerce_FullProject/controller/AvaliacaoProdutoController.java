package com.Mateus_Ulrich.eCommerce_FullProject.controller;

import com.Mateus_Ulrich.eCommerce_FullProject.exceptions.CustomException;
import com.Mateus_Ulrich.eCommerce_FullProject.model.AvaliacaoProduto;
import com.Mateus_Ulrich.eCommerce_FullProject.model.ImagemProduto;
import com.Mateus_Ulrich.eCommerce_FullProject.model.dto.AvaliacaoProdutoDTO;
import com.Mateus_Ulrich.eCommerce_FullProject.model.dto.ImagemProdutoDTO;
import com.Mateus_Ulrich.eCommerce_FullProject.repository.AvaliacaoProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class AvaliacaoProdutoController {
    @Autowired
    private AvaliacaoProdutoRepository avaliacaoProdutoRepository;

    @ResponseBody
    @PostMapping(value = "**/salvarAvaliacaoProduto")
    public ResponseEntity<AvaliacaoProdutoDTO> salvarAvaliacaoProduto(@RequestBody @Valid AvaliacaoProduto avaliacaoProduto) throws CustomException {
        if (avaliacaoProduto.getEmpresa() == null || (avaliacaoProduto.getEmpresa() != null && avaliacaoProduto.getEmpresa().getId() <= 0)) {
            throw new CustomException("Informe a empresa dona do registro");
        }
        if (avaliacaoProduto.getProduto() == null || (avaliacaoProduto.getProduto() != null && avaliacaoProduto.getProduto().getId() <= 0)) {
            throw new CustomException("A avaliação deve conter o produto associado");
        }

        if (avaliacaoProduto.getPessoa() == null || (avaliacaoProduto.getPessoa() != null && avaliacaoProduto.getPessoa().getId() <= 0)) {
            throw new CustomException("A avaliação deve conter a pessoa associada");
        }
        else {
            avaliacaoProduto = avaliacaoProdutoRepository.saveAndFlush(avaliacaoProduto);

            AvaliacaoProdutoDTO avaliacaoProdutoDTO = new AvaliacaoProdutoDTO();
            avaliacaoProdutoDTO.setId(avaliacaoProduto.getId());
            avaliacaoProdutoDTO.setDescricao(avaliacaoProduto.getDescricao());
            avaliacaoProdutoDTO.setNota(avaliacaoProduto.getNota());
            avaliacaoProdutoDTO.setEmpresa(avaliacaoProduto.getEmpresa());
            avaliacaoProdutoDTO.setPessoa(avaliacaoProduto.getPessoa());
            avaliacaoProdutoDTO.setProduto(avaliacaoProduto.getProduto());


            return new ResponseEntity<AvaliacaoProdutoDTO>(avaliacaoProdutoDTO, HttpStatus.OK);



        }


    }

    @ResponseBody
    @DeleteMapping(value = "**/deleteAvaliaçãoProduto/{id}")
    public ResponseEntity<?> deleteAvaliaçãoProduto(@PathVariable("id") Long id) {

        avaliacaoProdutoRepository.deleteById(id);

        return new ResponseEntity("Avaliação Removida", HttpStatus.OK);
    }

    @GetMapping(value = "**/buscarAvaliacaoProduto/{id}")
    @ResponseBody
    public ResponseEntity<List<AvaliacaoProdutoDTO>> buscarAvaliacaoProduto(@PathVariable("id")Long id) {
        List<AvaliacaoProdutoDTO> dtos = new ArrayList<AvaliacaoProdutoDTO>();

        List<AvaliacaoProduto> avaliacaoProdutos = avaliacaoProdutoRepository.avaliacaoProduto(id);


        for (AvaliacaoProduto avaliacaoProduto : avaliacaoProdutos) {

            AvaliacaoProdutoDTO avaliacaoProdutoDTO = new AvaliacaoProdutoDTO();
            avaliacaoProdutoDTO.setId(avaliacaoProduto.getId());
            avaliacaoProdutoDTO.setDescricao(avaliacaoProduto.getDescricao());
            avaliacaoProdutoDTO.setNota(avaliacaoProduto.getNota());
            avaliacaoProdutoDTO.setEmpresa(avaliacaoProduto.getEmpresa());
            avaliacaoProdutoDTO.setPessoa(avaliacaoProduto.getPessoa());
            avaliacaoProdutoDTO.setProduto(avaliacaoProduto.getProduto());
            dtos.add(avaliacaoProdutoDTO);

        }
        return new ResponseEntity<List<AvaliacaoProdutoDTO>>(dtos, HttpStatus.OK);
    }

    @GetMapping(value = "**/buscarAvaliacaoProdutoPessoa/{idProduto}/{idPessoa}")
    @ResponseBody
    public ResponseEntity<List<AvaliacaoProdutoDTO>> buscarAvaliacaoProdutoPessoa(@PathVariable("idProduto")Long idProduto, @PathVariable("idPessoa")Long idPessoa) {
        List<AvaliacaoProdutoDTO> dtos = new ArrayList<AvaliacaoProdutoDTO>();

        List<AvaliacaoProduto> avaliacaoProdutos = avaliacaoProdutoRepository.avaliacaoProdutoPessoa(idProduto, idPessoa);

        for (AvaliacaoProduto avaliacaoProduto : avaliacaoProdutos) {

            AvaliacaoProdutoDTO avaliacaoProdutoDTO = new AvaliacaoProdutoDTO();
            avaliacaoProdutoDTO.setId(avaliacaoProduto.getId());
            avaliacaoProdutoDTO.setDescricao(avaliacaoProduto.getDescricao());
            avaliacaoProdutoDTO.setNota(avaliacaoProduto.getNota());
            avaliacaoProdutoDTO.setEmpresa(avaliacaoProduto.getEmpresa());
            avaliacaoProdutoDTO.setPessoa(avaliacaoProduto.getPessoa());
            avaliacaoProdutoDTO.setProduto(avaliacaoProduto.getProduto());
            dtos.add(avaliacaoProdutoDTO);

        }
        return new ResponseEntity<List<AvaliacaoProdutoDTO>>(dtos, HttpStatus.OK);
    }
}
