package br.com.alura.comex.builder;

import br.com.alura.comex.dominio.model.entities.ItemDePedido;
import br.com.alura.comex.dominio.model.entities.Produto;
import br.com.alura.comex.dominio.model.enums.TipoDesconto;

import java.math.BigDecimal;

public class ItemDePedidoBuilder {

    private BigDecimal precoUnitario;
    private long quantidade;

    private Produto produto;

    private BigDecimal desconto = BigDecimal.ZERO;
    private TipoDesconto tipoDesconto;

    public ItemDePedidoBuilder comPrecoUnitario(BigDecimal precoUnitario) {
        this.precoUnitario = precoUnitario;
        return this;
    }

    public ItemDePedidoBuilder comQuantidade(long quantidade) {
        this.quantidade = quantidade;
        return this;
    }

    public ItemDePedidoBuilder comProduto(Produto produto) {
        this.produto = produto;
        return this;
    }

    public ItemDePedidoBuilder aplicarDesconto() {
        if (quantidade > 10) {
            this.tipoDesconto = TipoDesconto.QUANTIDADE;
            this.desconto = BigDecimal.TEN;
        } else {
            this.tipoDesconto = TipoDesconto.NENHUM;
            this.desconto = BigDecimal.ZERO;
        }
        return this;
    }


    public ItemDePedido build() {
        return new ItemDePedido(precoUnitario, quantidade, produto, desconto, tipoDesconto);
    }

}
