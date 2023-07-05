package com.Mateus_Ulrich.eCommerce_FullProject.repository;

import com.Mateus_Ulrich.eCommerce_FullProject.model.PessoaFisica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface PessoaFisicaRepository extends JpaRepository<PessoaFisica, Long> {

    @Query(value = "select pf from PessoaFisica pf where pf.cpf = ?1")
    public PessoaFisica existeCpfCadastrado(String cpf);
}
