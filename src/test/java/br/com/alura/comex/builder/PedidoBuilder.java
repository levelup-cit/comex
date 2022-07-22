package br.com.alura.comex.builder;

import br.com.alura.comex.dominio.model.entities.Cliente;
import br.com.alura.comex.dominio.model.entities.ItemDePedido;
import br.com.alura.comex.dominio.model.entities.Pedido;
import br.com.alura.comex.dominio.model.enums.TipoDesconto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class PedidoBuilder {

    private LocalDateTime data;

    private Cliente cliente;

    private BigDecimal desconto;

    private TipoDesconto tipoDesconto;

    private List<ItemDePedido> listaDePedidos;

    public PedidoBuilder comData(LocalDateTime data) {
        this.data = data;
        return this;
    }

    public PedidoBuilder comDataAtual() {
        this.data = LocalDateTime.now();
        return this;
    }

    public PedidoBuilder comCliente(Cliente cliente) {
        this.cliente = cliente;
        return this;
    }

    public PedidoBuilder comDesconto(BigDecimal desconto) {
        this.desconto = desconto;
        return this;
    }

    public PedidoBuilder comTipoDesconto(TipoDesconto tipoDesconto) {
        this.tipoDesconto = tipoDesconto;
        return this;
    }

    public PedidoBuilder comListaDePedidos(List<ItemDePedido> listaDePedidos) {
        this.listaDePedidos = listaDePedidos;
        return this;
    }

    public PedidoBuilder aplicarDesconto() {

        this.tipoDesconto = TipoDesconto.NENHUM;
        this.desconto = BigDecimal.ZERO;

        if (cliente.getListaDePedidos() == null) return this;

        if (cliente.getListaDePedidos().size() > 5) {
            this.tipoDesconto = TipoDesconto.FIDELIDADE;
            this.desconto = new BigDecimal(0.5);
        }

        return this;
    }

    public Pedido build() {
        return new Pedido(data, cliente, desconto, tipoDesconto, listaDePedidos);
    }

}
