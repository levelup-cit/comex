package br.com.alura.comex.repository;

import br.com.alura.comex.application.model.projecao.PedidosPorCategoriaProjecao;
import br.com.alura.comex.application.repository.PedidoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class PedidoRepositoryTest {
    @Autowired
    private PedidoRepository repository;

    @Test
    public void deveRetornarUmRegistroParaCadaCategoria() {
        List<PedidosPorCategoriaProjecao> resultado = repository.findPedidosPorCategoria();

        assertThat(resultado)
                .hasSize(2)
                .extracting(PedidosPorCategoriaProjecao::getNome,
                        PedidosPorCategoriaProjecao::getQuantidadeProdutos,
                        PedidosPorCategoriaProjecao::getMontanteVendido)
                .containsExactly(
                        tuple(("JOGOS"), (2L), new BigDecimal("800.00")),
                        tuple(("FILMES"), (1L), new BigDecimal("20.00"))
                );
    }
}