package br.com.alura.comex.menu;

import br.com.alura.comex.model.Pedido;
import br.com.alura.comex.processador.*;
import br.com.alura.comex.relatorios.*;

import java.util.List;
import java.util.Scanner;

public class MenuRelatorio {

    Scanner input = new Scanner(System.in);

    private List<Pedido> escolherArquivo() throws Exception {
        imprimirOpcoesProcessador();
        System.out.print("Digite: ");
        String opcao = input.nextLine();
        CategoriaProcessador categoria = CategoriaProcessador.valueOf(opcao);
            switch (categoria) {
            case CSV:
                return ((Processador) new ProcessadorDeCSV()).lerRegistros();
            case JSON:
                return ((Processador) new ProcessadorDeJSON()).lerRegistros();
            case XML:
                return ((Processador) new ProcessadorDeXML()).lerRegistros();
        }
        return escolherArquivo();
    }

    private void escolherRelatorio(List<Pedido> listaDePedidos) {
        imprimirOpcoesRelatorio();
        System.out.print("Digite: ");
        String opcao = input.nextLine();
        CategoriaRelatorio categoria = CategoriaRelatorio.valueOf(opcao);

        switch (categoria) {
            case SINTETICO:
                new RelatorioProxy(new RelatorioSintetico(listaDePedidos)).executa();
                break;
            case CLIENTES_FIEIS:
                new RelatorioProxy(new RelatorioFidelidade(listaDePedidos)).executa();
                break;
            case VENDAS_POR_CATEGORIA:
                new RelatorioProxy(new RelatorioVendasPorCategoria(listaDePedidos)).executa();
                break;
            case PRODUTOS_MAIS_VENDIDOS:
                new RelatorioProxy(new RelatorioProdutosMaisVendidos(listaDePedidos)).executa();
                break;
            case PRODUTOS_MAIS_CAROS:
                new RelatorioProxy(new RelatorioProdutosMaisCaros(listaDePedidos)).executa();
                break;
            case CLIENTES_MAIS_LUCRATIVOS:
                new RelatorioProxy(new RelatorioClientesMaisLucrativos(listaDePedidos)).executa();
                break;
            case TODOS:
                new RelatorioProxy(new RelatorioSintetico(listaDePedidos)).executa();
                new RelatorioProxy(new RelatorioFidelidade(listaDePedidos)).executa();
                new RelatorioProxy(new RelatorioVendasPorCategoria(listaDePedidos)).executa();
                new RelatorioProxy(new RelatorioProdutosMaisVendidos(listaDePedidos)).executa();
                new RelatorioProxy(new RelatorioProdutosMaisCaros(listaDePedidos)).executa();
                new RelatorioProxy(new RelatorioClientesMaisLucrativos(listaDePedidos)).executa();
                break;
        }
    }

    private void imprimirOpcoesProcessador() {
        System.out.println("----BEM VINDO----");
        System.out.println("Digite uma opção do menu abaixo em letras maiúsculas");
        System.out.println("Que tipo de arquivo deseja processar?");
        System.out.println("1) CSV");
        System.out.println("2) JSON");
        System.out.println("3) XML");
    }

    private void imprimirOpcoesRelatorio() {
        System.out.println("\nQual relatório deseja imprimir?");
        System.out.println("1 - Relatório sintético");
        System.out.println("2 - Relatório de clientes fiéis");
        System.out.println("3 - Relatório de vendas por categoria");
        System.out.println("4 - Relatório de produtos mais vendidos");
        System.out.println("5 - Relatório de produtos mais caros em cada categoria");
        System.out.println("6 - Relatório de clientes mais lucrativos");
    }

    public void executar() throws Exception {
        List<Pedido> listaDePedidos = escolherArquivo();
        escolherRelatorio(listaDePedidos);
    }


}
