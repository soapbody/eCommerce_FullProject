package com.Mateus_Ulrich.eCommerce_FullProject.repository;

import com.Mateus_Ulrich.eCommerce_FullProject.model.Produto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface ProdutoRepository extends CrudRepository<Produto, Long> {
    @Query("select a from Produto a where upper(trim(a.nome)) like %?1%")
    List<Produto> buscarPorNome1(String nome);

    @Query("select a from Produto a where upper(trim(a.nome)) like %?1% and a.empresa.id = ?2")
    List<Produto> buscarPorNome2(String nome, Long idEmpresa);


}
