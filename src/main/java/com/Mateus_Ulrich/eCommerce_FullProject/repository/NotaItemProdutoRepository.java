package com.Mateus_Ulrich.eCommerce_FullProject.repository;

import com.Mateus_Ulrich.eCommerce_FullProject.model.NotaItemProduto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface NotaItemProdutoRepository extends JpaRepository<NotaItemProduto, Long> {
    @Query("select a from NotaItemProduto a where a.produto.id = ?1 and a.notaFiscalCompra.id = ?2")
    List<NotaItemProduto> buscarItemPorProdutoENota(Long idProduto, Long idNotaFiscal);
    @Query("select a from NotaItemProduto a where a.produto.id = ?1")
    List<NotaItemProduto> buscarNotaItemPorProduto(Long idProduto);
    @Query("select a from NotaItemProduto a where a.notaFiscalCompra.id = ?1")
    List<NotaItemProduto> buscarNotaItemPorNotaFiscal(Long idNotaFiscal);
    @Query("select a from NotaItemProduto a where a.empresa.id = ?1")
    List<NotaItemProduto> buscarNotaItemPorEmpresa(Long idEmpresa);
}
