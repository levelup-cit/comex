package br.com.alura.comex.application.controller;

import br.com.alura.comex.application.controller.dto.ClienteDto;
import br.com.alura.comex.application.controller.dto.ResumoClienteDto;
import br.com.alura.comex.application.controller.form.ClienteForm;
import br.com.alura.comex.application.repository.ClienteRepository;
import br.com.alura.comex.domain.Cliente;
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

@RestController
@RequestMapping("api/clientes")
public class ClienteController {
    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    public ResponseEntity<Page<ResumoClienteDto>> listarTodos(@RequestParam(defaultValue = "0") int pagina){
        Pageable pageable = PageRequest.of(pagina, 5, Sort.Direction.ASC, "nome");
        Page<Cliente> clientes = clienteRepository.findAll(pageable);
        Page<ResumoClienteDto> resumoClientes = ResumoClienteDto.converter(clientes);

        return ResponseEntity.ok().body(resumoClientes);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ClienteDto> cadastrar(@RequestBody @Valid ClienteForm form, UriComponentsBuilder uriBuilder){
        Cliente cliente = form.converter();
        clienteRepository.save(cliente);
        URI uri = uriBuilder.path("/api/clientes/{id}").buildAndExpand(cliente.getId()).toUri();

        return ResponseEntity.created(uri).body(new ClienteDto(cliente));
    }
}
