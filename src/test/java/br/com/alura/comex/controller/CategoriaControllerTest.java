package br.com.alura.comex.controller;

import br.com.alura.comex.model.Categoria;
import br.com.alura.comex.model.Produto;
import br.com.alura.comex.repository.CategoriaRepository;
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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestEntityManager
@Transactional
@ActiveProfiles("test")
class CategoriaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CategoriaRepository categoriaRepository;

    private Categoria INFORMATICA;

    @BeforeEach
    void setup() {
        INFORMATICA = new Categoria("INFORMATICA");
        entityManager.persist(INFORMATICA);

    }


    @Test
    public void deveriaListar3Categorias() throws Exception {
        URI uri = new URI("/api/categorias");

        Categoria FILMES = new Categoria("FILMES");
        Categoria LIVROS = new Categoria("LIVROS");

        entityManager.persist(FILMES);
        entityManager.persist(LIVROS);

        mockMvc
                .perform(MockMvcRequestBuilders
                        .get(uri)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$.[0].id", equalTo(INFORMATICA.getId().intValue())))
                .andExpect(jsonPath("$.[0].nome", equalTo(INFORMATICA.getNome())))
                .andExpect(jsonPath("$.[0].status", equalTo(INFORMATICA.getStatus().toString())))
                .andExpect(jsonPath("$.[1].id", equalTo(FILMES.getId().intValue())))
                .andExpect(jsonPath("$.[1].nome", equalTo(FILMES.getNome())))
                .andExpect(jsonPath("$.[1].status", equalTo(FILMES.getStatus().toString())))
                .andExpect(jsonPath("$.[2].id", equalTo(LIVROS.getId().intValue())))
                .andExpect(jsonPath("$.[2].nome", equalTo(LIVROS.getNome())))
                .andExpect(jsonPath("$.[2].status", equalTo(LIVROS.getStatus().toString())));
    }

    @Test
    void deveriaRetornarDetalhesCategoriaCom2Produtos() throws Exception {

        URI uri = new URI("/api/categorias/" + INFORMATICA.getId());

         Produto teclado = new Produto("teclado", "Dell", new BigDecimal("25.4"), 3, INFORMATICA );
         Produto tela = new Produto("tela", "Dell", new BigDecimal("45.00"), 10, INFORMATICA );

         entityManager.persist(teclado);
         entityManager.persist(tela);

        INFORMATICA.setProdutos(List.of(teclado, tela));

        mockMvc.perform(MockMvcRequestBuilders
                        .get(uri))
                .andExpect(status().isOk())
                .andDo(log())
                .andExpect(jsonPath("$.nome", equalTo(INFORMATICA.getNome())))
                .andExpect(jsonPath("$.status", equalTo(INFORMATICA.getStatus().toString())))
                .andExpect(jsonPath("$.produtos", equalTo(INFORMATICA.getProdutos().stream().map(Produto::getName).collect(Collectors.toList()))));
    }

    @Test
    void deveriaRetornarDetalhesCategoriaSemProdutos() throws Exception {

        URI uri = new URI("/api/categorias/" + INFORMATICA.getId());

        mockMvc.perform(MockMvcRequestBuilders
                        .get(uri))
                .andExpect(status().isOk())
                .andDo(log())
                .andExpect(jsonPath("$.nome", equalTo(INFORMATICA.getNome())))
                .andExpect(jsonPath("$.status", equalTo(INFORMATICA.getStatus().toString())))
                .andExpect(jsonPath("$.produtos", equalTo(new ArrayList<>(INFORMATICA.getProdutos()))));
    }


    @Test
    public void deveriaCadastrarCategoria() throws Exception {
        URI uri = new URI("/api/categorias");

        JSONObject json = new JSONObject().put("nome", "LIVROS");
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
    public void deveriaRetornarRelatorioPedidosCategoria() throws Exception {

        URI uri = new URI("/api/categorias/pedidos");
        MvcResult retorno = mockMvc
                .perform(MockMvcRequestBuilders
                        .get(uri)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status()
                        .is(200))
                .andReturn();

        System.out.println(">>>> ");

    }


    @Test
    public void deveriaRetornar404ParaCategoriaNaoEncontradoParaAlteração() throws Exception {

        URI uri = new URI("/api/categorias/" + 9);

        String request = new JSONObject()
                .put("nome", "FILMES").toString();

        mockMvc
                .perform(MockMvcRequestBuilders
                        .put(uri)
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

    }

    @Test
    public void deveriaAlterarStatusCategoria() throws Exception {

        URI uri = new URI("/api/categorias/" + INFORMATICA.getId());

        mockMvc
                .perform(MockMvcRequestBuilders
                        .patch(uri)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status()
                        .is(200));

    }

    @Test
    public void deveriaRetornar404NaAlteracaoStatusCategoria() throws Exception {

        URI uri = new URI("/api/categorias/298383");

        mockMvc
                .perform(MockMvcRequestBuilders
                        .patch(uri)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status()
                        .is(404));

    }

    @Test
    public void deveriaRetornar400ParaAlteracaoComBodyVazio() throws Exception {

        URI uri = new URI("/api/categorias/" + INFORMATICA.getId());

        mockMvc
                .perform(MockMvcRequestBuilders
                        .put(uri)
                        .content("")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status()
                        .is(400));

    }

    @Test
    public void deveriaDeletarCategoria() throws Exception {

        URI uri = new URI("/api/categorias/" + INFORMATICA.getId());

        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete(uri)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}