package br.com.alura.comex.controller;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.net.URI;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class CategoriaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private JSONObject criaObjetoJson() throws JSONException {
        return new JSONObject()
                .put("nome","INFORMÁTICA");
    }

    @Test
    public void deveCriarCategoria() throws Exception {
        URI uri = new URI("/api/categorias");
        JSONObject json = criaObjetoJson();
        String request = json.toString();

        mockMvc
                .perform(MockMvcRequestBuilders
                        .post(uri)
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated());
    }

    @Test
    public void deveListarTodasCategorias() throws Exception {
        URI uri = new URI("/api/categorias");

        mockMvc.perform(MockMvcRequestBuilders
                        .get(uri)
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.length()").isNotEmpty())
                        .andExpect(jsonPath("$[0].nome").value("JOGOS"))
                        .andExpect(jsonPath("$[1].nome").value("FILMES"))
                        .andExpect(jsonPath("$[2].nome").value("INFORMÁTICA"));

    }

    @Test
    public void devePesquisarCategoriasPorId() throws Exception {
        URI uri = new URI("/api/categorias/" + 1);

        mockMvc
                .perform(MockMvcRequestBuilders
                        .get(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("JOGOS"))
                .andExpect(jsonPath("$.status").value("ATIVA"));
    }


    @Test
    public void deveListarPedidosPorCategoria() throws Exception {
        URI uri = new URI("/api/categorias/pedidos");

        mockMvc
                .perform(MockMvcRequestBuilders
                        .get(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().is(200))
                .andReturn();
    }

    @Test
    public void deveRetornar404PorIdNaoExistente() throws Exception {
        URI uri = new URI("/api/categorias/" + 404);

        mockMvc
                .perform(MockMvcRequestBuilders
                        .get(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNotFound());
    }

}