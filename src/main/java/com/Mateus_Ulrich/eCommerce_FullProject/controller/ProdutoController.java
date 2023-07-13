package com.Mateus_Ulrich.eCommerce_FullProject.controller;

import com.Mateus_Ulrich.eCommerce_FullProject.exceptions.CustomException;
import com.Mateus_Ulrich.eCommerce_FullProject.model.Produto;
import com.Mateus_Ulrich.eCommerce_FullProject.repository.ProdutoRepository;
import com.Mateus_Ulrich.eCommerce_FullProject.service.ServiceSendEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.mail.MessagingException;
import javax.validation.Valid;
import javax.xml.bind.DatatypeConverter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
@RestController
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private ServiceSendEmail serviceSendEmail;
	
	@ResponseBody /*Poder dar um retorno da API*/
	@PostMapping(value = "**/salvarProduto") /*Mapeando a url para receber JSON*/
	public ResponseEntity<Produto> salvarProduto(@RequestBody @Valid Produto produto) throws CustomException, MessagingException, IOException { /*Recebe o JSON e converte pra Objeto*/
		if(produto.getTipoUnidade() == null || produto.getTipoUnidade().trim().isEmpty()) {
			throw new CustomException("Tipo unidade deve ser informada");
		}
		if(produto.getNome().length() < 10) {
			throw new CustomException("Nome do produto deve ter mais de 10 letras");
		}
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
		if (produto.getQtdEstoque() < 1) {
			throw new CustomException("O produto deve ter no mínimo 1 quantidade.");
		}
		if (produto.getImagens() == null || produto.getImagens().isEmpty() || produto.getImagens().size() == 0) {
			throw new CustomException("Deve ser informado imagens para o produto");
		}
		if (produto.getImagens().size() < 3 || produto.getImagens().size() > 6) {
			throw new CustomException("Deve haver pelo menos 3 imagens e no máximo 6.");
		}
		if (produto.getId() == null) {
			for (int x = 0; x < produto.getImagens().size(); x++) {
				produto.getImagens().get(x).setProduto(produto);
				produto.getImagens().get(x).setEmpresa(produto.getEmpresa());
				String base64Image = "";
				if (produto.getImagens().get(x).getImagemOriginal().contains("data:image")) {
					base64Image = produto.getImagens().get(x).getImagemOriginal().split(",")[1];

				}else {
					base64Image = produto.getImagens().get(x).getImagemOriginal();
				}
				byte[] imageBytes = DatatypeConverter.parseBase64Binary(base64Image);
				BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(imageBytes));
				if (bufferedImage != null) {
					int type = bufferedImage.getType() ==  0 ? BufferedImage.TYPE_INT_ARGB : bufferedImage.getType();
					int largura =  Integer.parseInt("800");
					int altura = Integer.parseInt("600");
					BufferedImage resizedImage = new BufferedImage(largura, altura, type);
					Graphics2D g = resizedImage.createGraphics();
					g.drawImage(bufferedImage, 0, 0, largura, altura, null);
					g.dispose();

					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					ImageIO.write(resizedImage, "png", baos);
					String miniImgBase64 = "data:image/jpeg;base64," + DatatypeConverter.printBase64Binary(baos.toByteArray());
					produto.getImagens().get(x).setImagemMiniatura(miniImgBase64);
					bufferedImage.flush();
					resizedImage.flush();
					baos.flush();
					baos.close();
				}
			}
		}
		Produto produtoSalvo = produtoRepository.save(produto);
		if (produto.getAlertaQtdeEstoque() && produto.getQtdEstoque() <= 1) {
			StringBuilder html = new StringBuilder();
			html.append("<h2>").append("Produto: + " + produto.getNome()).append (" com estoque baixo: " + produto.getQtdEstoque()).append("</h2>)");
			html.append("<p> Id Produto: ").append(produto.getId()).append("</p>");

			if (produto.getEmpresa().getEmail() != null) {
				serviceSendEmail.enviarEmailHtml("Produto Com Estoque Baixo", html.toString(), produto.getEmpresa().getEmail());

			}

		}
		
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
