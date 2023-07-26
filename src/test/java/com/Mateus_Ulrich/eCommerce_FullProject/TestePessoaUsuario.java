package com.Mateus_Ulrich.eCommerce_FullProject;

import com.Mateus_Ulrich.eCommerce_FullProject.controller.FormaPagamentoController;
import com.Mateus_Ulrich.eCommerce_FullProject.controller.PessoaController;
import com.Mateus_Ulrich.eCommerce_FullProject.enums.TipoEndereco;
import com.Mateus_Ulrich.eCommerce_FullProject.exceptions.CustomException;
import com.Mateus_Ulrich.eCommerce_FullProject.model.Endereco;
import com.Mateus_Ulrich.eCommerce_FullProject.model.PessoaFisica;
import com.Mateus_Ulrich.eCommerce_FullProject.model.PessoaJuridica;
import com.Mateus_Ulrich.eCommerce_FullProject.repository.PessoaJuridicaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Profile("test")
@SpringBootTest(classes = ECommerce_FullProject.class)
public class TestePessoaUsuario {
    @Autowired
    private PessoaController pessoaController;
    @Autowired
    private PessoaJuridicaRepository pessoaJuridicaRepository;

    @Autowired
    private FormaPagamentoController formaPagamentoController;

    @Test
    public void testFormaPagamento() throws CustomException{
        formaPagamentoController.ListaFormaPagamento();
        formaPagamentoController.listaFormaPagamentoPorIdEmpresa(64L);
    }

    @Test
    public void testCadPessoaJuridica() throws CustomException {

        PessoaJuridica pessoaJuridica = new PessoaJuridica();
        pessoaJuridica.setCnpj("72.852.476/0001-61");
        pessoaJuridica.setNome("Lisodododms");
        pessoaJuridica.setEmail("345345345453@gmail.com");
        pessoaJuridica.setTelefone("543534534");
        pessoaJuridica.setInscEstadual("234232423");
        pessoaJuridica.setInscMunicipal("234234");
        pessoaJuridica.setNomeFantasia("23423423");
        pessoaJuridica.setRazaoSocial("3423423435");

        Endereco endereco1 = new Endereco();
        endereco1.setBairro("Jd Dias");
        endereco1.setCep("556556565");
        endereco1.setComplemento("Casa cinza");
        endereco1.setEmpresa(pessoaJuridica);
        endereco1.setNumero("389");
        endereco1.setPessoa(pessoaJuridica);
        endereco1.setRuaLogra("Av. são joao sexto");
        endereco1.setTipoEndereco(TipoEndereco.COBRANCA);
        endereco1.setUf("PR");
        endereco1.setCidade("Curitiba");


        Endereco endereco2 = new Endereco();
        endereco2.setBairro("Jd Maracana");
        endereco2.setCep("7878778");
        endereco2.setComplemento("Andar 4");
        endereco2.setEmpresa(pessoaJuridica);
        endereco2.setNumero("555");
        endereco2.setPessoa(pessoaJuridica);
        endereco2.setRuaLogra("Av. maringá");
        endereco2.setTipoEndereco(TipoEndereco.ENTREGA);
        endereco2.setUf("PR");
        endereco2.setCidade("Curitiba");

        pessoaJuridica.getEnderecos().add(endereco2);
        pessoaJuridica.getEnderecos().add(endereco1);

        pessoaJuridica = pessoaController.salvarPj(pessoaJuridica).getBody();

        assertEquals(true, pessoaJuridica.getId() > 0 );

        for (Endereco endereco : pessoaJuridica.getEnderecos()) {
            assertEquals(true, endereco.getId() > 0);
        }

        assertEquals(2, pessoaJuridica.getEnderecos().size());

        pessoaController.salvarPj(pessoaJuridica);

    }

    @Test
    public void testCadPessoaFisica() throws CustomException {
        PessoaJuridica pessoaJuridica = pessoaJuridicaRepository.existeCnpjCadastrado("72.852.476/0001-61");


		PessoaFisica pessoaFisica = new PessoaFisica();

		pessoaFisica.setCpf("900.328.720-11");
		pessoaFisica.setNome("Mateus ulrich");
		pessoaFisica.setEmail("ma23424s_t2342i@gmail.com");
		pessoaFisica.setTelefone("479993795800");
		pessoaFisica.setEmpresa(pessoaJuridica);

        Endereco endereco1 = new Endereco();
        endereco1.setBairro("Jd Dias");
        endereco1.setCep("556556565");
        endereco1.setComplemento("Casa cinza");
        endereco1.setNumero("389");
        endereco1.setPessoa(pessoaFisica);
        endereco1.setRuaLogra("Av. são joao sexto");
        endereco1.setTipoEndereco(TipoEndereco.COBRANCA);
        endereco1.setUf("PR");
        endereco1.setCidade("Curitiba");
        endereco1.setEmpresa(pessoaJuridica);


        Endereco endereco2 = new Endereco();
        endereco2.setBairro("Jd Maracana");
        endereco2.setCep("7878778");
        endereco2.setComplemento("Andar 4");
        endereco2.setNumero("555");
        endereco2.setPessoa(pessoaFisica);
        endereco2.setRuaLogra("Av. maringá");
        endereco2.setTipoEndereco(TipoEndereco.ENTREGA);
        endereco2.setUf("PR");
        endereco2.setCidade("Curitiba");
        endereco2.setEmpresa(pessoaJuridica);

        pessoaFisica.getEnderecos().add(endereco2);
        pessoaFisica.getEnderecos().add(endereco1);

        pessoaFisica = pessoaController.salvarPf(pessoaFisica).getBody();

        assertEquals(true, pessoaFisica.getId() > 0 );

        for (Endereco endereco : pessoaFisica.getEnderecos()) {
            assertEquals(true, endereco.getId() > 0);
        }

        assertEquals(2, pessoaFisica.getEnderecos().size());


    }

}
