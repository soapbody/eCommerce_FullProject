package com.Mateus_Ulrich.eCommerce_FullProject.service;

import com.Mateus_Ulrich.eCommerce_FullProject.model.PessoaFisica;
import com.Mateus_Ulrich.eCommerce_FullProject.model.PessoaJuridica;
import com.Mateus_Ulrich.eCommerce_FullProject.model.Usuario;
import com.Mateus_Ulrich.eCommerce_FullProject.repository.PessoaFisicaRepository;
import com.Mateus_Ulrich.eCommerce_FullProject.repository.PessoaJuridicaRepository;
import com.Mateus_Ulrich.eCommerce_FullProject.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service
public class PessoaUserService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PessoaJuridicaRepository pessoaJuridicaRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private ServiceSendEmail serviceSendEmail;
    @Autowired
    private PessoaFisicaRepository pessoaFisicaRepository;

    public PessoaJuridica salvarPessoaJuridica(PessoaJuridica juridica) {

        //juridica = pesssoaRepository.save(juridica);

        for (int i = 0; i< juridica.getEnderecos().size(); i++) {
            juridica.getEnderecos().get(i).setPessoa(juridica);
            juridica.getEnderecos().get(i).setEmpresa(juridica);
        }

        juridica = pessoaJuridicaRepository.save(juridica);

        Usuario usuarioPj = usuarioRepository.findUserByPessoa(juridica.getId(), juridica.getEmail());

        if (usuarioPj == null) {

            String constraint = usuarioRepository.consultaConstraintAcesso();
            if (constraint != null) {
                jdbcTemplate.execute("begin; alter table usuarios_acesso drop constraint " + constraint +"; commit;");
            }

            usuarioPj = new Usuario();
            usuarioPj.setDataAtualSenha(Calendar.getInstance().getTime());
            usuarioPj.setEmpresa(juridica);
            usuarioPj.setPessoa(juridica);
            usuarioPj.setLogin(juridica.getEmail());

            String senha = "" + Calendar.getInstance().getTimeInMillis();
            String senhaCript = new BCryptPasswordEncoder().encode(senha);

            usuarioPj.setSenha(senhaCript);

            usuarioPj = usuarioRepository.save(usuarioPj);
            usuarioRepository.insereAcessoUser(usuarioPj.getId(), "ROLE_USER");


            StringBuilder menssagemHtml = new StringBuilder();
            menssagemHtml.append("<b> #teste para jamine Segue abaixo seus dados de acesso para loja virtual </b>");
            menssagemHtml.append("<b>Login: </b>" + juridica.getEmail() + "<br/>");
            menssagemHtml.append("<b>Senha: </b>").append(senha).append("<br/>");
            menssagemHtml.append("<b>Obrigado!</b>");
            menssagemHtml.append("<b> Segue abaixo seus dados de acesso para loja virtual </b>");

            try {
                serviceSendEmail.enviarEmailHtml("Acesso gerado para Loja Virtual", menssagemHtml.toString(), juridica.getEmail());
            }catch (Exception e) {
                e.printStackTrace();
            }

        }

        return juridica;
    }

    public PessoaFisica salvarPessoaFisica(PessoaFisica pessoaFisica) {

        //juridica = pesssoaRepository.save(juridica);

        for (int i = 0; i< pessoaFisica.getEnderecos().size(); i++) {
            pessoaFisica.getEnderecos().get(i).setPessoa(pessoaFisica);
            //pessoaFisica.getEnderecos().get(i).setEmpresa(pessoaFisica);
        }

        pessoaFisica = pessoaFisicaRepository.save(pessoaFisica);

        Usuario usuarioPf = usuarioRepository.findUserByPessoa(pessoaFisica.getId(), pessoaFisica.getEmail());

        if (usuarioPf == null) {

            String constraint = usuarioRepository.consultaConstraintAcesso();
            if (constraint != null) {
                jdbcTemplate.execute("begin; alter table usuarios_acesso drop constraint " + constraint +"; commit;");
            }

            usuarioPf = new Usuario();
            usuarioPf.setDataAtualSenha(Calendar.getInstance().getTime());
            usuarioPf.setEmpresa(pessoaFisica.getEmpresa());
            usuarioPf.setPessoa(pessoaFisica);
            usuarioPf.setLogin(pessoaFisica.getEmail());

            String senha = "" + Calendar.getInstance().getTimeInMillis();
            String senhaCript = new BCryptPasswordEncoder().encode(senha);

            usuarioPf.setSenha(senhaCript);

            usuarioPf = usuarioRepository.save(usuarioPf);
            usuarioRepository.insereAcessoUser(usuarioPf.getId(), "ROLE_USER");

            StringBuilder menssagemHtml = new StringBuilder();
            menssagemHtml.append("<b> #teste para jamine Segue abaixo seus dados de acesso para loja virtual </b>");
            menssagemHtml.append("<b>Login: </b>" + pessoaFisica.getEmail() + "<br/>");
            menssagemHtml.append("<b>Senha: </b>").append(senha).append("<br/>");
            menssagemHtml.append("<b>Obrigado!</b>");
            menssagemHtml.append("<b> Segue abaixo seus dados de acesso para loja virtual </b>");

            try {
                serviceSendEmail.enviarEmailHtml("Acesso gerado para Loja Virtual", menssagemHtml.toString(), pessoaFisica.getEmail());
            }catch (Exception e) {
                e.printStackTrace();
            }

        }

        return pessoaFisica;
    }
}