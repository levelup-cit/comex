package br.com.alura.comex.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.alura.comex.enums.TipoDescontoEnum;

@Entity
@Table(name = "Pedido")
public class PedidoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDate data = LocalDate.now();
	private double desconto;

	@Enumerated(EnumType.STRING)
	private TipoDescontoEnum tipo_desconto;

	@OneToMany(mappedBy = "pedido_id", cascade = CascadeType.ALL)
	private List<ItemDePedidoEntity> item_pedido = new ArrayList<>();

	@ManyToOne
	// @Column(name = "id_cliente")
	private ClienteEntity id_cliente;

	public PedidoEntity() {
	}

	public PedidoEntity(ClienteEntity cliente) {
		this.id_cliente = cliente;
	}

	public void adicionarItem(ItemDePedidoEntity item) {
		item.setPedidoId(this);
		this.item_pedido.add(item);
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
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
