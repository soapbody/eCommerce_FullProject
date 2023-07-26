package com.Mateus_Ulrich.eCommerce_FullProject.service;

import com.Mateus_Ulrich.eCommerce_FullProject.model.dto.RelatorioProdutoCompradoNotaFiscalDTO;
import com.Mateus_Ulrich.eCommerce_FullProject.repository.NotaFiscalCompraRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NotaFiscalCompraService {
    private NotaFiscalCompraRepository notaFiscalCompraRepository;
    private JdbcTemplate jdbcTemplate;

    public NotaFiscalCompraService(NotaFiscalCompraRepository notaFiscalCompraRepository, JdbcTemplate jdbcTemplate) {
        this.notaFiscalCompraRepository = notaFiscalCompraRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<RelatorioProdutoCompradoNotaFiscalDTO> gerarRelatorioProdutoCompraNota(RelatorioProdutoCompradoNotaFiscalDTO dto) {

        String sql = "SELECT p.id AS codigoProduto, p.nome AS nomeProduto, " +
                "p.valor_venda AS valorVendaProduto, ntp.quantidade AS quantidadeComprada, " +
                "pj.id AS codigoFornecedor, pj.nome AS nomeFornecedor, cfc.data_compra AS dataCompra  " +
                "FROM nota_fiscal_compra AS cfc " +
                "INNER JOIN nota_item_produto AS ntp ON cfc.id = nota_fiscal_compra_id " +
                "INNER JOIN produto AS p ON p.id = ntp.produto_id " +
                "INNER JOIN pessoa_juridica AS pj ON pj.id = cfc.pessoa_id " +
                "WHERE " +
                "cfc.data_compra BETWEEN ? AND ?"; // Usamos '?' para indicar parâmetros

        List<Object> parametros = new ArrayList<>();
        parametros.add(dto.getDataInicial());
        parametros.add(dto.getDataFinal());

        if (dto.getCodigoNota() != null) {
            sql += " AND cfc.id = ?";
            parametros.add(dto.getCodigoNota());
        }
        if (dto.getCodigoProduto() != null) {
            sql += " AND p.id = ?";
            parametros.add(dto.getCodigoProduto());
        }
        if (!dto.getNomeProduto().isEmpty()) {
            sql += " AND p.nome like upper ?";
            parametros.add(dto.getNomeProduto());
        }
        if (!dto.getNomeFornecedor().isEmpty()) {
            sql += " AND pj.nome like upper ?";
            parametros.add(dto.getNomeFornecedor());
        }

        return jdbcTemplate.query(sql, parametros.toArray(), new BeanPropertyRowMapper<>(RelatorioProdutoCompradoNotaFiscalDTO.class));
    }
}
