package br.com.alura.comex.compartilhado.infra.pedido.dto;

import br.com.alura.comex.compartilhado.entity.itemDePedido.ItemDePedido;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class ItemDePedidoPagoDto {
    private Long id;
    private BigDecimal precoUnitario;
    private Integer quantidade;
    private String produto;
    private String categoria;
    private BigDecimal desconto;
    private BigDecimal valorFinal;

    public ItemDePedidoPagoDto(ItemDePedido item) {
        this.id = item.getId();
        this.precoUnitario = item.getPrecoUnitario();
        this.quantidade = item.getQuantidade();
        this.produto = item.getProdutoEstoque().getNome();
        this.categoria = item.getProdutoEstoque().getCategoria().getNome();
        this.desconto = item.getDesconto();
        this.valorFinal = item.getValorTotalItem();
    }

}
