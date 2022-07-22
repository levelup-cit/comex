package br.com.alura.comex.a.service;

import br.com.alura.comex.dominio.model.entities.Cliente;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ClienteService {

    List<Cliente> listarTodos();

    public Page<Cliente> obterPagina(Integer page, Integer linhasPorPage, String ordenarPor, String direcao);

    Cliente listarPorCodigo(Long id);

    Cliente inserir(Cliente cliente);

    Cliente atualizar(Cliente cliente);

    void remover(Long id);
}
