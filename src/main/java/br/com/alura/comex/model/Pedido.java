package br.com.alura.comex.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
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

    public void adicionarItem(ItemDePedido item) {
        item.setPedido(this);
        this.getItens().add(item);
        this.valorTotal = this.valorTotal.add(item.getValor());
    }

    public List<ItemDePedido> getItens() {
        return listaDePedidos;
    }
}