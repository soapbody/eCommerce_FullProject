package com.Mateus_Ulrich.eCommerce_FullProject.repository;

import com.Mateus_Ulrich.eCommerce_FullProject.model.NotaFiscalVenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface NotaFiscalVendaRepository extends JpaRepository<NotaFiscalVenda, Long> {
    @Query(value = "select n from NotaFiscalVenda n where n.vendaCompraLojaVirtual.id = ?1")
    List<NotaFiscalVenda> buscaNotaPorVendaList(Long id);

    @Query(value = "select n from NotaFiscalVenda n where n.vendaCompraLojaVirtual.id = ?1")
    NotaFiscalVenda buscaNotaPorVenda(Long id);
}
