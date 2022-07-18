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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URI;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
public class ProdutoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private JSONObject criaObjetoJson() throws JSONException {
        return new JSONObject()
                .put("nome","Jogo")
                .put("descricao", "The last of us")
                .put("precoUnitario",400.00)
                .put("quantidadeEstoque", 2)
                .put("categoria", 2);
    }

    @Test
    public void deveCadastrarProduto() throws Exception {
        URI uri = new URI("/api/produtos");
        JSONObject json = criaObjetoJson();
        String request = json.toString();

        mockMvc
                .perform(MockMvcRequestBuilders
                        .post(uri)
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status().is(201));
    }

    @Test
    public void deveListarTodosProdutos() throws Exception {
        URI uri = new URI("/api/produtos");

        mockMvc.perform(MockMvcRequestBuilders
                        .get(uri)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").isNotEmpty())
                .andExpect(jsonPath("$.content[0].nome").value("Jogo"));
    }

    @Test
    public void devePesquisarProdutoPorId() throws Exception {
        URI uri = new URI("/api/produtos/" + 1);

        mockMvc
                .perform(MockMvcRequestBuilders
                        .get(uri)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("The last of us"));
    }

    @Test
    public void deveRetornar404PorIdNaoExistente() throws Exception {
        URI uri = new URI("/api/produtos/" + 404);

        mockMvc
                .perform(MockMvcRequestBuilders
                        .get(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNotFound());
    }

}