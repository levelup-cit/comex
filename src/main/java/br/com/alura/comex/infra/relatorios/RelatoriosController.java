package br.com.alura.comex.infra.relatorios;

import br.com.alura.comex.aplicacao.relatorios.RelatorioPedidoPorCategoriaProjecao;
import br.com.alura.comex.dominio.pedido.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("categorias/pedidos")
public class RelatoriosController {

  @Autowired
  private PedidoRepository pedidoRepository;

  @GetMapping
  @Cacheable({"relatorioPorCategoria"})
  public List<RelatorioPedidoPorCategoriaProjecao> pesquisaRelatorioPorCategoria() {
    List<RelatorioPedidoPorCategoriaProjecao> list = pedidoRepository.gerarRelatorioDePedidosPorCategoria();
    list.forEach(pedido -> System.out.println("Pedido: nome_da_categoria: "
            + pedido.getNomeCategoria()
            + " | quantidade_produtos_vendidos: " + pedido.getQuantidadeProdutosVendidos()
            + " | montante_vendido: " + pedido.getMontanteVendido()));
    return list;
  }
}
