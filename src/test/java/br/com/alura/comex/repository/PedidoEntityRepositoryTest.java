//package br.com.alura.comex.repository;
//
//import br.com.alura.comex.infra.pedido.PedidoDAO;
//import br.com.alura.comex.infra.ItemDePedido.ItemDePedidoEntity;
//import br.com.alura.comex.infra.categoria.CategoriaEntity;
//import br.com.alura.comex.infra.cliente.ClienteEntity;
//import br.com.alura.comex.infra.cliente.EnderecoEntity;
//import br.com.alura.comex.infra.pedido.PedidoEntity;
//import br.com.alura.comex.infra.produto.ProdutoEntity;
//import br.com.alura.comex.model.*;
//import br.com.alura.comex.entity.categoria.RelatorioPedidosPorCategoriaProjecao;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
//import org.springframework.test.context.ActiveProfiles;
//
//import java.math.BigDecimal;
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.assertj.core.api.Assertions.tuple;
//
//
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@ActiveProfiles("test")
//public class PedidoEntityRepositoryTest {
//    @Autowired
//    private PedidoDAO repository;
//
//    @Autowired
//    private TestEntityManager em;
//
//    @Test
//    public void deveriaRetornarUmRegistroParaCadaCategoriaComDescontoQuantidadeEmUmDosRegistros() {
//
//        persistirRegistrosTeste();
//
//        List<RelatorioPedidosPorCategoriaProjecao> resultado = repository.findPedidosPorCategoria();
//
//        assertThat(resultado)
//                .hasSize(2)
//                .extracting(RelatorioPedidosPorCategoriaProjecao::getNome,
//                        RelatorioPedidosPorCategoriaProjecao::getQuantidadeProdutos,
//                        RelatorioPedidosPorCategoriaProjecao::getMontanteVendido)
//                .containsExactly(tuple(("INFORMÁTICA"), (10L), new BigDecimal("305.00")), tuple("FILMES", 3L, new BigDecimal("75.00")) );
//    }
//
//    private void persistirRegistrosTeste() {
//
//        CategoriaEntity informatica = new CategoriaEntity("INFORMÁTICA");
//        CategoriaEntity filmes = new CategoriaEntity("FILMES");
//
//        ProdutoEntity mouse = new ProdutoEntity("Mouse", "Mouse", new BigDecimal("30.50"), 15, informatica);
//        ProdutoEntity moana = new ProdutoEntity("Moana", "BlueRay",  new BigDecimal(25), 8, filmes);
//
//        EnderecoEntity enderecoEntity = new EnderecoEntity("Rua da esquina", "366", "H22", "Santa Genebra", "Campinas", "SP");
//        ClienteEntity kelvin = new ClienteEntity("Kelvin", 4156667228L, "198273666444", enderecoEntity);
//
//        PedidoEntity pedidoEntity1 = new PedidoEntity(kelvin);
//        PedidoEntity pedidoEntity2 = new PedidoEntity(kelvin);
//        PedidoEntity pedidoEntity3 = new PedidoEntity(kelvin);
//
//        kelvin.adicionarPedido(pedidoEntity1);
//        kelvin.adicionarPedido(pedidoEntity2);
//        kelvin.adicionarPedido(pedidoEntity3);
//
//        ItemDePedidoEntity item1 = new ItemDePedidoEntity(7, mouse);
//        ItemDePedidoEntity item3 = new ItemDePedidoEntity(3, mouse);
//        ItemDePedidoEntity item2 = new ItemDePedidoEntity(3, moana);
//
//        em.persist(informatica);
//        em.persist(filmes);
//
//        em.persist(mouse);
//        em.persist(moana);
//
//        em.persist(kelvin);
//
//
//        em.persist(pedidoEntity1);
//        em.persist(pedidoEntity2);
//        em.persist(pedidoEntity3);
//
//        pedidoEntity1.adicionarItem(item1);
//        pedidoEntity2.adicionarItem(item2);
//        pedidoEntity3.adicionarItem(item3);
//
//
//    }
//
//
//}