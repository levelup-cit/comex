package br.com.alura.comex.application.controller.dto;

import br.com.alura.comex.domain.Categoria;
import br.com.alura.comex.domain.Produto;
import br.com.alura.comex.application.model.enuns.StatusCategoria;

import java.util.List;
import java.util.stream.Collectors;

public class DetalhesCategoriaDto {
    private String nome;
    private StatusCategoria status;
    private List<String> produtos;

    public DetalhesCategoriaDto(Categoria categoria) {
        this.nome = categoria.getNome();
        this.status = categoria.getStatus();
        this.produtos = categoria.getProdutos().stream().map(Produto::getNome).collect(Collectors.toList());
    }

    public String getNome() {
        return nome;
    }

    public StatusCategoria getStatus() {
        return status;
    }

    public List<String> getProdutos() {
        return produtos;
    }

    @Override
    public String toString() {
        return "DetalhesDaCategoriaDto{" +
                "nome='" + nome + '\'' +
                ", status=" + status +
                ", produtos=" + produtos +
                '}';
    }
}
