package br.com.alura.comex.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime data;

    @Column(name = "valor_total")
    private BigDecimal valorTotal = BigDecimal.ZERO;

    @ManyToOne(optional = false)
    private Cliente cliente;

    @Column(nullable = false)
    private BigDecimal desconto;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoDescontoPedido tipoDesconto;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<ItemDePedido> listaDePedidos;

    public List<ItemDePedido> getItens() {
        return listaDePedidos;
    }

    public Pedido(LocalDateTime data, Cliente cliente, BigDecimal desconto, TipoDescontoPedido tipoDesconto, List<ItemDePedido> listaDePedidos) {
        this.data = data;
        this.cliente = cliente;
        this.desconto = desconto;
        this.tipoDesconto = tipoDesconto;
        this.listaDePedidos = listaDePedidos;
    }

    public long getQuantidadeItens() {
        return this.listaDePedidos.stream()
                .mapToLong(ItemDePedido::getQuantidade)
                .sum();
    }

    public BigDecimal getValorTotalDescontos() {
        return this.listaDePedidos.stream()
                .map(ItemDePedido::getDesconto)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .add(this.desconto);
    }

    public BigDecimal getValorTotalPedido() {
        return this.listaDePedidos.stream()
                .map(ItemDePedido::getValorTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .subtract(this.getValorTotalDescontos());
    }

}
