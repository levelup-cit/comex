package br.com.alura.comex.dominio.pedido;

import br.com.alura.comex.aplicacao.relatorios.RelatorioPedidoPorCategoriaProjecao;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {


  @Query(value = "SELECT c.nome AS nomeCategoria, COUNT(i.id) AS quantidadeProdutosVendidos, "
          + "SUM((i.preco_unitario)) AS montanteVendido "
          + "FROM pedidos "
          + "JOIN itens_de_pedido i "
          + "JOIN produtos p "
          + "JOIN categoria c "
          + "WHERE pedidos.id = i.pedido_id "
          + "AND i.produto_id = p.id "
          + "AND p.categoria_id = c.id "
          + "GROUP BY c.id", nativeQuery = true)
  List<RelatorioPedidoPorCategoriaProjecao> gerarRelatorioDePedidosPorCategoria();
}
