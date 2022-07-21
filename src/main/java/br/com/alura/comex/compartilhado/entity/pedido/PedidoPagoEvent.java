package br.com.alura.comex.compartilhado.entity.pedido;

public interface PedidoPagoEvent {

    void enviarEventoPedidoPago(Pedido pedido);
}
