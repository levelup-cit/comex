package br.com.alura.comex.usecase;

import br.com.alura.comex.domain.Pedido;
import br.com.alura.comex.application.model.enuns.TipoDesconto;
import br.com.alura.comex.application.model.enuns.TipoDescontoItem;

import java.math.BigDecimal;

public class Descontos {

    public static void aplicarDesconto(Pedido pedido){
        if (pedido.getQuantidadeDeProdutos() > 10){
            BigDecimal valorDesconto = pedido.getValorTotalPedido().multiply(new BigDecimal("0.1"));
            pedido.getItens().stream().forEach(item -> {
                item.setDesconto(valorDesconto);
                item.setTipoDesconto(TipoDescontoItem.QUANTIDADE);
            });
        }

        if (pedido.getCliente().quantidadePedidos() > 5){
            BigDecimal valorDesconto = pedido.getValorTotalPedido().multiply(new BigDecimal("0.05"));
            pedido.setDesconto(valorDesconto);
            pedido.setTipoDesconto(TipoDesconto.FIDELIDADE);
        }
    }
}
