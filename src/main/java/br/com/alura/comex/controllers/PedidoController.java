package br.com.alura.comex.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alura.comex.controllers.form.PedidoForm;
import br.com.alura.comex.dto.DetalhamentoDePedidoDto;
import br.com.alura.comex.dto.PedidoDto;
import br.com.alura.comex.models.Pedido;
import br.com.alura.comex.repository.ClienteRepository;
import br.com.alura.comex.repository.PedidoRepository;
import br.com.alura.comex.repository.ProdutoRepository;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping
    public ResponseEntity<List<PedidoDto>> listar() {
        Page<Pedido> pedidos = pedidoRepository.findAll(PageRequest.of(0, 5));
        return ResponseEntity.ok(PedidoDto.converter(pedidos.get().toList()));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<PedidoDto> salvar(@RequestBody @Valid PedidoForm pedidoForm,
            UriComponentsBuilder uriBuilder) {

        Pedido pedido = pedidoForm.converter(clienteRepository, produtoRepository, pedidoRepository);
        pedidoRepository.save(pedido);

        URI uri = uriBuilder
                .path("/pedidos/{id}")
                .buildAndExpand(pedido.getId())
                .toUri();

        return ResponseEntity.created(uri).body(new PedidoDto(pedido));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhamentoDePedidoDto> buscar(@PathVariable Long id) {
        Optional<Pedido> pedido = pedidoRepository.findById(id);
        if (pedido.isPresent()) {
            return ResponseEntity.ok(new DetalhamentoDePedidoDto(pedido.get()));
        }
        return ResponseEntity.notFound().build();
    }
}