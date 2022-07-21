package br.com.alura.comex.compartilhado.infra.pedido.dto;

import br.com.alura.comex.compartilhado.adapter.controller.pedido.dto.ItemDePedidoDto;
import br.com.alura.comex.compartilhado.entity.enuns.StatusPedido;
import br.com.alura.comex.compartilhado.entity.pedido.Pedido;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class PedidoPagoDto {

    private Long id;
    private LocalDate data;
    private BigDecimal valorTotal;
    private BigDecimal desconto;
    private List<ItemDePedidoDto> itens;
    private String cpfClient;
    private String nomeCliente;
    private String enderecoCompleto;
    private String estado;
    private String statusPedido = StatusPedido.CONFIRMADO.toString();

    public PedidoPagoDto(Pedido pedido) {
        this.id = pedido.getId();
        this.estado = pedido.getCliente().getEndereco().getEstado();
        this.enderecoCompleto = pedido.getCliente().getEndereco().getEnderecoCompleto();
        this.data = pedido.getData();
        this.valorTotal = pedido.getValorTotalPedido();
        this.desconto = pedido.getDesconto();
        this.itens = pedido.getItens().stream().map(ItemDePedidoDto::new).collect(Collectors.toList());
        this.cpfClient = pedido.getCliente().getCpf().getNumero();
        this.nomeCliente = pedido.getCliente().getNome();
        this.statusPedido = pedido.getStatusPedido().toString();
    }
}
