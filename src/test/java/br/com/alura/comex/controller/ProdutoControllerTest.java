package br.com.alura.comex.controller;


import br.com.alura.comex.compartilhado.entity.enuns.StatusCategoria;
import br.com.alura.comex.compartilhado.infra.categoria.CategoriaEntity;
import br.com.alura.comex.compartilhado.infra.produto.DimensaoEntity;
import br.com.alura.comex.loja.infra.produto.ProdutoLojaEntity;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.net.URI;
import java.util.ArrayList;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestEntityManager
@Transactional
@ActiveProfiles("test")
public class ProdutoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TestEntityManager entityManager;

    private CategoriaEntity INFORMATICA;

    private ProdutoLojaEntity DEFAULT_MOUSE;

    @BeforeEach
    void setup() {
        INFORMATICA = CategoriaEntity.builder()
                .nome("INFORMATICA")
                .status(StatusCategoria.ATIVA)
                .produtoEntities(new ArrayList<>())
                .build();

        DEFAULT_MOUSE = ProdutoLojaEntity.builder()
                .nome("mouse")
                .descricao("Dell")
                .precoUnitario(new BigDecimal("25.4"))
                .quantidadeEstoque(3)
                .categoria(INFORMATICA)
                .dimensao(DimensaoEntity.builder()
                        .comprimento(123)
                        .altura(500)
                        .largura(5555)
                        .peso(new BigDecimal(4))
                        .build())
                .build();

        entityManager.persist(INFORMATICA);

        entityManager.persist(DEFAULT_MOUSE);
    }


    @Test
    public void deveriaListar2ProdutosOrdenadosPorNome() throws Exception {
        URI uri = new URI("/api/loja/produtos");

        ProdutoLojaEntity teclado = ProdutoLojaEntity.builder()
                .nome("teclado")
                .descricao("Dell")
                .precoUnitario(new BigDecimal("45.90"))
                .quantidadeEstoque(10)
                .categoria(INFORMATICA)
                .dimensao(DimensaoEntity.builder()
                        .comprimento(123)
                        .altura(500)
                        .largura(5555)
                        .peso(new BigDecimal(4))
                        .build())
                .build();

        entityManager.persist(teclado);

        mockMvc
                .perform(MockMvcRequestBuilders
                        .get(uri)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(2)))
                .andExpect(jsonPath("$.content[0].nome", is("mouse")))
                .andExpect(jsonPath("$.content[1].nome", is("teclado")));

    }

    @Test
    void deveriaRetornarInformacoesMouse() throws Exception {
        URI uri = new URI("/api/loja/produtos/" + DEFAULT_MOUSE.getCodigoProduto());

        mockMvc.perform(MockMvcRequestBuilders
                        .get(uri))
                .andExpect(status().isOk())
                .andDo(log())
                .andExpect(jsonPath("$.id", equalTo(DEFAULT_MOUSE.getCodigoProduto().intValue())))
                .andExpect(jsonPath("$.nome", equalTo(DEFAULT_MOUSE.getNome())))
                .andExpect(jsonPath("$.descricao", equalTo(DEFAULT_MOUSE.getDescricao())))
                .andExpect(jsonPath("$.quantidadeEstoque", equalTo(DEFAULT_MOUSE.getQuantidadeEstoque())))
                .andExpect(jsonPath("$.categoria", equalTo(DEFAULT_MOUSE.getCategoria().getNome())));
    }


    @Test
    public void deveriaCadastrarProduto() throws Exception {
        URI uri = new URI("/api/loja/produtos");

        JSONObject json = criarObjetoJson();
        String request = json.toString();

        mockMvc
                .perform(MockMvcRequestBuilders
                        .post(uri)
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status()
                        .is(201));

    }

    @Test
    public void deveriaAlterarQuantidadeEmEstoqueDoProduto() throws Exception {

        URI uri = new URI("/api/loja/produtos/" + DEFAULT_MOUSE.getCodigoProduto());

        String request = criarObjetoProdutoJsonAlterado(DEFAULT_MOUSE).toString();

        mockMvc
                .perform(MockMvcRequestBuilders
                        .put(uri)
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status()
                        .is(200));

    }

    @Test
    public void deveriaRetornar404ParaProdutoNaoEncontradoParaAlteração() throws Exception {

        URI uri = new URI("/api/loja/produtos/1010");

        String request = criarObjetoProdutoJsonAlterado(DEFAULT_MOUSE).toString();

        mockMvc
                .perform(MockMvcRequestBuilders
                        .put(uri)
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status()
                        .is(404));

    }

    @Test
    public void deveriaRetornar400ParaAlteracaoComBodyVazio() throws Exception {

        URI uri = new URI("/api/loja/produtos/" + DEFAULT_MOUSE.getCodigoProduto());

        mockMvc
                .perform(MockMvcRequestBuilders
                        .put(uri)
                        .content("")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status()
                        .is(400));

    }

    @Test
    public void deveriaDeletarProduto() throws Exception {

        URI uri = new URI("/api/loja/produtos/" + DEFAULT_MOUSE.getCodigoProduto());

        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete(uri)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    private JSONObject criarObjetoProdutoJsonAlterado(ProdutoLojaEntity produtoEntity) throws JSONException {

        return new JSONObject()
                .put("nome", produtoEntity.getNome())
                .put("descricao", produtoEntity.getDescricao())
                .put("precoUnitario", produtoEntity.getPrecoUnitario())
                .put("quantidadeEstoque", 10)
                .put("categoria", INFORMATICA.getId())
                .put("comprimento", 200)
                .put("altura", 1000)
                .put("largura", 400)
                .put("peso", 15.4);
    }

    private JSONObject criarObjetoJson() throws JSONException {

        return new JSONObject()
                .put("nome", "Tela")
                .put("descricao", "4K com AI")
                .put("precoUnitario", 5000.00)
                .put("quantidadeEstoque", 2)
                .put("categoria", INFORMATICA.getId())
                .put("comprimento", 200)
                .put("altura", 1000)
                .put("largura", 400)
                .put("peso", 15.4);
    }


}