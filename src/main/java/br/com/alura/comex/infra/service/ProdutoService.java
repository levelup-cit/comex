package br.com.alura.comex.a.service;

import br.com.alura.comex.dominio.model.entities.Produto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProdutoService {

    List<Produto> listarTodos();

    public Page<Produto> obterPagina(Integer page, Integer linhasPorPage, String ordenarPor, String direcao);

    Produto listarPorCodigo(Long id);

    Produto inserir(Produto produto);

    Produto atualizar(Produto produto);

    void remover(Long id);
}
