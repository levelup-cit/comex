package br.com.alura.comex.application.model.projecao;

import java.math.BigDecimal;

public interface PedidosPorCategoriaProjecao {
    String getNome();
    Long getQuantidadeProdutos();
    BigDecimal getMontanteVendido();
}
