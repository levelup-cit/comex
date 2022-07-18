package br.com.alura.comex.application.repository;

import br.com.alura.comex.application.model.projecao.PedidosPorCategoriaProjecao;
import br.com.alura.comex.domain.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PedidoRepository  extends JpaRepository<Pedido, Long> {
    @Query(value = "SELECT c.nome, SUM(item.quantidade) AS quantidadeProdutos, SUM((item.preco_unitario * item.quantidade)) AS montanteVendido " +
            "FROM itens_pedido item " +
            "JOIN produtos p " +
            "JOIN categorias c " +
            "WHERE item.produto_id = p.id AND p.categoria_id = c.id " +
            "GROUP BY item.produto_id, c.nome", nativeQuery = true)
    List<PedidosPorCategoriaProjecao> findPedidosPorCategoria();
}
