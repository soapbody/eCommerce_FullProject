package com.Mateus_Ulrich.eCommerce_FullProject.controller;

import com.Mateus_Ulrich.eCommerce_FullProject.CustomException;
import com.Mateus_Ulrich.eCommerce_FullProject.model.ContaPagar;

import com.Mateus_Ulrich.eCommerce_FullProject.repository.ContaPagarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ContaPagarController {
    @Autowired
    private ContaPagarRepository contaPagarRepository;

    @ResponseBody /*Poder dar um retorno da API*/
    @PostMapping(value = "**/salvarContaPagar") /*Mapeando a url para receber JSON*/
    public ResponseEntity<ContaPagar> salvarMarca(@RequestBody @Valid ContaPagar contaPagar) throws CustomException { /*Recebe o JSON e converte pra Objeto*/
        if(contaPagar.getEmpresa() == null || contaPagar.getEmpresa().getId() <= 0) {
            throw new CustomException("Empresa responsável deve ser informada");
        }
        if(contaPagar.getPessoa() == null || contaPagar.getPessoa().getId() <= 0) {
            throw new CustomException("Pessoa responsável deve ser informada");

        }if(contaPagar.getPessoa_fornecedor() == null || contaPagar.getPessoa_fornecedor().getId() <= 0) {
            throw new CustomException("Fornecedor responsável deve ser informada");

        }
        if (contaPagar.getId() == null) {
            List<ContaPagar> contas = contaPagarRepository.buscarContaPorDesc(contaPagar.getDescricao().toUpperCase().trim());
            if(!contas.isEmpty()) {
                throw new CustomException("Já existe conta a pagar com a mesma descrição");
            }

        }
        ContaPagar contaSalva = contaPagarRepository.save(contaPagar);

        return new ResponseEntity<ContaPagar>(contaSalva, HttpStatus.OK);
    }



    @ResponseBody /*Poder dar um retorno da API*/
    @PostMapping(value = "**/deleteContaPagar") /*Mapeando a url para receber JSON*/
    public ResponseEntity<?> deleteContaPagar(@RequestBody ContaPagar contaPagar) { /*Recebe o JSON e converte pra Objeto*/

        contaPagarRepository.deleteById(contaPagar.getId());

        return new ResponseEntity("Conta Removida",HttpStatus.OK);
    }


    //@Secured({ "ROLE_GERENTE", "ROLE_ADMIN" })
    @ResponseBody
    @DeleteMapping(value = "**/deleteContaPagarPorId/{id}")
    public ResponseEntity<?> deleteContaPagarPorId(@PathVariable("id") Long id) {

        contaPagarRepository.deleteById(id);

        return new ResponseEntity("Conta Removida",HttpStatus.OK);
    }



    @ResponseBody
    @GetMapping(value = "**/buscarContaPagarPorId/{id}")
    public ResponseEntity<ContaPagar> buscarContaPagarPorId(@PathVariable("id") Long id) throws CustomException {

        ContaPagar contaPagar = contaPagarRepository.findById(id).orElse(null);
        if(contaPagar == null) {
            throw new CustomException("Não existe Marca com o ID: " + id);
        }

        return new ResponseEntity<ContaPagar>(contaPagar,HttpStatus.OK);
    }



    @ResponseBody
    @GetMapping(value = "**/buscarContaPagarPorNome/{nome}")
    public ResponseEntity<List<ContaPagar>> buscarMarcaPorNome(@PathVariable("nome") String nome) throws CustomException {

        List<ContaPagar> contaPagar = contaPagarRepository.buscarContaPorDesc(nome.toUpperCase().trim());
        if(contaPagar == null) {
            throw new CustomException("Não existe Marca com esse nome: " + nome);
        }


        return new ResponseEntity<List<ContaPagar>>(contaPagar,HttpStatus.OK);
    }
}
