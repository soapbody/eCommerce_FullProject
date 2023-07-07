package com.Mateus_Ulrich.eCommerce_FullProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class ServiceContagemAcessoApi {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void atualizaAcessoEndPoint() {
        jdbcTemplate.execute("begin; update tabela_acesso_end_point set qtd_acesso_end_point = qtd_acesso_end_point + 1 where nome_end_point = 'END_POINT_PESSOA_FISICA'; commit;");
    }
}
