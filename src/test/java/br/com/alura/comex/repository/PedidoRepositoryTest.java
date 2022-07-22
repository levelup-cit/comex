package br.com.alura.comex.repository;

import br.com.alura.comex.builder.*;
import br.com.alura.comex.dominio.model.entities.*;
import br.com.alura.comex.infra.repository.*;
import br.com.alura.comex.dominio.model.enums.Status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class PedidoRepositoryTest {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ItemDePedidoRepository itemDePedidoRepository;

    @Test
    public void deveRetornarDoisRegistros() {

        Categoria categoria1 =
                new CategoriaBuilder()
                        .comNome("VESTUARIO")
                        .comStatus(Status.ATIVA)
                        .build();

        Categoria categoria2 =
                new CategoriaBuilder()
                        .comNome("ELETRONICOS")
                        .comStatus(Status.ATIVA)
                        .build();

        Produto produto1 = new ProdutoBuilder()
                .comCategoria(categoria1)
                .comNome("Moletom branco")
                .comDescricao(" ")
                .comPrecoUnitario(new BigDecimal("350.00"))
                .build();

        Produto produto2 = new ProdutoBuilder()
                .comCategoria(categoria2)
                .comNome("Monitor")
                .comDescricao(" ")
                .comPrecoUnitario(new BigDecimal("1000.00"))
                .build();

        Cliente cliente = new ClienteBuilder()
                .comNome("Cliente Um")
                .comCpf("1267867812")
                .comTelefone("1989712380")
                .comRua("Rua Um")
                .comBairro("Centro")
                .comCidade("São Paulo")
                .comComplemento("Casa")
                .comEstado("São Paulo")
                .comNumero("100")
                .build();

        ItemDePedido item1 = new ItemDePedidoBuilder()
                .comProduto(produto1)
                .comPrecoUnitario(produto1.getPrecoUnitario())
                .comQuantidade(1)
                .aplicarDesconto()
                .build();

        ItemDePedido item2 = new ItemDePedidoBuilder()
                .comProduto(produto2)
                .comPrecoUnitario(produto2.getPrecoUnitario())
                .comQuantidade(1)
                .aplicarDesconto()
                .build();

        List<ItemDePedido> listaDeItensProduto1 = List.of(item1, item1);
        List<ItemDePedido> listaDeItensProduto2 = List.of(item2);

        Pedido pedido1 = new PedidoBuilder()
                .comCliente(cliente)
                .comDataAtual()
                .comListaDePedidos(listaDeItensProduto1)
                .aplicarDesconto()
                .build();

        Pedido pedido2 = new PedidoBuilder()
                .comCliente(cliente)
                .comDataAtual()
                .comListaDePedidos(listaDeItensProduto2)
                .aplicarDesconto()
                .build();

        item1.setPedido(pedido1);
        item2.setPedido(pedido2);

        categoriaRepository.save(categoria1);
        categoriaRepository.save(categoria2);
        clienteRepository.save(cliente);
        pedidoRepository.save(pedido1);
        pedidoRepository.save(pedido2);
        produtoRepository.save(produto1);
        produtoRepository.save(produto2);
        itemDePedidoRepository.save(item1);
        itemDePedidoRepository.save(item2);

        List<Pedido> resultado = pedidoRepository.findAll();

        assertThat(resultado)
                .hasSize(2)
                .extracting(Pedido::getCategoria,
                        Pedido::getQuantidade,
                        Pedido::getPreco)
                .containsExactly(
                        tuple("VESTUARIO", 1, new BigDecimal("350.00")),
                        tuple("ELETRONICOS", 1, new BigDecimal("1000.00"))
                );
    }
}
