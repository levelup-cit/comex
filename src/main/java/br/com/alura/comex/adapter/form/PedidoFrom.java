package br.com.alura.comex.adapter.form;
import br.com.alura.comex.entity.cliente.Cliente;
import br.com.alura.comex.entity.pedido.Pedido;
import br.com.alura.comex.infra.cliente.ClienteRepositoryImpl;
import br.com.alura.comex.infra.produto.ProdutoRepositoryImpl;
import br.com.alura.comex.usecase.Descontos;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PedidoFrom {

    @NotNull
    @Min(0)
    private Long idCliente;

    @NotNull
    private List<ItemDePedidoForm> itens;

    public Pedido converter(ClienteRepositoryImpl clienteRepository, ProdutoRepositoryImpl produtoRepository) {
         Cliente cliente = clienteRepository.buscarPorId(this.idCliente);

         Pedido pedido = new Pedido(cliente);

         cliente.adicionarPedido(pedido);

         this.itens.forEach(p -> {
             pedido.adicionarItem(p.converter(produtoRepository));
         });

        Descontos.aplicarDesconto(pedido);

        return pedido;
    }
}
