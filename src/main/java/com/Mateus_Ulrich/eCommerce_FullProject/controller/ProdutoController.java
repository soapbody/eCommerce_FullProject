package com.Mateus_Ulrich.eCommerce_FullProject.controller;

import com.Mateus_Ulrich.eCommerce_FullProject.CustomException;
import com.Mateus_Ulrich.eCommerce_FullProject.model.Acesso;
import com.Mateus_Ulrich.eCommerce_FullProject.model.PessoaFisica;
import com.Mateus_Ulrich.eCommerce_FullProject.model.Produto;
import com.Mateus_Ulrich.eCommerce_FullProject.repository.AcessoRepository;
import com.Mateus_Ulrich.eCommerce_FullProject.repository.ProdutoRepository;
import com.Mateus_Ulrich.eCommerce_FullProject.service.AcessoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RestController
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@ResponseBody /*Poder dar um retorno da API*/
	@PostMapping(value = "**/salvarProduto") /*Mapeando a url para receber JSON*/
	public ResponseEntity<Produto> salvarProduto(@RequestBody @Valid Produto produto) throws CustomException { /*Recebe o JSON e converte pra Objeto*/
		if(produto.getEmpresa() == null || produto.getEmpresa().getId() <= 0) {
			throw new CustomException("Empresa responsável deve ser informada");
		}

		if(produto.getId() == null) {
			List<Produto> produtos = produtoRepository.buscarPorNome2(produto.getNome().toUpperCase().trim(), produto.getEmpresa().getId());
			if(!produtos.isEmpty()) {
				throw new CustomException("Já existe Produto com esse Nome: " + produto.getNome());
			}
		}

		if(produto.getCategoriaProduto() == null || produto.getCategoriaProduto().getId() <= 0) {
			throw new CustomException("Categoria do produto deve ser informada");
		}
		if(produto.getMarcaProduto() == null || produto.getMarcaProduto().getId() <= 0) {
			throw new CustomException("Marca do produto deve ser informada");
		}

		Produto produtoSalvo = produtoRepository.save(produto);
		
		return new ResponseEntity<Produto>(produtoSalvo, HttpStatus.OK);
	}
	
	
	
	@ResponseBody /*Poder dar um retorno da API*/
	@PostMapping(value = "**/deleteProduto") /*Mapeando a url para receber JSON*/
	public ResponseEntity<?> deleteProduto(@RequestBody Produto produto) { /*Recebe o JSON e converte pra Objeto*/

		produtoRepository.deleteById(produto.getId());
		
		return new ResponseEntity("Produto Removido",HttpStatus.OK);
	}
	

	//@Secured({ "ROLE_GERENTE", "ROLE_ADMIN" })
	@ResponseBody
	@DeleteMapping(value = "**/deleteProdutoPorId/{id}")
	public ResponseEntity<?> deleteProdutoPorId(@PathVariable("id") Long id) {

		produtoRepository.deleteById(id);
		
		return new ResponseEntity("Produto Removido",HttpStatus.OK);
	}
	
	
	
	@ResponseBody
	@GetMapping(value = "**/obterProduto/{id}")
	public ResponseEntity<Produto> obterProduto(@PathVariable("id") Long id) {

		Produto produto = produtoRepository.findById(id).get();
		
		return new ResponseEntity<Produto>(produto,HttpStatus.OK);
	}


	@ResponseBody
	@GetMapping(value = "**/buscarPorNome/{nome}")
	public ResponseEntity<List<Produto>> buscarPorNome(@PathVariable("nome") String nome) {
		List<Produto> produtos = produtoRepository.buscarPorNome1(nome.trim().toUpperCase());
		return new ResponseEntity<List<Produto>>(produtos, HttpStatus.OK);
	}
	
	
	

}
