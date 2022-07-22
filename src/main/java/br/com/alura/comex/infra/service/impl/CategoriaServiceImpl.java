package br.com.alura.comex.a.service.impl;

import br.com.alura.comex.a.service.CategoriaService;
import br.com.alura.comex.dominio.model.entities.Categoria;
import br.com.alura.comex.dominio.model.enums.Status;
import br.com.alura.comex.infra.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    CategoriaRepository categoriaRepository;

    @Override
    public List<Categoria> listarTodos() {

        return categoriaRepository.findAll();
    }

    @Override
    public Page<Categoria> obterPagina(Integer page, Integer linhasPorPage, String ordenarPor, String direcao){
        PageRequest pageRequest = PageRequest.of(page,linhasPorPage, Sort.Direction.valueOf(direcao), ordenarPor);
        return categoriaRepository.findAll(pageRequest);
    }

    @Override
    public Categoria listarPorCodigo(Long id) {

        return categoriaRepository.findById(id).get();
    }

    @Override
    public Categoria inserir(Categoria categoria) {

        return categoriaRepository.save(categoria);
    }

    @Override
    public Categoria atualizar(Categoria categoria) {

        return this.categoriaRepository.save(categoria);
    }

    @Override
    public Categoria alterarStatus(Categoria categoria) {

        if (categoria.getStatus().equals(Status.ATIVA)) {
            categoria.setStatus(Status.INATIVA);
            return categoria;
        }
        categoria.setStatus(Status.ATIVA);
        return categoria;
    }

    @Override
    public void remover(Long id) {

        this.categoriaRepository.deleteById(id);
    }

}
