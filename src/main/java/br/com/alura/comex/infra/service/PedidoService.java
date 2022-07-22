package br.com.alura.comex.a.service;

import br.com.alura.comex.dominio.model.entities.Pedido;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PedidoService {

    List<Pedido> listarTodos();

    public Page<Pedido> obterPagina(Integer page, Integer linhasPorPage, String ordenarPor, String direcao);

    Pedido listarPorCodigo(Long id);

    Pedido inserir(Pedido pedido);

    Pedido atualizar(Pedido pedido);

    void remover(Long id);
}
