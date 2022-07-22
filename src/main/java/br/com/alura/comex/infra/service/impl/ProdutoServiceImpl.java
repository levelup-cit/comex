package br.com.alura.comex.a.service.impl;

import br.com.alura.comex.a.service.ProdutoService;
import br.com.alura.comex.dominio.model.entities.Produto;
import br.com.alura.comex.infra.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    @Autowired
    ProdutoRepository produtoRepository;

    @Override
    public List<Produto> listarTodos() {

        return produtoRepository.findAll();
    }

    @Override
    public Page<Produto> obterPagina(Integer page, Integer linhasPorPage, String ordenarPor, String direcao){
        PageRequest pageRequest = PageRequest.of(page,linhasPorPage, Sort.Direction.valueOf(direcao), ordenarPor);
        return produtoRepository.findAll(pageRequest);
    }

    @Override
    public Produto listarPorCodigo(Long id) {

        return produtoRepository.findById(id).get();
    }

    @Override
    public Produto inserir(Produto produto) {

        return produtoRepository.save(produto);
    }

    @Override
    public Produto atualizar(Produto produto) {

        return this.produtoRepository.save(produto);
    }

    @Override
    public void remover(Long id) {

        this.produtoRepository.deleteById(id);
    }
}
