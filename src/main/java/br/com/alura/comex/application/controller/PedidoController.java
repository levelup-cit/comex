package br.com.alura.comex.application.controller;

import br.com.alura.comex.application.controller.dto.DetalhesPedidoDto;
import br.com.alura.comex.application.controller.dto.PedidoDto;
import br.com.alura.comex.application.controller.form.PedidoForm;
import br.com.alura.comex.application.repository.ClienteRepository;
import br.com.alura.comex.application.repository.ItemDePedidoRepository;
import br.com.alura.comex.application.repository.PedidoRepository;
import br.com.alura.comex.application.repository.ProdutoRepository;
import br.com.alura.comex.domain.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("api/pedidos")
public class PedidoController {
    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ItemDePedidoRepository itemDePedidoRepository;

    @GetMapping
    public ResponseEntity<Page<PedidoDto>> listarTodos(@RequestParam(defaultValue = "0") int pagina){
        Pageable pageable = PageRequest.of(pagina, 5, Sort.Direction.DESC, "data");
        Page<Pedido> pedidos = pedidoRepository.findAll(pageable);
        Page<PedidoDto> paginaPedidos = PedidoDto.converter(pedidos);

        return ResponseEntity.ok().body(paginaPedidos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhesPedidoDto> buscarPedidoPorId(@PathVariable Long id){
        Optional<Pedido> optionalPedido= pedidoRepository.findById(id);
        if (optionalPedido.isPresent()){
            return ResponseEntity.ok().body(new DetalhesPedidoDto(optionalPedido.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DetalhesPedidoDto> cadastrar(@RequestBody @Valid PedidoForm form, UriComponentsBuilder uriComponentsBuilder){
        Pedido pedido = form.converter(clienteRepository, produtoRepository);
        pedidoRepository.save(pedido);
        URI uri = uriComponentsBuilder.path("/api/pedidos/{id}").buildAndExpand(pedido.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesPedidoDto(pedido));

    }
}
