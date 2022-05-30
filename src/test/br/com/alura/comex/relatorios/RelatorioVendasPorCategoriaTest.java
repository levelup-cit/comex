package br.com.alura.comex.relatorios;

import br.com.alura.comex.model.Pedido;
import br.com.alura.comex.model.builder.PedidoBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

class RelatorioVendasPorCategoriaTest {

    @Test
    public void deveGerarRelatorioComVariosPedidos() throws Exception {
        Pedido primeiroPedido = new PedidoBuilder()
                .comCategoria("LIVROS")
                .comProduto("O guia do mochileiro das galáxias")
                .comCliente("Ale")
                .comValor("44.90")
                .comQuantidade(1)
                .comData(LocalDate.of(2022,5,30))
                .build();

        Pedido segundoPedido = new PedidoBuilder()
                .comCategoria("JOGOS")
                .comProduto("The Witcher 3")
                .comCliente("Isabela")
                .comValor("235.99")
                .comQuantidade(1)
                .comData(LocalDate.of(2020, 3, 11))
                .build();

        Pedido terceiroPedido = new PedidoBuilder()
                .comCategoria("LIVROS")
                .comProduto("Anne de Green Gables")
                .comCliente("Teresa")
                .comValor("51.23")
                .comQuantidade(1)
                .comData(LocalDate.of(2022,10,23))
                .build();

        List<Pedido> listaDePedidos = new ArrayList<>();
        listaDePedidos.add(primeiroPedido);
        listaDePedidos.add(segundoPedido);
        listaDePedidos.add(terceiroPedido);

        Consumer consumer = Mockito.mock(Consumer.class);

        RelatorioVendasPorCategoria relatorio = new RelatorioVendasPorCategoria(listaDePedidos, consumer);
        relatorio.executa();

        List<RelatorioVendasPorCategoria.VendasPorCategoria> resultado = relatorio.getVendasPorCategoria();
        Assertions.assertEquals(2, resultado.size());

        RelatorioVendasPorCategoria.VendasPorCategoria vendasPorCategoria1 = resultado.get(0);
        Assertions.assertEquals("JOGOS", vendasPorCategoria1.getCategoria());
        Assertions.assertEquals(new BigDecimal("235.99"), vendasPorCategoria1.getMontante());
        Assertions.assertEquals(1, vendasPorCategoria1.getQuantidadeVendida());

        RelatorioVendasPorCategoria.VendasPorCategoria vendasPorCategoria2 = resultado.get(1);
        Assertions.assertEquals("LIVROS", vendasPorCategoria2.getCategoria());
        Assertions.assertEquals(new BigDecimal("96.13"), vendasPorCategoria2.getMontante());
        Assertions.assertEquals(2, vendasPorCategoria2.getQuantidadeVendida());
    }
    @Test
    public void deveGerarRelatorioComUmPedido() throws Exception {

        Pedido pedido = new PedidoBuilder()
                .comCategoria("LIVROS")
                .comProduto("O guia do mochileiro das galáxias")
                .comCliente("Alexandre")
                .comValor("44.90")
                .comQuantidade(1)
                .comData(LocalDate.of(2022,5,30))
                .build();

        List<Pedido> pedidos = List.of(pedido);
        Consumer consumer = Mockito.mock(Consumer.class);

        RelatorioVendasPorCategoria relatorio = new RelatorioVendasPorCategoria(pedidos, consumer);
        relatorio.executa();

        List<RelatorioVendasPorCategoria.VendasPorCategoria> resultado = relatorio.getVendasPorCategoria();
        Assertions.assertEquals(1, resultado.size());
        RelatorioVendasPorCategoria.VendasPorCategoria primeiraVenda = resultado.get(0);

        Assertions.assertEquals("LIVROS", primeiraVenda.getCategoria());
        Assertions.assertEquals(1, primeiraVenda.getQuantidadeVendida());
    }

    @Test
    public void recebeListaVaziaDeveLancarIllegalArgumentException() throws Exception {
        List<Pedido> pedidos = null;
        Consumer consumer = Mockito.mock(Consumer.class);
        RelatorioVendasPorCategoria relatorio = new RelatorioVendasPorCategoria(pedidos, consumer);
        Assertions.assertThrows(IllegalArgumentException.class, () -> relatorio.executa());
    }
}