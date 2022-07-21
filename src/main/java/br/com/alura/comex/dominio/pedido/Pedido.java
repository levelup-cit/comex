package br.com.alura.comex.dominio.pedido;

import br.com.alura.comex.dominio.cliente.Cliente;
import br.com.alura.comex.dominio.itemDePedido.ItemDePedido;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "pedidos")
public class Pedido {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private LocalDate data;

  @ManyToOne
  private Cliente cliente;

  private BigDecimal desconto;

//  @Column(name = "tipo_de_desconto_por_pedido")
//  @Enumerated(EnumType.STRING)
  private TipoDeDescontoPorPedido tipoDeDescontoPorPedido;

  @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
  private List<ItemDePedido> itens = new ArrayList<>();

  public Pedido(LocalDate data, Cliente cliente,
                BigDecimal desconto, TipoDeDescontoPorPedido tipoDeDescontoPorPedido) {
    this.data = data;
    this.cliente = cliente;
    this.desconto = desconto;
    this.tipoDeDescontoPorPedido = tipoDeDescontoPorPedido;
  }

  public LocalDate getData() {
    return data;
  }


  public Cliente getCliente() {
    return cliente;
  }

  public BigDecimal getDesconto() {
    return desconto;
  }

  public TipoDeDescontoPorPedido getTipoDeDescontoPorPedido() {
    return tipoDeDescontoPorPedido;
  }
}
