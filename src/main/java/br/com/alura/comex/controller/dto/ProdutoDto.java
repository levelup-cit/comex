package br.com.alura.comex.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.alura.comex.modelo.CategoriaEntity;
import br.com.alura.comex.modelo.ProdutoEntity;

public class ProdutoDto {

	CategoriaEntity categoria = new CategoriaEntity();

	private String nome;
	private String descricao;
	private double preco_unitario;
	private int quantidade_estoque;
	private Long categoriaId;

	public ProdutoDto(ProdutoEntity produto) {
		this.nome = produto.getNome();
		this.descricao = produto.getDescricao();
		this.preco_unitario = produto.getPreco_unitario();
		this.quantidade_estoque = produto.getQuantidade_estoque();
		this.categoriaId = produto.getCategoriaId();
	}
	
	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public double getPreco_unitario() {
		return preco_unitario;
	}

	public int getQuantidade_estoque() {
		return quantidade_estoque;
	}

	public Long getCategoriaId() {
		return categoriaId;
	}

	public static List<ProdutoDto> converter(List<ProdutoEntity> produtos) {
		return produtos.stream().map(ProdutoDto::new).collect(Collectors.toList());
	}
}
