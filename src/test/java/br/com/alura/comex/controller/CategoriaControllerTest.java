package br.com.alura.comex.controller;

import br.com.alura.comex.dominio.categoria.Categoria;
import br.com.alura.comex.dominio.categoria.CategoriaRepository;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.transaction.Transactional;
import java.net.URI;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@Transactional
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestEntityManager
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class CategoriaControllerTest {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Autowired
  private CategoriaRepository categoriaRepository;

  @Autowired
  private TestEntityManager testEntityManager;

  @Autowired
  private MockMvc mockMvc;

  @Test
  void deveRetornarUmListaDeCategoriasVazia() throws Exception {

    List<Categoria> listaDeCategorias = categoriaRepository.findAll();

    URI uri = new URI("/categorias");

    mockMvc.perform(MockMvcRequestBuilders
                    .get(uri)
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(0)));

    //Teste se uma lista de categorias foi retornada
    assertEquals(0, listaDeCategorias.size());
  }

  @Test
  void deveRetornarUmaListaDeCategoriasComUmItem() throws Exception {

    Categoria categoria = new Categoria("CASA");

    testEntityManager.persist(categoria);

    List<Categoria> listaDeCategorias = categoriaRepository.findAll();

    URI uri = new URI("/categorias");

    mockMvc.perform(MockMvcRequestBuilders
                    .get(uri)
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(1)))
            .andExpect(jsonPath("$.[0].nome", equalTo(categoria.getNome())));

    //Teste se uma lista de categorias foi retornada
    assertEquals(1, listaDeCategorias.size());

    //Testa se o resultado esperado é o mesmo persistido
    assertEquals("CASA", listaDeCategorias.get(0).getNome());
  }

  @Test
  void deveInserirUmaCategoria() throws Exception {

    URI uri = new URI("/categorias");

    JSONObject json = new JSONObject().put("nome", "LIVROS");
    String request = json.toString();

    mockMvc.perform(MockMvcRequestBuilders
                    .post(uri)
                    .content(request)
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status()
                    .is(201));
  }

//  @Test
//  void deveAlterarStatusDaCategoria() throws Exception{
//    Categoria categoria = new Categoria("CASA");
//
//    testEntityManager.persist(categoria);
//
//    URI uri = new URI("/categorias/" + 1);
//    //"{\"id\":\"1\",\"nome\":\"LIVROS\",\"status\":\"ATIVO\"}"
//    JSONObject json = new JSONObject().put("status", "ATIVO");
//
//    mockMvc.perform(MockMvcRequestBuilders
//                    .put(uri)
//                    .contentType(MediaType.APPLICATION_JSON))
//            .andExpect(status()
//                    .is(201));
//  }

//  @AfterEach
//  public void execute() {
//    jdbcTemplate.execute("DROP DATABASE comexdb_test");
//  }
}