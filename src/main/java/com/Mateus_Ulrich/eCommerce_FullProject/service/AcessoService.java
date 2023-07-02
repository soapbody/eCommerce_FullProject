package com.Mateus_Ulrich.eCommerce_FullProject.service;

import com.Mateus_Ulrich.eCommerce_FullProject.model.Acesso;
import com.Mateus_Ulrich.eCommerce_FullProject.repository.AcessoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AcessoService {
	
	@Autowired
	private AcessoRepository acessoRepository;
	
	
	public Acesso save(Acesso acesso) {
		
		/*Qualquer tipo de validação*/
		
		return acessoRepository.save(acesso);
	}

}
