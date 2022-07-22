package br.com.alura.comex.a.service.impl;

import br.com.alura.comex.a.service.PedidoService;
import br.com.alura.comex.dominio.model.entities.Pedido;
import br.com.alura.comex.infra.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    PedidoRepository pedidoRepository;

    @Override
    public List<Pedido> listarTodos() {

        return pedidoRepository.findAll();
    }

    @Override
    public Page<Pedido> obterPagina(Integer page, Integer linhasPorPage, String ordenarPor, String direcao){
        PageRequest pageRequest = PageRequest.of(page,linhasPorPage, Sort.Direction.valueOf(direcao), ordenarPor);
        return pedidoRepository.findAll(pageRequest);
    }

    @Override
    public Pedido listarPorCodigo(Long id) {

        return pedidoRepository.findById(id).get();
    }

    @Override
    public Pedido inserir(Pedido pedido) {

        return pedidoRepository.save(pedido);
    }

    @Override
    public Pedido atualizar(Pedido pedido) {

        return this.pedidoRepository.save(pedido);
    }

    @Override
    public void remover(Long id) {

        this.pedidoRepository.deleteById(id);
    }
}
