package br.com.alura.comex.relatorios;

import java.util.List;
import br.com.alura.comex.model.Pedido;
import br.com.alura.comex.utils.Formatador;

public class RelatorioSintetico extends Relatorio {

	public RelatorioSintetico(List<Pedido> listaDePedidos) {
		super(listaDePedidos);
	}

	@Override
	public void filtrarRelatorio() {

	}

	@Override
	public void imprimirRelatorio() {
		
		  System.out.println("#### RELAT?RIO DE VALORES TOTAIS");
	        System.out.printf("- TOTAL DE PEDIDOS REALIZADOS: %s\n", getTotalDePedidosRealizados());
	        System.out.printf("- TOTAL DE PRODUTOS VENDIDOS: %s\n", getTotalDeProdutosVendidos());
	        System.out.printf("- TOTAL DE CATEGORIAS: %s\n", getTotalDeCategorias());
	        System.out.printf("- MONTANTE DE VENDAS: %s\n", Formatador.formatarValorTotal(getMontanteDeVendas()));
	        System.out.printf("- PEDIDO MAIS BARATO: %s (%s)\n", Formatador.formatarValorTotal(getPedidoMaisBarato().getValorTotal()), getPedidoMaisBarato().getProduto());
	        System.out.printf("- PEDIDO MAIS CARO: %s (%s)\n", Formatador.formatarValorTotal(getPedidoMaisCaro().getValorTotal()), getPedidoMaisCaro().getProduto());
	    			
	}

}
