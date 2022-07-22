package br.com.alura.comex.controller;

import br.com.alura.comex.dominio.model.entities.Cliente;
import br.com.alura.comex.infra.repository.ClienteRepository;
import br.com.alura.comex.a.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/cliente")
@CrossOrigin(origins = "*")
public class ClienteController {

    @Autowired
    ClienteRepository clienteRepository;
    @Autowired
    ClienteService clienteService;

    @GetMapping
    @Operation(summary = "Lista todos os clientes")
    public ResponseEntity<List<Cliente>> listarTodos() {
        List<Cliente> lista = clienteService.listarTodos();
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @GetMapping("/page")
    @Operation(summary = "Lista paginada de todos os clientes")
    public ResponseEntity<Page<Cliente>> obterPagina(
            @RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "5") Integer linhasPorPage,
            @RequestParam(defaultValue = "nome") String ordenarPor, @RequestParam(defaultValue = "ASC") String direcao){
        Page<Cliente> listaPaginada = clienteService.obterPagina(page, linhasPorPage, ordenarPor, direcao);

        return ResponseEntity.ok().body(listaPaginada);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Lista o cliente com ID correspondente")
    public ResponseEntity<Cliente> listarPorCodigo(@PathVariable Long id) {
        Cliente cliente = clienteService.listarPorCodigo(id);
        return new ResponseEntity<>(cliente, HttpStatus.OK);

    }

    @PostMapping("/cadastro")
    @Operation(summary = "Insere um cliente no banco de dados")
    public ResponseEntity<Cliente> inserir(@RequestBody @Valid Cliente cliente) {
        clienteService.inserir(cliente);
        return new ResponseEntity<>(cliente, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza o cliente com ID correspondente")
    public ResponseEntity<Cliente> atualizar(@Valid @PathVariable Long id, @RequestBody Cliente cliente) {
        boolean clienteExiste = this.clienteRepository.existsById(cliente.getId());

        if (!clienteExiste) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        cliente.setId(id);
        cliente = clienteService.atualizar(cliente);

        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Remove o cliente com ID correspondente")
    public ResponseEntity<Void> remover(@PathVariable Long id) {

        if (!clienteRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        clienteService.remover(id);
        return ResponseEntity.noContent().build();
    }
}
