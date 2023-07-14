package com.Mateus_Ulrich.eCommerce_FullProject.controller;

import com.Mateus_Ulrich.eCommerce_FullProject.model.ImagemProduto;
import com.Mateus_Ulrich.eCommerce_FullProject.model.Produto;
import com.Mateus_Ulrich.eCommerce_FullProject.model.dto.ImagemProdutoDTO;
import com.Mateus_Ulrich.eCommerce_FullProject.repository.ImagemProdutoRepository;
import com.Mateus_Ulrich.eCommerce_FullProject.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ImagemProdutoController {
    @Autowired
    private ImagemProdutoRepository imagemProdutoRepository;
    @PostMapping(value = "**/salvarImagemProduto")
    @ResponseBody
    public ResponseEntity<ImagemProdutoDTO> salvarImagemProduto(@RequestBody ImagemProduto imagemProduto) {
        imagemProduto = imagemProdutoRepository.saveAndFlush(imagemProduto);
        ImagemProdutoDTO imagemProdutoDTO = new ImagemProdutoDTO();
        imagemProdutoDTO.setId(imagemProduto.getId());
        imagemProdutoDTO.setEmpresa(imagemProduto.getEmpresa().getId());
        imagemProdutoDTO.setProduto(imagemProduto.getProduto().getId());
        imagemProdutoDTO.setImagemMiniatura(imagemProduto.getImagemMiniatura());
        imagemProdutoDTO.setImagemOriginal(imagemProduto.getImagemOriginal());

        return new ResponseEntity<ImagemProdutoDTO>(imagemProdutoDTO, HttpStatus.OK);
    }

   // @PostMapping(value = "**/salvarImagemProduto")
   // @ResponseBody
   /*
    public ResponseEntity<?> salvarImagemProduto(@RequestBody ImagemProdutoDTO imagemProdutoDTO) {
        // Recuperar o produto com base no ID fornecido no DTO
        Produto produto = produtoRepository.findById(imagemProdutoDTO.getProduto().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado"));

        // Criar o objeto ImagemProduto com base nos dados do DTO
        ImagemProduto imagemProduto = new ImagemProduto();
        imagemProduto.setImagemOriginal(imagemProdutoDTO.getImagemOriginal());
        imagemProduto.setImagemMiniatura(imagemProdutoDTO.getImagemMiniatura());
        imagemProduto.setProduto(produto);

        // Salvar o ImagemProduto no banco de dados
        imagemProdutoRepository.save(imagemProduto);

        return ResponseEntity.ok("ImagemProduto salvo com sucesso");
    }
    */

    @DeleteMapping(value = "**/deleteTodasImagens/{idProduto}")
    @ResponseBody
    public ResponseEntity<?> deleteTodasImagens(@PathVariable("idProduto") Long idProduto) {
        imagemProdutoRepository.deleteTodasImagens(idProduto);
        return new ResponseEntity<>("Imagens do produto removida", HttpStatus.OK);

    }

    @DeleteMapping(value = "**/deleteImagemObjeto")
    @ResponseBody
    public ResponseEntity<?> deleteImagemObjeto(@RequestBody ImagemProduto imagemProduto) {
        if (!imagemProdutoRepository.existsById(imagemProduto.getId())) {
            return new ResponseEntity<String>("Imagem já foi removida ou não existe com esse id: " + imagemProduto.getId(), HttpStatus.OK);
        }else {
            imagemProdutoRepository.deleteById(imagemProduto.getId());
            return new ResponseEntity<>("imagem removida", HttpStatus.OK);
        }


    }
    @DeleteMapping(value = "**/deleteImagemProdutoPorId/{id}")
    @ResponseBody
    public ResponseEntity<?> deleteImagemProdutoPorId(@PathVariable("id") Long id) {
        if (!imagemProdutoRepository.existsById(id)) {
            return new ResponseEntity<String>("Imagem já foi removida ou não existe com esse id: " + id, HttpStatus.OK);
        }else {
            imagemProdutoRepository.deleteById(id);
            return new ResponseEntity<>("imagem removida", HttpStatus.OK);
        }

    }
    @GetMapping(value = "**/buscarImagemPorProduto/{idProduto}")
    @ResponseBody
    public ResponseEntity<List<ImagemProdutoDTO>> buscarImagemPorProduto(@PathVariable("idProduto")Long idProduto) {
        List<ImagemProdutoDTO> dtos = new ArrayList<ImagemProdutoDTO>();

        List<ImagemProduto> imagemProdutos = imagemProdutoRepository.buscarImagemPorProduto(idProduto);


        for (ImagemProduto imagemProduto : imagemProdutos) {

            ImagemProdutoDTO imagemProdutoDTO = new ImagemProdutoDTO();
            imagemProdutoDTO.setId(imagemProduto.getId());
            imagemProdutoDTO.setEmpresa(imagemProduto.getEmpresa().getId());
            imagemProdutoDTO.setProduto(imagemProduto.getProduto().getId());
            imagemProdutoDTO.setImagemMiniatura(imagemProduto.getImagemMiniatura());
            imagemProdutoDTO.setImagemOriginal(imagemProduto.getImagemOriginal());
            dtos.add(imagemProdutoDTO);
        }
        return new ResponseEntity<List<ImagemProdutoDTO>>(dtos, HttpStatus.OK);
    }

}
