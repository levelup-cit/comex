package br.com.alura.comex.entity.Cliente;


import br.com.alura.comex.entity.Pedido.Pedido;
import br.com.alura.comex.entity.Usuario.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Cliente {

    private String nome;

    private CPF cpf;

    private Telefone telefone;

    private Endereco endereco;

    private List<Pedido> pedidos = new ArrayList<>();

    private Usuario usuario;

    public void adicionarPedido(Pedido pedido){
        this.pedidos.add(pedido);
    }

    public BigDecimal getMontanteGasto(){
        return pedidos.stream().map(Pedido::getValorTotalPedido).reduce(BigDecimal.ZERO, BigDecimal::add);
    }


}
