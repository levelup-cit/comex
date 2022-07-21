package br.com.alura.comex.dominio.categoria;

import br.com.alura.comex.dominio.categoria.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

  Categoria findByNome(String nome);



}
