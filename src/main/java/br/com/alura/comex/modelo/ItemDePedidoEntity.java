package br.com.alura.comex.modelo;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.alura.comex.enums.TipoDescontoEnum;

@Entity
@Table(name = "Item_Pedido")
public class ItemDePedidoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private double preco_unitario;
	private int quantidade;
	private double desconto;

	@Enumerated(EnumType.STRING)
	private TipoDescontoEnum tipo_desconto;

	// @Column(name = "pedido_id")
	@ManyToOne
	private PedidoEntity pedido_id;

	// @Column(name = "produto_id")
	@ManyToOne
	private ProdutoEntity produto_id;

	public ItemDePedidoEntity() {
	}

	public ItemDePedidoEntity(int quantidade, PedidoEntity pedido, ProdutoEntity produto) {
		this.quantidade = quantidade;
		this.pedido_id = pedido;
		this.produto_id = produto;
		this.preco_unitario = produto.getPreco_unitario();
	}

	public PedidoEntity getPedido() {
		return pedido_id;
	}

	public void setPedidoId(PedidoEntity pedido) {
		this.pedido_id = pedido;
	}

	public ProdutoEntity getProduto() {
		return produto_id;
	}

	public void setProduto(ProdutoEntity produto) {
		this.produto_id = produto;
	}

	public double getPreco_unitario() {
		return preco_unitario;
	}

	public void setPreco_unitario(double preco_unitario) {
		this.preco_unitario = preco_unitario;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public double getDesconto() {
		return desconto;
	}

	public void setDesconto(double desconto) {
		this.desconto = desconto;
	}

	public TipoDescontoEnum getTipo_desconto() {
		return tipo_desconto;
	}

	public void setTipo_desconto(TipoDescontoEnum tipo_desconto) {
		this.tipo_desconto = tipo_desconto;
	}

	public long getId() {
		return id;
	}
}
