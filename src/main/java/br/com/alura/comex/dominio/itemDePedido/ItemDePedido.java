package br.com.alura.comex.dominio.itemDePedido;

import br.com.alura.comex.dominio.pedido.Pedido;
import br.com.alura.comex.dominio.produto.Produto;

import java.math.BigDecimal;
import javax.persistence.*;

@Entity
@Table(name = "itens_de_pedido")
public class ItemDePedido {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "preco_unitario")
  private BigDecimal precoUnitario;

  private Long quantidade;

  @ManyToOne(fetch = FetchType.LAZY)
  private Produto produto;

  @ManyToOne(fetch = FetchType.LAZY)
  private Pedido pedido;

  private BigDecimal desconto;

  @Column(name = "tipo_de_desconto_por_item_de_pedido")
  private TipoDeDescontoPorItemDePedido tipoDeDescontoPorItemDePedido;

  public ItemDePedido(BigDecimal precoUnitario, Long quantidade,
                      Produto produto, Pedido pedido,
                      BigDecimal desconto,
                      TipoDeDescontoPorItemDePedido tipoDeDescontoPorItemDePedido) {
    this.precoUnitario = precoUnitario;
    this.quantidade = quantidade;
    this.produto = produto;
    this.pedido = pedido;
    this.desconto = desconto;
    this.tipoDeDescontoPorItemDePedido = tipoDeDescontoPorItemDePedido;
  }

  @Override
  public String toString() {
    return "ItemDePedido{" +
            "id=" + id +
            ", precoUnitario=" + precoUnitario +
            ", quantidade=" + quantidade +
            ", produto=" + produto +
            ", pedido=" + pedido +
            ", desconto=" + desconto +
            ", tipoDeDescontoPorItemDePedido=" + tipoDeDescontoPorItemDePedido +
            '}';
  }

  public BigDecimal getPrecoUnitario() {
    return precoUnitario;
  }

  public Long getQuantidade() {
    return quantidade;
  }

  public Produto getProduto() {
    return produto;
  }

  public Pedido getPedido() {
    return pedido;
  }

  public BigDecimal getDesconto() {
    return desconto;
  }

  public TipoDeDescontoPorItemDePedido getTipoDeDescontoPorItemDePedido() {
    return tipoDeDescontoPorItemDePedido;
  }
}
