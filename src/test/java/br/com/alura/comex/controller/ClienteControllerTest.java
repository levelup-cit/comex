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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
public class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private JSONObject criaObjetoJson() throws JSONException {
        return new JSONObject()
                .put("nome","Thais")
                .put("cpf", "12312312312")
                .put("telefone","12112231223")
                .put("rua", "Rua Principal")
                .put("numero", "123")
                .put("bairro", "Centro")
                .put("cidade", "Belo Horizonte")
                .put("estado", "MG");
    }

    @Test
    public void deveAdicionarCliente() throws Exception {
        URI uri = new URI("/api/clientes");

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
    public void deveListarTodosClientes() throws Exception {
        URI uri = new URI("/api/clientes");
        mockMvc.perform(MockMvcRequestBuilders
                        .get(uri))
                        .andExpect(status().isOk())
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                        .andExpect(jsonPath("$.length()").isNotEmpty())
                        .andExpect(jsonPath("$.content[0].nome").value("Cassia"));
    }

    @Test
    public void deveRetornar404PorIdNaoExistente() throws Exception {
        URI uri = new URI("api/clientes/" + 404);

        mockMvc
                .perform(MockMvcRequestBuilders
                        .get(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNotFound());
    }
}