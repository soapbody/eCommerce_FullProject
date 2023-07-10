package com.Mateus_Ulrich.eCommerce_FullProject.repository;

import com.Mateus_Ulrich.eCommerce_FullProject.model.ContaPagar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface ContaPagarRepository extends JpaRepository<ContaPagar, Long> {
    @Query("select a from ContaPagar a where upper(trim(a.descricao)) like %?1%")
    List<ContaPagar> buscarContaPorDesc(String desc);

    @Query("select a from ContaPagar a where upper(trim(a.pessoa.id)) = ?1")
    List<ContaPagar> buscarContaPorPessoa(Long id);

    @Query("select a from ContaPagar a where upper(trim(a.pessoa_fornecedor.id)) = ?1")
    List<ContaPagar> buscarContaPorFornecedor(Long id);

    @Query("select a from ContaPagar a where upper(trim(a.empresa.id)) = ?1")
    List<ContaPagar> buscarContaPorEmpresa(Long id);
}
