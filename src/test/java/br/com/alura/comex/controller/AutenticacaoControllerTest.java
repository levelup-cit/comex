package br.com.alura.comex.controller;

import br.com.alura.comex.dominio.usuario.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URI;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@AutoConfigureMockMvc
@ActiveProfiles("test")
class AutenticacaoControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private UsuarioRepository usuarioRepository;

//  @Autowired
//  private TestEntityManager testEntityManager;

  @Test
  void deveDevolver400CasoDadosDeAutenticacaoEstejamIncorretos() throws Exception {

    URI uri = new URI("/auth");
    String json = "{\"email\":\"invalido@email.com\",\"senha\":\"123456\"}";

    mockMvc.perform(MockMvcRequestBuilders
                    .post(uri)
                    .content(json)
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers
                    .status()
                    .is(403));
  }

//  @Test
//  void deveAutenticarUsuarioCadastrado() throws Exception {
//
//    Usuario usuario = new Usuario("aluno@email.com", "$2a$10$HZ7H4VOI.b12rGISjwPRT.8ORSfukcbHlUowek14WjuiI7gYxLkya", new ArrayList<>());
//
//    Usuario save = usuarioRepository.save(usuario);
//
//    assertNotNull(save);
//
//    URI uri = new URI("/auth");
//    String json = "{\"email\":\"aluno@email.com\",\"senha\":\"123456\"}";
//
//    mockMvc
//            .perform(MockMvcRequestBuilders
//                    .post(uri)
//                    .content(json)
//                    .contentType(MediaType.APPLICATION_JSON))
//            .andExpect(MockMvcResultMatchers
//                    .status()
//                    .is(200));
//  }
}