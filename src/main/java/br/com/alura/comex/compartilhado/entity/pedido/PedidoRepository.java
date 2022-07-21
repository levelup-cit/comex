package br.com.alura.comex.compartilhado.entity.pedido;

import br.com.alura.comex.compartilhado.entity.categoria.RelatorioPedidosPorCategoriaProjecao;
import br.com.alura.comex.compartilhado.entity.enuns.StatusPedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PedidoRepository {

    Page<Pedido> listarTodosPedidos(Pageable pageable);

    Pedido buscarPedidoPorCodIdentificador(Long codigoIdentificador);

    Pedido cadastrarPedido(Pedido pedido);

    List<RelatorioPedidosPorCategoriaProjecao> buscarPedidosPorCategoria();

    Pedido atualizarStatus(Long id, StatusPedido statusPedido);

}
