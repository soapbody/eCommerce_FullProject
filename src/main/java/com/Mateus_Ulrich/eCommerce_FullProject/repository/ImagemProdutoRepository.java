package com.Mateus_Ulrich.eCommerce_FullProject.repository;

import com.Mateus_Ulrich.eCommerce_FullProject.model.ImagemProduto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface ImagemProdutoRepository extends JpaRepository<ImagemProduto, Long> {
    @Query("select a from ImagemProduto a where a.produto.id = ?1")
    List<ImagemProduto> buscarImagemPorProduto(Long id);
    @Transactional
    @Modifying(flushAutomatically = true)
    @Query(nativeQuery = true, value = "delete from imagem_produto where produto_id = ?1")
    void deleteTodasImagens(Long idProduto);


}
