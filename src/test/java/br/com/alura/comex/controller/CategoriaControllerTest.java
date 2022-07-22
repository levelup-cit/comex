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

import java.net.URI;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
public class CategoriaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void deveCriarUmaCategoria() throws Exception {

        URI uri = new URI("/categorias");

        JSONObject json = criarObjetoJson();
        String request = json.toString();

        mockMvc
                .perform(post(uri)
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated());

    }

    @Test
    public void deveAtualizarStatus() throws Exception {

        int idCategoria = 7;
        URI uri = new URI("/categorias/"+idCategoria);

        JSONObject json = criarObjetoJson();
        String request = json.toString();

        mockMvc
                .perform(patch(uri)
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());

    }

    @Test
    public void deveListarTodasAsCategorias() throws Exception {

        URI uri = new URI("/categorias");
        mockMvc.perform(get(uri))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").isNotEmpty())
                .andExpect(jsonPath("$[0].nome").value("JOGOS"))
                .andExpect(jsonPath("$[1].nome").value("LIVROS"));

    }

    @Test
    public void deveListarCategoriasPorId() throws Exception {

        int idCategoria = 50;
        URI uri = new URI("/categorias/"+idCategoria);

        JSONObject json = criarObjetoJson();
        String request = json.toString();

        mockMvc
                .perform(get(uri)
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("JOGOS"))
                .andExpect(jsonPath("$.status").value("ATIVA"));

    }

    private JSONObject criarObjetoJson() throws JSONException {
        return new JSONObject()
                .put("nome","JOGOS");
    }
}
