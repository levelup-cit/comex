package br.com.alura.comex.service.relatorios;

import br.com.alura.comex.model.entities.Pedido;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

public class RelatorioVendasPorCategoria extends Relatorio {

    private Map<String, List<Pedido>> vendasPorCategoria;

    public RelatorioVendasPorCategoria(List<Pedido> listaDePedidos) {
        super(listaDePedidos);
    }

    @Override
    public void filtrarRelatorio() {
        vendasPorCategoria = getListaDePedidos().stream()
                .collect(Collectors.groupingBy(Pedido::getCategoria));
    }

    @Override
    public void imprimirRelatorio() {
        System.out.println("\n#### RELATÓRIO DE VENDAS POR CATEGORIA");
        vendasPorCategoria.entrySet().stream().sorted(Map.Entry.comparingByKey())
                .forEach(entry -> System.out.println("CATEGORIA: " + entry.getKey() + "\nQUANTIDADE VENDIDA: "
                        + entry.getValue().stream().mapToInt(Pedido::getQuantidade).sum()
                        + "\nMONTANTE: "
                        + NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(entry.getValue().stream()
                        .map(Pedido::getValorTotal).reduce(BigDecimal.ZERO, BigDecimal::add))
                        + "\n"));
    }
}