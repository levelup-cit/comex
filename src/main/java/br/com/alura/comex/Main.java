package br.com.alura.comex;

import java.io.IOException;
import java.net.URISyntaxException;

public class Main {

	public static void main(String[] args) throws IOException, URISyntaxException {

		//Pedido pedido = new Pedido();
		
		RelatorioSintetico relatorio = new RelatorioSintetico();
	
		
		/*
		ArrayList<Pedido> pedidos = new ProcessadorDeCsv().registrarPedidos();

		int totalDeProdutosVendidos = 0;
		int totalDePedidosRealizados = 0;

		CategoriasProcessadas categoriasProcessadas = new CategoriasProcessadas();
		int totalDeCategorias = 0;

		for (int i = 0; i < pedidos.size(); i++) {
			Pedido pedidoAtual = pedidos.get(i);

			if (pedidoAtual == null) break;

			pedido.isMaisBaratoQue(pedidoAtual);
			pedido.isMaisCaroQue(pedidoAtual);

			pedido.getValorTotal(pedidoAtual);

			totalDeProdutosVendidos += pedidoAtual.getQuantidade();
			totalDePedidosRealizados++;

			if (!categoriasProcessadas.contains(pedidoAtual.getCategoria())) {
				totalDeCategorias++;
				categoriasProcessadas.add(pedidoAtual.getCategoria());
			}
		}*/
		
		/*
		System.out.println("#### RELATÓRIO DE VALORES TOTAIS");
		System.out.printf("- TOTAL DE PEDIDOS REALIZADOS: %s\n", totalDePedidosRealizados);
		System.out.printf("- TOTAL DE PRODUTOS VENDIDOS: %s\n", totalDeProdutosVendidos);
		System.out.printf("- TOTAL DE CATEGORIAS: %s\n", totalDeCategorias);
		System.out.printf("- MONTANTE DE VENDAS: %s\n", pedido.valorTotalFormatado());
		System.out.printf("- PEDIDO MAIS BARATO: %s (%s)\n", pedido.pedidoMaisBaratoFormatado(),
				pedido.getPedidoMaisBarato().getProduto());
		System.out.printf("- PEDIDO MAIS CARO: %s (%s)\n", pedido.pedidoMaisCaroQueFormatado(),
				pedido.getPedidoMaisCaro().getProduto());
		*/
	}
}
