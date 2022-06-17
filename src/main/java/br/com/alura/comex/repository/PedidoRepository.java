package br.com.alura.comex.repository;

import br.com.alura.comex.controller.dto.projecao.PedidosPorCategoriaProjecao;
import br.com.alura.comex.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PedidoRepository  extends JpaRepository<Pedido, Long> {

    @Query(value = "SELECT c.nome, COUNT(item.id) AS quantidadeProdutos, SUM((item.preco_unitario * item.quantidade)) AS montanteVendido " +
            "FROM pedidos " +
            "JOIN itens_pedido item " +
            "JOIN produtos p " +
            "JOIN categorias c " +
            "WHERE pedidos.id = item.pedido_id AND item.produto_id = p.id AND p.categoria_id = c.id " +
            "GROUP BY c.id, pedidos.id, item.id", nativeQuery = true)
    List<PedidosPorCategoriaProjecao> findPedidosPorCategoria();
}
