//package br.com.alura.comex.controller;
//
//
//import br.com.alura.comex.compartilhado.adapter.controller.pedido.form.ItemDePedidoForm;
//import br.com.alura.comex.compartilhado.adapter.controller.pedido.form.PedidoFrom;
//import br.com.alura.comex.compartilhado.entity.enuns.StatusCategoria;
//import br.com.alura.comex.compartilhado.entity.enuns.TipoDesconto;
//import br.com.alura.comex.compartilhado.infra.ItemDePedido.ItemDePedidoEntity;
//import br.com.alura.comex.compartilhado.infra.categoria.CategoriaEntity;
//import br.com.alura.comex.compartilhado.infra.cliente.ClienteEntity;
//import br.com.alura.comex.compartilhado.infra.cliente.EnderecoEntity;
//import br.com.alura.comex.compartilhado.infra.pedido.PedidoEntity;
//import br.com.alura.comex.compartilhado.infra.pedido.PedidoRepositoryImpl;
//import br.com.alura.comex.compartilhado.infra.produto.DimensaoEntity;
//import br.com.alura.comex.estoque.infra.produto.ProdutoEstoqueEntity;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//import javax.transaction.Transactional;
//import java.math.BigDecimal;
//import java.net.URI;
//import java.time.LocalDate;
//import java.util.ArrayList;
//
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//@Transactional
//@AutoConfigureTestEntityManager
//@ActiveProfiles("test")
//class PedidoEntityControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private TestEntityManager em;
//
//
//    @Autowired
//    private PedidoRepositoryImpl pedidoRepository;
//
//    private PedidoEntity DEFAULT_PEDIDO;
//
//    private EnderecoEntity DEFAULT_ENDERECO = new EnderecoEntity("Rua da esquina", "366", "H22", "Santa Genebra", "Campinas", "SP");
//
//    @Mock
//    private ItemDePedidoForm itemDePedidoForm;
//
//    @Mock
//    private PedidoFrom pedido;
//
//    @BeforeEach
//    public void setup() {
//
//        ClienteEntity kelvin = ClienteEntity.builder()
//                .id(1L)
//                .nome("Kelvin")
//                .cpf("4156667228")
//                .endereco(DEFAULT_ENDERECO)
//                .ddd("19")
//                .numeroTelefone("9827736453")
//                .pedidos(new ArrayList<>())
//                .build();
//
//        DEFAULT_PEDIDO = PedidoEntity.builder()
//                                .id(1L)
//                                .data(LocalDate.now())
//                                .desconto(BigDecimal.ZERO)
//                                .clienteEntity(kelvin)
//                                .itens(new ArrayList<>())
//                                .tipoDesconto(TipoDesconto.NENHUM)
//                                .build();
//
//        pedidoRepository.cadastrarPedido(DEFAULT_PEDIDO.paraPedido());
//    }
//
//    @Test
//    public void deveriaListarPedidos() throws Exception {
//
//        URI uri = new URI("/api/pedidos");
//
//        persistirRegistrosTeste();
//
//        mockMvc.perform(MockMvcRequestBuilders
//                        .get(uri))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.length()").isNotEmpty())
//                .andExpect(jsonPath("$.content[0].data").value(LocalDate.now().toString()));
//    }
//
//
//    @Test
//    public void deveriaListarDetalheDeUmPedido() throws Exception {
//        URI uri = new URI("/api/pedidos/" + DEFAULT_PEDIDO.getId());
//
//        mockMvc.perform(MockMvcRequestBuilders
//                        .get(uri))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.idCliente").value(DEFAULT_PEDIDO.getClienteEntity().getId()))
//                .andExpect(jsonPath("$.nomeCliente").value(DEFAULT_PEDIDO.getClienteEntity().getNome()))
//                .andExpect(jsonPath("$.itens").isEmpty())
//                .andExpect(jsonPath("$.data").value(LocalDate.now().toString()));
//
//
//    }
//
//    @Test
//    public void deveriaRetornarNotFoundParaIdInvalido() throws Exception {
//
//        URI uri = new URI("/api/pedidos/99");
//
//        mockMvc.perform(MockMvcRequestBuilders
//                        .get(uri))
//                .andExpect(status().isNotFound());
//    }
//
//
//    private void persistirRegistrosTeste() {
//
//        CategoriaEntity filmes = CategoriaEntity.builder()
//                .nome("FILMES")
//                .status(StatusCategoria.ATIVA)
//                .produtoEntities(new ArrayList<>())
//                .build();
//
//        CategoriaEntity informatica = CategoriaEntity.builder()
//                .nome("INFORMÁTICA")
//                .status(StatusCategoria.ATIVA)
//                .produtoEntities(new ArrayList<>())
//                .build();
//
//        ProdutoEstoqueEntity mouse = ProdutoEstoqueEntity.builder()
//                .nome("Mouse")
//                .descricao("Mouse")
//                .precoUnitario(new BigDecimal("30.50"))
//                .quantidadeEstoque(15)
//                .categoria(informatica)
//                .dimensao(DimensaoEntity.builder()
//                        .comprimento(123)
//                        .altura(500)
//                        .largura(5555)
//                        .peso(new BigDecimal(4))
//                        .build())
//                .build();
//
//        ProdutoEstoqueEntity moana = ProdutoEstoqueEntity.builder()
//                .nome("Moana")
//                .descricao("BlueRay")
//                .precoUnitario(new BigDecimal(25))
//                .quantidadeEstoque(8)
//                .categoria(filmes)
//                .dimensao(DimensaoEntity.builder()
//                        .comprimento(123)
//                        .altura(500)
//                        .largura(5555)
//                        .peso(new BigDecimal(4))
//                        .build())
//                .build();
//
//
//
//        ClienteEntity kelvin = ClienteEntity.builder()
//                .nome("Kelvin")
//                .cpf("4156667228")
//                .endereco(DEFAULT_ENDERECO)
//                .ddd("19")
//                .numeroTelefone("198273666444")
//                .pedidos(new ArrayList<>())
//                .build();
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
//    }
//
//}