package com.Mateus_Ulrich.eCommerce_FullProject.controller;

import com.Mateus_Ulrich.eCommerce_FullProject.CustomException;
import com.Mateus_Ulrich.eCommerce_FullProject.model.MarcaProduto;
import com.Mateus_Ulrich.eCommerce_FullProject.repository.MarcaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@RestController
public class MarcaController {

    @Autowired
    private MarcaRepository marcaRepository;

    @ResponseBody /*Poder dar um retorno da API*/
    @PostMapping(value = "**/salvarMarca") /*Mapeando a url para receber JSON*/
    public ResponseEntity<MarcaProduto> salvarMarca(@RequestBody @Valid MarcaProduto marcaProduto) throws CustomException { /*Recebe o JSON e converte pra Objeto*/
        if(marcaProduto.getEmpresa() == null || marcaProduto.getEmpresa().getId() <= 0) {
            throw new CustomException("Empresa responsável deve ser informada");
        }

        if(marcaProduto.getId() == null) {
            List<MarcaProduto> marcas = marcaRepository.buscarMarcaPorNome2(marcaProduto.getNomeDesc().toUpperCase().trim(), marcaProduto.getEmpresa().getId());
            if(!marcas.isEmpty()) {
                throw new CustomException("Já existe Marca com esse Nome: " + marcaProduto.getNomeDesc());
            }
        }

        MarcaProduto marcaSalva = marcaRepository.save(marcaProduto);

        return new ResponseEntity<MarcaProduto>(marcaSalva, HttpStatus.OK);
    }



    @ResponseBody /*Poder dar um retorno da API*/
    @PostMapping(value = "**/deleteMarca") /*Mapeando a url para receber JSON*/
    public ResponseEntity<?> deleteMarca(@RequestBody MarcaProduto marcaProduto) { /*Recebe o JSON e converte pra Objeto*/

        marcaRepository.deleteById(marcaProduto.getId());

        return new ResponseEntity("Marca Removido",HttpStatus.OK);
    }


    //@Secured({ "ROLE_GERENTE", "ROLE_ADMIN" })
    @ResponseBody
    @DeleteMapping(value = "**/deleteMarcaPorId/{id}")
    public ResponseEntity<?> deleteMarcaPorId(@PathVariable("id") Long id) {

        marcaRepository.deleteById(id);

        return new ResponseEntity("Marca Removida",HttpStatus.OK);
    }



    @ResponseBody
    @GetMapping(value = "**/buscarMarcaPorId/{id}")
    public ResponseEntity<MarcaProduto> buscarMarcaPorId(@PathVariable("id") Long id) throws CustomException {

        MarcaProduto marcaProduto = marcaRepository.findById(id).orElse(null);
        if(marcaProduto == null) {
            throw new CustomException("Não existe Marca com o ID: " + id);
        }

        return new ResponseEntity<MarcaProduto>(marcaProduto,HttpStatus.OK);
    }



    @ResponseBody
    @GetMapping(value = "**/buscarMarcaPorNome/{nome}")
    public ResponseEntity<List<MarcaProduto>> buscarMarcaPorNome(@PathVariable("nome") String nome) throws CustomException {

        List<MarcaProduto> marcaProduto = marcaRepository.buscarMarcaPorNome1(nome.toUpperCase().trim());
        if(marcaProduto == null) {
            throw new CustomException("Não existe Marca com esse nome: " + nome);
        }


        return new ResponseEntity<List<MarcaProduto>>(marcaProduto,HttpStatus.OK);
    }
}
