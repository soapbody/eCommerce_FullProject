package com.Mateus_Ulrich.eCommerce_FullProject.repository;

import com.Mateus_Ulrich.eCommerce_FullProject.model.PessoaJuridica;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PessoaJuridicaRepository extends CrudRepository<PessoaJuridica, Long> {
    @Query(value = "select pj from PessoaJuridica pj where (trim(pj.nome)) like %?1%")
    public List<PessoaJuridica> consultaNomePJ(String nome);

    @Query(value = "select pj from PessoaJuridica pj where (trim(pj.cnpj)) like %?1%")
    public List<PessoaJuridica> consultaCnpjPJ(String cnpj);

    @Query(value = "select pj from PessoaJuridica pj where pj.cnpj = ?1")
    public PessoaJuridica existeCnpjCadastrado(String cnpj);

    @Query(value = "select pj from PessoaJuridica pj where pj.cnpj = ?1")
    public List<PessoaJuridica> existeCnpjCadastradoList(String cnpj);

    @Query(value = "select pj from PessoaJuridica pj where pj.inscEstadual = ?1")
    public PessoaJuridica existeInscEstadual(String inscEstadual);

    @Query(value = "select pj from PessoaJuridica pj where pj.inscEstadual = ?1")
    public List<PessoaJuridica> existeInscEstadualList(String inscEstadual);
}
