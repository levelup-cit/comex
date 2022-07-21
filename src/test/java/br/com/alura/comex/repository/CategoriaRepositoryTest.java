package br.com.alura.comex.repository;


import br.com.alura.comex.dominio.categoria.Categoria;
import br.com.alura.comex.dominio.categoria.CategoriaRepository;
import br.com.alura.comex.dominio.produto.ProdutoRepository;
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

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestEntityManager
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class CategoriaRepositoryTest {

  @Autowired
  private JdbcTemplate jdbcTemplate;
  @Autowired
  private CategoriaRepository categoriaRepository;

  @Autowired
  private ProdutoRepository produtoRepository;

  @Autowired
  private TestEntityManager testEntityManager;

  @Autowired
  private MockMvc mockMvc;

  @Test
  void deveRetornarUmaCategoriaAoPesquisarPeloNome() throws Exception{

    Categoria categoria = new Categoria("CASA");

    testEntityManager.persist(categoria);

    Categoria retornoBusca = categoriaRepository.findByNome("CASA");

    URI uri = new URI("/categorias");

    mockMvc.perform(MockMvcRequestBuilders
                    .get(uri)
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(1)))
            .andExpect(jsonPath("$.[0].nome", equalTo(categoria.getNome())));

  }

  //Dropando o bd de testes após cada teste
//  @AfterEach
//  public void execute() {
//    jdbcTemplate.execute("DROP DATABASE comexdb_test" );
//  }


}