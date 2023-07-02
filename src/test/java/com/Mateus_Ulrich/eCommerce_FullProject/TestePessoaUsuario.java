package com.Mateus_Ulrich.eCommerce_FullProject;

import com.Mateus_Ulrich.eCommerce_FullProject.controller.PessoaController;
import com.Mateus_Ulrich.eCommerce_FullProject.model.PessoaJuridica;
import com.Mateus_Ulrich.eCommerce_FullProject.repository.PessoaRepository;
import com.Mateus_Ulrich.eCommerce_FullProject.service.PessoaUserService;
import junit.framework.TestCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

import java.util.Calendar;

@Profile("test")
@SpringBootTest(classes = ECommerce_FullProject.class)
public class TestePessoaUsuario extends TestCase {
    @Autowired
    private PessoaController pessoaController;

    @Test
    public void testCadPessoaFisica() throws CustomException {

        PessoaJuridica pessoaJuridica = new PessoaJuridica();
        pessoaJuridica.setCnpj("" + Calendar.getInstance().getTimeInMillis());
        pessoaJuridica.setNome("Mateus Ulrich");
        pessoaJuridica.setEmail("mateus_ti98@gmail.com");
        pessoaJuridica.setTelefone("45999795800");
        pessoaJuridica.setInscEstadual("65556565656665");
        pessoaJuridica.setInscMunicipal("55554565656565");
        pessoaJuridica.setNomeFantasia("54556565665");
        pessoaJuridica.setRazaoSocial("4656656566");

        pessoaController.salvarPj(pessoaJuridica);

		/*
		PessoaFisica pessoaFisica = new PessoaFisica();

		pessoaFisica.setCpf("0597975788");
		pessoaFisica.setNome("Mateus ulrich");
		pessoaFisica.setEmail("mateus_teti@gmail.com");
		pessoaFisica.setTelefone("479993795800");
		pessoaFisica.setEmpresa(pessoaFisica);*/

    }

}
