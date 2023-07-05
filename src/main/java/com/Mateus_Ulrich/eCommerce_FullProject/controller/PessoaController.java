package com.Mateus_Ulrich.eCommerce_FullProject.controller;

import com.Mateus_Ulrich.eCommerce_FullProject.CustomException;
import com.Mateus_Ulrich.eCommerce_FullProject.model.PessoaFisica;
import com.Mateus_Ulrich.eCommerce_FullProject.model.PessoaJuridica;
import com.Mateus_Ulrich.eCommerce_FullProject.repository.PessoaFisicaRepository;
import com.Mateus_Ulrich.eCommerce_FullProject.repository.PessoaJuridicaRepository;
import com.Mateus_Ulrich.eCommerce_FullProject.service.PessoaUserService;
import com.Mateus_Ulrich.eCommerce_FullProject.util.ValidaCNPJ;
import com.Mateus_Ulrich.eCommerce_FullProject.util.ValidaCPF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class PessoaController {
    @Autowired
    private PessoaJuridicaRepository pesssoaJuridicaRepository;
    @Autowired
    private PessoaFisicaRepository pessoaFisicaRepository;

    @Autowired
    private PessoaUserService pessoaUserService;

    /*end-point é microsservicos é um API*/
    @ResponseBody
    @PostMapping(value = "**/salvarPj")
    public ResponseEntity<PessoaJuridica> salvarPj(@RequestBody @Valid PessoaJuridica pessoaJuridica) throws CustomException {

        if (pessoaJuridica == null) {
            throw new CustomException("Pessoa juridica nao pode ser NULL");
        }

        if (pessoaJuridica.getId() == null && pesssoaJuridicaRepository.existeCnpjCadastrado(pessoaJuridica.getCnpj()) != null) {
            throw new CustomException("Já existe CNPJ cadastrado com o número: " + pessoaJuridica.getCnpj());
        }
        if (pessoaJuridica.getId() == null && pesssoaJuridicaRepository.existeInscEstadual(pessoaJuridica.getInscEstadual()) != null) {
            throw new CustomException("Já existe Inscrição estadual cadastrado com o número: " + pessoaJuridica.getInscEstadual());
        }
        if(!ValidaCNPJ.isCNPJ(pessoaJuridica.getCnpj())) {
            throw new CustomException("Cnpj : " + pessoaJuridica.getCnpj() + " está inválido.");
        }

        pessoaJuridica = pessoaUserService.salvarPessoaJuridica(pessoaJuridica);

        return new ResponseEntity<PessoaJuridica>(pessoaJuridica, HttpStatus.OK);
    }


    @ResponseBody
    @PostMapping(value = "**/salvarPf")
    public ResponseEntity<PessoaFisica> salvarPf(@RequestBody @Valid PessoaFisica pessoaFisica) throws CustomException {

        if (pessoaFisica == null) {
            throw new CustomException("Pessoa fisica nao pode ser NULL");
        }

        if (pessoaFisica.getId() == null && pessoaFisicaRepository.existeCpfCadastrado(pessoaFisica.getCpf()) != null) {
            throw new CustomException("Já existe CPF cadastrado com o número: " + pessoaFisica.getCpf());
        }
        if(!ValidaCPF.isCPF(pessoaFisica.getCpf())) {
            throw new CustomException("Cpf : " + pessoaFisica.getCpf() + " está inválido.");
        }

        pessoaFisica = pessoaUserService.salvarPessoaFisica(pessoaFisica);

        return new ResponseEntity<PessoaFisica>(pessoaFisica, HttpStatus.OK);
    }
}
