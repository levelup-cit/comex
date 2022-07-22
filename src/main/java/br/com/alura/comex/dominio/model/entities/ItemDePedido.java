package br.com.alura.comex.dominio.model.entities;


import br.com.alura.comex.dominio.model.enums.TipoDesconto;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "itensDePedidos")
public class ItemDePedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Long id;
    @NotNull
    private BigDecimal precoUnitario = BigDecimal.ZERO;
    @NotNull
    private int quantidade;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "produto_id")
    @NotNull
    private Produto produto;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pedido_id")
    @NotNull
    private Pedido pedido;
    private BigDecimal desconto;
    private TipoDesconto tipoDesconto;

    public ItemDePedido(BigDecimal precoUnitario, long quantidade, Produto produto, BigDecimal desconto, TipoDesconto tipoDesconto) {

    }

    public ItemDePedido() {
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public BigDecimal getDesconto() {
        return desconto;
    }

    public void setDesconto(BigDecimal desconto) {
        this.desconto = desconto;
    }

    public void setPrecoUnitario(BigDecimal precoUnitario) {
        this.precoUnitario = precoUnitario;
    }


    public TipoDesconto getTipoDeDesconto() {
        return tipoDesconto;
    }

    public void setTipoDeDesconto(TipoDesconto tipoDeDesconto) {
        this.tipoDesconto= tipoDeDesconto;
    }

    public BigDecimal getValor() {
        return precoUnitario.multiply(new BigDecimal(quantidade));
    }
}
