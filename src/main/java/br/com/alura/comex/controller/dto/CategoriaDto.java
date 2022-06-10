package br.com.alura.comex.controller.dto;

import br.com.alura.comex.entity.Categoria;
import br.com.alura.comex.entity.Produto;
import br.com.alura.comex.entity.enuns.StatusCategoria;

import java.util.ArrayList;
import java.util.List;

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

    public static List<CategoriaDto> converter(Iterable<Categoria> categorias){

        List<CategoriaDto> categoriasDto = new ArrayList<CategoriaDto>();
        categorias.iterator().forEachRemaining(catg -> {
            categoriasDto.add(new CategoriaDto(catg));
        });

        return categoriasDto;
        //return categorias.stream().map(CategoriaDto::new).collect(Collectors.toList());
    }
}
