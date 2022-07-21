package br.com.alura.comex.compartilhado.infra.pedido;

import br.com.alura.comex.compartilhado.config.kafka.PedidoStreamConfig;
import br.com.alura.comex.compartilhado.entity.pedido.Pedido;
import br.com.alura.comex.compartilhado.entity.pedido.PedidoPagoEvent;
import br.com.alura.comex.compartilhado.infra.pedido.dto.PedidoPagoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class PedidoPagoEventImpl implements PedidoPagoEvent {

    private final PedidoStreamConfig.PedidoPagoSource pedidoPagoSource;
    @Override
    public void enviarEventoPedidoPago(Pedido pedido) {

        Message<?> pedidoConfirmadoEvent = MessageBuilder.withPayload(new PedidoPagoDto(pedido)).build();
        pedidoPagoSource.pedidosPagos().send(pedidoConfirmadoEvent);
    }
}
