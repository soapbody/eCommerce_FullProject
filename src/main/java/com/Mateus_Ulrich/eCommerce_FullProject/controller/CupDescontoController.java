package com.Mateus_Ulrich.eCommerce_FullProject.controller;

import com.Mateus_Ulrich.eCommerce_FullProject.exceptions.CustomException;
import com.Mateus_Ulrich.eCommerce_FullProject.model.CupDesc;
import com.Mateus_Ulrich.eCommerce_FullProject.repository.CupDescontoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CupDescontoController {
    @Autowired
    private CupDescontoRepository cupDescontoRepository;

    @ResponseBody
    @PostMapping(value = "**/salvarCupDesc")
    public ResponseEntity<CupDesc> salvarCupDesc(@RequestBody @Valid CupDesc cupDesc) {
        return new ResponseEntity<CupDesc>(cupDescontoRepository.save(cupDesc), HttpStatus.OK);

    }
    @ResponseBody
    @GetMapping(value = "**/buscarCuponsPorEmpresa/{id}")
    public ResponseEntity<List<CupDesc>> buscarCuponsPorEmpresa(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<List<CupDesc>>(cupDescontoRepository.buscarCuponsPorEmpresa(id),HttpStatus.OK);

    }
    @ResponseBody
    @GetMapping(value = "**/buscarCuponPorId/{id}")
    public ResponseEntity<CupDesc> buscarCuponPorId(@PathVariable(value = "id") Long id) throws CustomException {
        CupDesc cupDesc = cupDescontoRepository.findById(id).orElse(null);
        if (cupDesc == null) {
            throw new CustomException("Não foi encontrado Cupon com o código: " + id);
        }
        return new ResponseEntity<CupDesc>(cupDesc,HttpStatus.OK);

    }
    @ResponseBody
    @GetMapping(value = "**/buscarCuponLista")
    public ResponseEntity<List<CupDesc>> buscarCuponLista() throws CustomException {
        return new ResponseEntity<List<CupDesc>>(cupDescontoRepository.findAll(),HttpStatus.OK);

    }
    @ResponseBody
    @DeleteMapping(value = "**/deletarCuponPorId/{id}")
    public ResponseEntity<?> deletarCuponPorId(@PathVariable(value = "id") Long id) {
        cupDescontoRepository.deleteById(id);
        return new ResponseEntity("Cupon removido com sucesso", HttpStatus.OK);
    }
}
