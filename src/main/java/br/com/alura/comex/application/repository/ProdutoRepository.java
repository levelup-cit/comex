package br.com.alura.comex.application.repository;

import br.com.alura.comex.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
