package br.com.alura.comex;


import java.util.*;


public class Main {

    public static void main(String[] args) {

        ProcessadorDeCsv arquivo = new ProcessadorDeCsv();
        List<Pedido> pedidos = arquivo.getPedidos("pedidos.csv");

        GerarRelatorioPedidos relatorio = new GerarRelatorioPedidos(pedidos);
        relatorio.imprimeValoresTotais();

        RelatorioClienteFieis relatorioClienteFieis = new RelatorioClienteFieis(pedidos);
        relatorioClienteFieis.printClientesFieis();

        RelatorioCategoria relatorioCategoria = new RelatorioCategoria(pedidos);
        relatorioCategoria.imprimeVendasPorCategoria();

        relatorio.imprimeProdutosMaisVendidos();

        relatorioCategoria.imprimeProdutoMaisCaroPorCategoria();
    }
}
