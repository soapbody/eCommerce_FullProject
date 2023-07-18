package com.Mateus_Ulrich.eCommerce_FullProject.controller;

import com.Mateus_Ulrich.eCommerce_FullProject.model.StatusRastreio;
import com.Mateus_Ulrich.eCommerce_FullProject.model.dto.StatusRastreioDTO;
import com.Mateus_Ulrich.eCommerce_FullProject.repository.StatusRastreioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class StatusRastreioController {

    @Autowired
    private StatusRastreioRepository repository;
    @ResponseBody
    @GetMapping(value = "**/listRastreioVenda/{id}")
    public ResponseEntity<List<StatusRastreioDTO>> listRastreioVenda(@PathVariable("id") Long id) {
        List<StatusRastreio> status = repository.listRastreioVenda(id);

        List<StatusRastreioDTO> dtos = status.stream().map(StatusRastreioDTO::new).collect(Collectors.toList());
        return new ResponseEntity<List<StatusRastreioDTO>>(dtos,HttpStatus.OK);
    }
}
