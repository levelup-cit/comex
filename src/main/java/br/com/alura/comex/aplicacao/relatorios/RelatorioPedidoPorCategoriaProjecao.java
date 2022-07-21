package br.com.alura.comex.aplicacao.relatorios;

import java.math.BigDecimal;

public interface RelatorioPedidoPorCategoriaProjecao {

  String getNomeCategoria();

  int getQuantidadeProdutosVendidos();

  BigDecimal getMontanteVendido();
}
