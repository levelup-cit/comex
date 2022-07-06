package br.com.alura.comex;

import br.com.alura.comex.model.Categoria;
import br.com.alura.comex.repository.CategoriaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class CategoriaRepositoryTest {
    @Autowired
    private CategoriaRepository repository;

    @Test
    public void deveRetornarListaDeCategorias(){
        Categoria categoria = new Categoria("FILMES");
        repository.save(categoria);
        Optional<Categoria> retorno = repository.findById(categoria.getId());

        assertThat(retorno).contains(categoria);
    }
}