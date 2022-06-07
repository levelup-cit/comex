package br.com.alura.comex.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "itens_de_pedido")
public class ItemDePedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BigDecimal precoUnitario;

    @Column(nullable = false)
    private long quantidade;
    @ManyToOne
    private Produto produto;
    @ManyToOne
    private Pedido pedido;
    private BigDecimal desconto;
    private TipoDescontoItemPedido tipoDesconto;

    public BigDecimal getValor() {
        return precoUnitario.multiply(new BigDecimal(quantidade));
    }

}