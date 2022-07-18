package br.com.alura.comex.application.controller.dto;

import br.com.alura.comex.domain.Dimensoes;
import br.com.alura.comex.domain.Produto;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class ProdutoDto {
    private Long id;
    private String nome;
    private String descricao;
    private BigDecimal precoUnitario;
    private int quantidadeEstoque;
    private CategoriaDto categoria;
    private Dimensoes dimensoes;

    public ProdutoDto(Produto produto) {
        this.id = produto.getId();
        this.nome = produto.getNome();
        this.descricao = produto.getDescricao();
        this.precoUnitario = produto.getPrecoUnitario();
        this.quantidadeEstoque = produto.getQuantidadeEstoque();
        this.categoria = new CategoriaDto(produto.getCategoria());
        this.dimensoes = produto.getDimensoes();
    }

    public static List<ProdutoDto> converter(List<Produto> produtos) {
        return produtos.stream().map(ProdutoDto::new).collect(Collectors.toList());
    }

    public static Page<ProdutoDto> converterPagina(Page<Produto> produtos) {
        return produtos.map(ProdutoDto::new);
    }
}
