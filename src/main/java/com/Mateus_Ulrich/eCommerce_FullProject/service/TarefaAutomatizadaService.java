package com.Mateus_Ulrich.eCommerce_FullProject.service;
import com.Mateus_Ulrich.eCommerce_FullProject.model.Usuario;
import com.Mateus_Ulrich.eCommerce_FullProject.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.List;
@Service
@Component
public class TarefaAutomatizadaService {
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    private ServiceSendEmail serviceSendEmail;
    @Scheduled(initialDelay = 2000, fixedDelay = 86400000) //Roda a cada 24horas
    //@Scheduled(cron = "0 0 11 * * *", zone = "America/Sao_Paulo") //Vai rodar todo dia as 11 horas no horario de brasilia
    public void notificarUserTrocaSenha() throws MessagingException, UnsupportedEncodingException, InterruptedException {
        List<Usuario> usuarios = usuarioRepository.usuarioSenhaVencida();
        for (Usuario usuario : usuarios) {
            StringBuilder msg = new StringBuilder();
            msg.append("Olá, ").append(usuario.getPessoa().getNome()).append("<br/>>");
            msg.append("Está na hora de trocar sua senha, ja passou 90 dias de validade.").append("<br/>");
            msg.append("Troque sua senha a loja virtual do Mateus Ulrich");
            serviceSendEmail.enviarEmailHtml("Troca de senha", msg.toString(), usuario.getLogin());
            Thread.sleep(3000);
        }
    }
}
