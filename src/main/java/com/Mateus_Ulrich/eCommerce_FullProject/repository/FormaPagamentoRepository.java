package com.Mateus_Ulrich.eCommerce_FullProject.repository;

import com.Mateus_Ulrich.eCommerce_FullProject.model.FormaPagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FormaPagamentoRepository extends JpaRepository<FormaPagamento, Long> {

    @Query(value = "select f from FormaPagamento f where f.empresa.id = ?1")
    List<FormaPagamento> findAll(Long id);
}
