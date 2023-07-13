package com.Mateus_Ulrich.eCommerce_FullProject.repository;

import com.Mateus_Ulrich.eCommerce_FullProject.model.NotaFiscalCompra;
import org.aspectj.weaver.ast.Not;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface NotaFiscalCompraRepository extends JpaRepository<NotaFiscalCompra, Long> {
    @Query("select a from NotaFiscalCompra a where upper(trim(a.descricaoObs)) like %?1%")
    List<NotaFiscalCompra> buscarNotaDesc(String desc);
    @Query(nativeQuery = true, value = "select count(1) > 0 from nota_fiscal_compra where upper(trim(descricao_obs)) like %?1%")
    boolean existeNotaComDescricao(String desc);
    @Query("select a from NotaFiscalCompra a where a.pessoa.id = ?1")
    List<NotaFiscalCompra> buscaNotaPorPessoa(Long idPessoa);
    @Query("select a from NotaFiscalCompra a where a.contaPagar.id = ?1")
    List<NotaFiscalCompra> buscaNotaContaPagar(Long idContaPagar);
    @Query("select a from NotaFiscalCompra a where a.empresa.id = ?1")
    List<NotaFiscalCompra> buscaNotaPorEmpresa(Long idEmpresa);
    @Transactional
    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query(nativeQuery = true, value = "delete from nota_item_produto where nota_fiscal_compra_id = ?1")
    void deleteItemNotaFiscalCompra(Long idNotaFiscalCompra);
}
