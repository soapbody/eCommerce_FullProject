package com.Mateus_Ulrich.eCommerce_FullProject.repository;

import com.Mateus_Ulrich.eCommerce_FullProject.model.VendaCompraLojaVirtual;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
@Transactional
public interface VendaCompraLojaVirtualRepository extends JpaRepository<VendaCompraLojaVirtual, Long> {
    @Query(value = "select a from VendaCompraLojaVirtual a where a.id = ?1 and a.excluido = false")
     VendaCompraLojaVirtual findByIdExclusao(Long id);
    @Query(value = "select i.vendaCompraLojaVirtual from ItemVendaLoja i where i.vendaCompraLojaVirtual.excluido = false and i.produto.id = ?1")
    List<VendaCompraLojaVirtual> vendaPorProduto(Long idProduto);
    @Query(value = "select distinct(i.vendaCompraLojaVirtual) from ItemVendaLoja i where i.vendaCompraLojaVirtual.excluido = false and upper(trim(i.produto.nome)) like %?1%")
    List<VendaCompraLojaVirtual> vendaPorNomeProduto(String valor);
    @Query(value = "select distinct(i.vendaCompraLojaVirtual) from ItemVendaLoja i where i.vendaCompraLojaVirtual.excluido = false and i.vendaCompraLojaVirtual.pessoa.id = :id")
    List<VendaCompraLojaVirtual> vendaPorCliente(@Param("id") Long id);
    @Query(value = "select distinct(i.vendaCompraLojaVirtual) from ItemVendaLoja i where i.vendaCompraLojaVirtual.excluido = false and upper(trim(i.vendaCompraLojaVirtual.pessoa.nome)) like %?1%")
    List<VendaCompraLojaVirtual> vendaPorNomeCliente(String valor);
    @Query(value = "select distinct(i.vendaCompraLojaVirtual) from ItemVendaLoja i where i.vendaCompraLojaVirtual.excluido = false and upper(trim(i.vendaCompraLojaVirtual.enderecoCobranca.ruaLogra)) like %?1%")
    List<VendaCompraLojaVirtual> vendaPorEnderecoCobranca(String valor);
    @Query(value = "select distinct(i.vendaCompraLojaVirtual) from ItemVendaLoja i where i.vendaCompraLojaVirtual.excluido = false and upper(trim(i.vendaCompraLojaVirtual.enderecoEntrega.ruaLogra)) like %?1%")
    List<VendaCompraLojaVirtual> vendaPorEnderecoEntrega(String valor);
    //@Query("SELECT DISTINCT i.vendaCompraLojaVirtual FROM ItemVendaLoja i WHERE i.vendaCompraLojaVirtual.excluido = false AND i.vendaCompraLojaVirtual.dataVenda >= :data1 AND i.vendaCompraLojaVirtual.dataVenda <= :data2")
    //List<VendaCompraLojaVirtual> consultaVendaFaixaData(@Param("data1") Date data1, @Param("data2") Date data2);
    @Query(value = "SELECT * FROM vd_cp_loja_virt WHERE data_venda >= :data1 AND data_venda <= :data2", nativeQuery = true)
    List<VendaCompraLojaVirtual> consultaVendaFaixaData(@Param("data1") Date data1, @Param("data2") Date data2);
    @Query(value = "select distinct(i.vendaCompraLojaVirtual) from ItemVendaLoja i where i.vendaCompraLojaVirtual.excluido = false and (upper(trim(i.vendaCompraLojaVirtual.pessoa.cpf)) = ?1 OR upper(trim(i.vendaCompraLojaVirtual.empresa.cnpj)) = ?1)")
    List<VendaCompraLojaVirtual> consultaVendaCpfCnpj(String value);
}
