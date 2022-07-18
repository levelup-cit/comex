package br.com.alura.comex.application.controller.dto;

import br.com.alura.comex.domain.Categoria;
import br.com.alura.comex.domain.Produto;
import br.com.alura.comex.application.model.enuns.StatusCategoria;

import java.util.List;
import java.util.stream.Collectors;

public class CategoriaDto {
    private Long id;
    private String nome;
    private StatusCategoria status;

    private List<Produto> produtos;

    public CategoriaDto(Categoria categoria) {
        this.id = categoria.getId();
        this.nome = categoria.getNome();
        this.status = categoria.getStatus();
        this.produtos = categoria.getProdutos();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public StatusCategoria getStatus() {
        return status;
    }

    public static List<CategoriaDto> converter(List<Categoria> categorias){
        return categorias.stream().map(CategoriaDto::new).collect(Collectors.toList());
    }
}