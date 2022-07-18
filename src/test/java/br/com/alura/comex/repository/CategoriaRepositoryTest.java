package br.com.alura.comex.repository;

import br.com.alura.comex.domain.Categoria;
import br.com.alura.comex.application.repository.CategoriaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

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