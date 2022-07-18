package br.com.alura.comex.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Produto")
public class ProdutoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String descricao;
	private double preco_unitario;
	private int quantidade_estoque;
	private Long categoriaId;
	
	@ManyToOne
	//@Column(name = "id_categoria")
	private CategoriaEntity id_categoria;
	
	public ProdutoEntity() {
	}

	public ProdutoEntity(String nome, String descricao, double preco_unitario, int quantidade_estoque,
			CategoriaEntity categoria) {
		this.nome = nome;
		this.descricao = descricao;
		this.preco_unitario = preco_unitario;
		this.quantidade_estoque = quantidade_estoque;
		this.id_categoria = categoria;
	}

	public ProdutoEntity(String nome, String descricao, double preco_unitario, int quantidade_estoque, Long categoriaId) {
		this.nome = nome;
		this.descricao = descricao;
		this.preco_unitario = preco_unitario;
		this.quantidade_estoque = quantidade_estoque;
		//this.categoriaId = categoria.getId();
		this.categoriaId = (long) 1; //para teste sem BD
	}
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getPreco_unitario() {
		return preco_unitario;
	}

	public void setPreco_unitario(double preco_unitario) {
		this.preco_unitario = preco_unitario;
	}

	public int getQuantidade_estoque() {
		return quantidade_estoque;
	}

	public void setQuantidade_estoque(int quantidade_estoque) {
		this.quantidade_estoque = quantidade_estoque;
	}

	public Long getId() {
		return id;
	}

	public Long getCategoriaId() {
		return categoriaId;
	}
}
