package br.com.alura.comex.repository;

import br.com.alura.comex.dominio.categoria.Categoria;
import br.com.alura.comex.dominio.cliente.Cliente;
import br.com.alura.comex.dominio.cliente.Endereco;
import br.com.alura.comex.dominio.itemDePedido.ItemDePedido;
import br.com.alura.comex.dominio.itemDePedido.TipoDeDescontoPorItemDePedido;
import br.com.alura.comex.dominio.pedido.Pedido;
import br.com.alura.comex.dominio.pedido.PedidoRepository;
import br.com.alura.comex.dominio.pedido.TipoDeDescontoPorPedido;
import br.com.alura.comex.dominio.produto.Produto;
import br.com.alura.comex.dominio.usuario.Usuario;
import br.com.alura.comex.aplicacao.relatorios.RelatorioPedidoPorCategoriaProjecao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class PedidoRepositoryTest {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Autowired
  private PedidoRepository pedidoRepository;

  @Autowired
  private TestEntityManager testEntityManager;

  @Test
  void deveGerarRelatorioDePedidosPorCategoria() {

    gerarDadosParaTeste();

    List<RelatorioPedidoPorCategoriaProjecao> relatorio =
            pedidoRepository.gerarRelatorioDePedidosPorCategoria();


    //Verificando se o relatório retorna 2 registros
    assertEquals(2, relatorio.size());

    //Verificando se o valor dos montantes dos dois registros estão corretos
    assertEquals(new BigDecimal("700.00"), relatorio.get(0).getMontanteVendido());
    assertEquals(new BigDecimal("30000.00"), relatorio.get(1).getMontanteVendido());

    //Verificando se a quantidade de itens por pedidos está correta
    assertEquals(2, relatorio.get(0).getQuantidadeProdutosVendidos());
    assertEquals(1, relatorio.get(1).getQuantidadeProdutosVendidos());

    //Verificando se o nome da categoria dos itens do pedido estão corretas
    assertEquals("LIVROS", relatorio.get(0).getNomeCategoria());
    assertEquals("CARROS", relatorio.get(1).getNomeCategoria());

  }

  private void gerarDadosParaTeste() {

    //Categorias
    Categoria livros =  new Categoria("LIVROS");
    Categoria carros =  new Categoria("CARROS");

    testEntityManager.persist(livros);
    testEntityManager.persist(carros);


    //Produtos
    Produto kindle = new Produto("Kindle",
            "Dona Amazon baixe para 0 reais .-.",
            new BigDecimal("450"),
            3L,
            livros);
    Produto hondaFit = new Produto("Honda Fit",
            "Um carro longe de ser o Sedan dos sonhos, mas um baita carrinho",
            new BigDecimal("30.000"),
            1L,
            carros);

    testEntityManager.persist(kindle);
    testEntityManager.persist(hondaFit);


    //Usuario
    Usuario usuario = new Usuario("joaninha@email.com",
            "123456", new ArrayList<>());

    testEntityManager.persist(usuario);

    //Cliente
    Cliente cliente = new Cliente("Ana",
            "11122233344",
            "1122224444",
            new Endereco("Rua Um",
                    "22",
                    "Centro",
                    "Catotas",
                    "São Paulo",
                    "São Paulo"),
            usuario);

    testEntityManager.persist(cliente);


    //Pedidos
    Pedido kindle1 = new Pedido(
            LocalDate.now(),
            cliente,
            new BigDecimal("0"),
            TipoDeDescontoPorPedido.NENHUM);
    Pedido kindle2 = new Pedido(
            LocalDate.now(),
            cliente,
            new BigDecimal("0"),
            TipoDeDescontoPorPedido.NENHUM);
    Pedido honda1 = new Pedido(
            LocalDate.now(),
            cliente,
            new BigDecimal("0"),
            TipoDeDescontoPorPedido.NENHUM);

    testEntityManager.persist(kindle1);
    testEntityManager.persist(kindle2);
    testEntityManager.persist(honda1);


    //Itens de Pedido
    ItemDePedido itemKindlePedido1 = new ItemDePedido( new BigDecimal("350"),
            1L,
            kindle,
            kindle1,
            new BigDecimal("0"),
            TipoDeDescontoPorItemDePedido.NENHUM);
    ItemDePedido itemKindlePedido2 = new ItemDePedido( new BigDecimal("350"),
            2L,
            kindle,
            kindle2,
            new BigDecimal("0"),
            TipoDeDescontoPorItemDePedido.NENHUM);
    ItemDePedido itemHondaPedido3 = new ItemDePedido( new BigDecimal("30000"),
            1L,
            hondaFit,
            honda1,
            new BigDecimal("0"),
            TipoDeDescontoPorItemDePedido.NENHUM);

    testEntityManager.persist(itemKindlePedido1);
    testEntityManager.persist(itemKindlePedido2);
    testEntityManager.persist(itemHondaPedido3);

  }

  //Dropando o bd de testes após cada teste
//  @AfterEach
//  public void execute() {
//    jdbcTemplate.execute("DROP DATABASE comexdb_test" );
//  }

}