package com.Mateus_Ulrich.eCommerce_FullProject.controller;

import com.Mateus_Ulrich.eCommerce_FullProject.CustomException;
import com.Mateus_Ulrich.eCommerce_FullProject.enums.TipoPessoa;
import com.Mateus_Ulrich.eCommerce_FullProject.model.Endereco;
import com.Mateus_Ulrich.eCommerce_FullProject.model.PessoaFisica;
import com.Mateus_Ulrich.eCommerce_FullProject.model.PessoaJuridica;
import com.Mateus_Ulrich.eCommerce_FullProject.model.dto.CepDTO;
import com.Mateus_Ulrich.eCommerce_FullProject.model.dto.ConsultaCnpjDTO;
import com.Mateus_Ulrich.eCommerce_FullProject.repository.EnderecoRepository;
import com.Mateus_Ulrich.eCommerce_FullProject.repository.PessoaFisicaRepository;
import com.Mateus_Ulrich.eCommerce_FullProject.repository.PessoaJuridicaRepository;
import com.Mateus_Ulrich.eCommerce_FullProject.service.PessoaUserService;
import com.Mateus_Ulrich.eCommerce_FullProject.service.ServiceContagemAcessoApi;
import com.Mateus_Ulrich.eCommerce_FullProject.util.ValidaCNPJ;
import com.Mateus_Ulrich.eCommerce_FullProject.util.ValidaCPF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class PessoaController {
    @Autowired
    private PessoaJuridicaRepository pesssoaJuridicaRepository;
    @Autowired
    private PessoaFisicaRepository pessoaFisicaRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private PessoaUserService pessoaUserService;

    @Autowired
    private ServiceContagemAcessoApi serviceContagemAcessoApi;
    @ResponseBody
    @GetMapping(value = "**/consultaNomePF/{nome}")
    public ResponseEntity<List<PessoaFisica>> consultaNomePF(@PathVariable("nome") String nome) {
        List<PessoaFisica> fisicas = pessoaFisicaRepository.consultaNomePF(nome.trim().toUpperCase());
        return new ResponseEntity<List<PessoaFisica>>(fisicas, HttpStatus.OK);
    }
    @ResponseBody
    @GetMapping(value = "**/consultaCpfPF/{cpf}")
    public ResponseEntity<List<PessoaFisica>> consultaCpfPF(@PathVariable("cpf") String cpf) {
        List<PessoaFisica> fisicas = pessoaFisicaRepository.consultaCpfPF(cpf.trim().toUpperCase());

        serviceContagemAcessoApi.atualizaAcessoEndPoint();

        return new ResponseEntity<List<PessoaFisica>>(fisicas, HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping(value = "**/consultaCnpjPJ/{cnpj}")
    public ResponseEntity<List<PessoaJuridica>> consultaCnpjPJ(@PathVariable("cnpj") String cnpj) {
        List<PessoaJuridica> fisicas = pesssoaJuridicaRepository.existeCnpjCadastradoList(cnpj.trim().toUpperCase());
        return new ResponseEntity<List<PessoaJuridica>>(fisicas, HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping(value = "**/consultaCep/{cep}")
    public ResponseEntity<CepDTO> consultaCep(@PathVariable("cep") String cep) {
        CepDTO cepDTO = pessoaUserService.consultaCep(cep);

        return new ResponseEntity<CepDTO>(cepDTO, HttpStatus.OK);

    }

    @ResponseBody
    @GetMapping(value = "**/consultaCnpj/{cnpj}")
    public ResponseEntity<ConsultaCnpjDTO> consultaCnpj(@PathVariable("cnpj") String cnpj) {
        return new ResponseEntity<ConsultaCnpjDTO>(pessoaUserService.consultaCnpjReceita(cnpj), HttpStatus.OK);

    }

    /*end-point é microsservicos é um API*/
    @ResponseBody
    @PostMapping(value = "**/salvarPj")
    public ResponseEntity<PessoaJuridica> salvarPj(@RequestBody @Valid PessoaJuridica pessoaJuridica) throws CustomException {

        if (pessoaJuridica == null) {
            throw new CustomException("Pessoa juridica nao pode ser NULL");
        }
        if(pessoaJuridica.getTipoPessoa() == null) {
            throw new CustomException("Informe o tipo Jurídico ou Fornecedor da Loja");
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
        if(pessoaJuridica.getId() == null || pessoaJuridica.getId() <= 0) {
            for (int p = 0; p < pessoaJuridica.getEnderecos().size(); p++) {
                CepDTO cepDTO = pessoaUserService.consultaCep(pessoaJuridica.getEnderecos().get(p).getCep());
                pessoaJuridica.getEnderecos().get(p).setBairro(cepDTO.getBairro());
                pessoaJuridica.getEnderecos().get(p).setCidade(cepDTO.getLocalidade());
                pessoaJuridica.getEnderecos().get(p).setComplemento(cepDTO.getComplemento());
                pessoaJuridica.getEnderecos().get(p).setRuaLogra(cepDTO.getLogradouro());
                pessoaJuridica.getEnderecos().get(p).setUf(cepDTO.getUf());
            }}
        else
         {
             for (int p = 0; p < pessoaJuridica.getEnderecos().size(); p++) {
                    Endereco enderecoTemp = (Endereco) enderecoRepository.findById(pessoaJuridica.getEnderecos().get(p).getId()).get();
                        if(!enderecoTemp.getCep().equals(pessoaJuridica.getEnderecos().get(p).getCep())) {
                            CepDTO cepDTO = pessoaUserService.consultaCep(pessoaJuridica.getEnderecos().get(p).getCep());
                            pessoaJuridica.getEnderecos().get(p).setBairro(cepDTO.getBairro());
                            pessoaJuridica.getEnderecos().get(p).setCidade(cepDTO.getLocalidade());
                            pessoaJuridica.getEnderecos().get(p).setComplemento(cepDTO.getComplemento());
                            pessoaJuridica.getEnderecos().get(p).setRuaLogra(cepDTO.getLogradouro());
                            pessoaJuridica.getEnderecos().get(p).setUf(cepDTO.getUf());

                        }
                }



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
        if (pessoaFisica.getTipoPessoa() == null) {
            pessoaFisica.setTipoPessoa(TipoPessoa.FISICA.name());
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
