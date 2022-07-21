package br.com.alura.comex.dominio.produto;

import br.com.alura.comex.dominio.produto.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProdutoRepository extends PagingAndSortingRepository<Produto, Long>,
        JpaSpecificationExecutor<Produto>, JpaRepository<Produto, Long> {
}
