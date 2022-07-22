package br.com.alura.comex.repository;

import br.com.alura.comex.builder.CategoriaBuilder;
import br.com.alura.comex.infra.repository.CategoriaRepository;
import br.com.alura.comex.dominio.model.entities.Categoria;
import br.com.alura.comex.dominio.model.enums.Status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class CategoriaRepositoryTest {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Test
    public void deveRetornarDoisRegistros() {

        Categoria categoria1 =
                new CategoriaBuilder()
                        .comNome("LIVROS")
                        .comStatus(Status.ATIVA)
                        .build();

        Categoria categoria2 =
                new CategoriaBuilder()
                        .comNome("JOGOS")
                        .comStatus(Status.ATIVA)
                        .build();

        categoriaRepository.save(categoria1);
        categoriaRepository.save(categoria2);

        List<Categoria> resultado = categoriaRepository.findAll();

        assertThat(resultado)
                .extracting(Categoria::getNome, Categoria::getStatus)
                .contains(
                        tuple("LIVROS", Status.ATIVA),
                        tuple("JOGOS", Status.ATIVA)
                );
    }
}
