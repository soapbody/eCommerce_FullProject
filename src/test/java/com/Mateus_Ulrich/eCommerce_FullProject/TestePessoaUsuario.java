package com.Mateus_Ulrich.eCommerce_FullProject;

import com.Mateus_Ulrich.eCommerce_FullProject.controller.PessoaController;
import com.Mateus_Ulrich.eCommerce_FullProject.enums.TipoEndereco;
import com.Mateus_Ulrich.eCommerce_FullProject.model.Endereco;
import com.Mateus_Ulrich.eCommerce_FullProject.model.PessoaJuridica;

import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

import java.util.Calendar;

@Profile("test")
@SpringBootTest(classes = ECommerce_FullProject.class)
public class TestePessoaUsuario {
    @Autowired
    private PessoaController pessoaController;

    @Test
    public void testCadPessoaFisica() throws CustomException {

        PessoaJuridica pessoaJuridica = new PessoaJuridica();
        pessoaJuridica.setCnpj("" + Calendar.getInstance().getTimeInMillis());
        pessoaJuridica.setNome("Mateus Ulrich2");
        pessoaJuridica.setEmail("mateus_teti669@gmail.com");
        pessoaJuridica.setTelefone("45999795800");
        pessoaJuridica.setInscEstadual("65556565656665");
        pessoaJuridica.setInscMunicipal("55554565656565");
        pessoaJuridica.setNomeFantasia("54556565665");
        pessoaJuridica.setRazaoSocial("4656656566");

        Endereco endereco1 = new Endereco();
        endereco1.setBairro("imigrantes");
        endereco1.setCep("2342424");
        endereco1.setComplemento("Casa cinza");
        endereco1.setEmpresa(pessoaJuridica);
        endereco1.setNumero("389");
        endereco1.setPessoa(pessoaJuridica);
        endereco1.setRuaLogra("Av sao joao sexto");
        endereco1.setTipoEndereco(TipoEndereco.COBRANCA);
        endereco1.setUf("PR");
        endereco1.setCidade("Guabiruba");

        Endereco endereco2 = new Endereco();
        endereco2.setBairro("imigrantes");
        endereco2.setCep("2342424");
        endereco2.setComplemento("Casa cinza");
        endereco2.setEmpresa(pessoaJuridica);
        endereco2.setNumero("555");
        endereco2.setPessoa(pessoaJuridica);
        endereco2.setRuaLogra("Av maringá");
        endereco2.setTipoEndereco(TipoEndereco.ENTREGA);
        endereco2.setUf("PR");
        endereco2.setCidade("Guabiruba");
        pessoaJuridica.getEnderecos().add(endereco2);
        pessoaJuridica.getEnderecos().add(endereco1);



        pessoaJuridica = pessoaController.salvarPj(pessoaJuridica).getBody();
        Assertions.assertEquals(true, pessoaJuridica.getId() > 0);

        for (Endereco endereco : pessoaJuridica.getEnderecos()) {
            Assertions.assertEquals(true, endereco.getId() > 0);
        }
        Assertions.assertEquals(2, pessoaJuridica.getEnderecos().size());

		/*
		PessoaFisica pessoaFisica = new PessoaFisica();

		pessoaFisica.setCpf("0597975788");
		pessoaFisica.setNome("Mateus ulrich");
		pessoaFisica.setEmail("mateus_teti@gmail.com");
		pessoaFisica.setTelefone("479993795800");
		pessoaFisica.setEmpresa(pessoaFisica);*/

    }

}
