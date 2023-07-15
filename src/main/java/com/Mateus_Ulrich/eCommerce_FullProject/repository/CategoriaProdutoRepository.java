package com.Mateus_Ulrich.eCommerce_FullProject.repository;

import com.Mateus_Ulrich.eCommerce_FullProject.model.CategoriaProduto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CategoriaProdutoRepository extends JpaRepository<CategoriaProduto, Long> {
    @Query(nativeQuery = true, value = "select count(1) > 0 from categoria_produto where upper(trim(nome_desc)) = :nome_desc")
    boolean existeCategoria(@Param("nome_desc") String nomeDesc);

    @Query("select a from CategoriaProduto a where upper(trim(a.nomeDesc)) like %?1%")
    public List<CategoriaProduto> buscarCategoriaProduto(String nomeDesc);
}
