package br.com.alura.comex.controller;

import br.com.alura.comex.dominio.model.entities.Pedido;
import br.com.alura.comex.infra.repository.PedidoRepository;
import br.com.alura.comex.a.service.PedidoService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/pedido")
@CrossOrigin(origins = "*")
public class PedidoController {

    @Autowired
    PedidoRepository pedidoRepository;
    @Autowired
    PedidoService pedidoService;

    @GetMapping
    @Cacheable(value = "listaDePedidos")
    @Operation(summary = "Lista todos os pedidos")
    public ResponseEntity<List<Pedido>> listarTodos() {
        List<Pedido> lista = pedidoService.listarTodos();
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @GetMapping("/page")
    @Cacheable(value = "listaPaginadaDePedidos")
    @Operation(summary = "Lista paginada de todos os pedidos")
    public ResponseEntity<Page<Pedido>> obterPagina(
            @RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "5") Integer linhasPorPage,
            @RequestParam(defaultValue = "nome") String ordenarPor, @RequestParam(defaultValue = "ASC") String direcao){
        Page<Pedido> listaPaginada = pedidoService.obterPagina(page, linhasPorPage, ordenarPor, direcao);

        return ResponseEntity.ok().body(listaPaginada);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Lista o pedido com ID correspondente")
    public ResponseEntity<Pedido> listarPorCodigo(@PathVariable Long id) {
        Pedido pedido = pedidoService.listarPorCodigo(id);
        return new ResponseEntity<Pedido>(pedido, HttpStatus.OK);

    }

    @PostMapping("/cadastro")
    @CacheEvict(value = {"listaDeClientes", "listaPaginadaDeClientes"}, allEntries = true)
    @Operation(summary = "Insere um pedido no banco de dados")
    public ResponseEntity<Pedido> inserir(@RequestBody @Valid Pedido pedido) {
        pedidoService.inserir(pedido);
        return new ResponseEntity<>(pedido, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @CacheEvict(value = {"listaDeClientes", "listaPaginadaDeClientes"}, allEntries = true)
    @Operation(summary = "Atualiza o pedido com ID correspondente")
    public ResponseEntity<Pedido> atualizar(@Valid @PathVariable Long id, @RequestBody Pedido pedido) {
        boolean pedidoExiste = this.pedidoRepository.existsById(pedido.getId());

        if (!pedidoExiste) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        pedido.setId(id);
        pedido = pedidoService.atualizar(pedido);

        return new ResponseEntity<>(pedido, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @CacheEvict(value = {"listaDeClientes", "listaPaginadaDeClientes"}, allEntries = true)
    @Operation(summary = "Remove o pedido com ID correspondente")
    public ResponseEntity<Void> remover(@PathVariable Long id) {

        if (!pedidoRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        pedidoService.remover(id);
        return ResponseEntity.noContent().build();
    }
}
