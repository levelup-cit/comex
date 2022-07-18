package br.com.alura.comex.application.controller;

import br.com.alura.comex.application.controller.form.AtualizacaoCategoriaForm;
import br.com.alura.comex.application.controller.dto.CategoriaDto;
import br.com.alura.comex.application.controller.dto.DetalhesCategoriaDto;
import br.com.alura.comex.application.model.projecao.PedidosPorCategoriaProjecao;
import br.com.alura.comex.application.controller.form.CategoriaForm;
import br.com.alura.comex.domain.Categoria;
import br.com.alura.comex.application.repository.CategoriaRepository;
import br.com.alura.comex.application.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/categorias")
public class CategoriaController {
    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @GetMapping
    public List<CategoriaDto> listarTodos(){
        List<Categoria> categorias = categoriaRepository.findAll();
        return CategoriaDto.converter(categorias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhesCategoriaDto> buscarCategoriaPorId(@PathVariable Long id) {
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        if (categoria.isPresent()) {
            return ResponseEntity.ok().body(new DetalhesCategoriaDto(categoria.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/pedidos")
    @Cacheable(value = "pedidosPorCategoria")
    public ResponseEntity<List<PedidosPorCategoriaProjecao>> pedidosVendidosPorCategoria(){
        List<PedidosPorCategoriaProjecao> pedidosPorCategoriaRelatorio = pedidoRepository.findPedidosPorCategoria();
        return ResponseEntity.ok(pedidosPorCategoriaRelatorio);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<CategoriaDto> cadastrar(@RequestBody @Valid CategoriaForm form, UriComponentsBuilder uriBuilder) {
        Categoria categoria = form.converter();
        categoriaRepository.save(categoria);

        URI uri = uriBuilder.path("/categorias/{id}").buildAndExpand(categoria.getId()).toUri();
        return ResponseEntity.created(uri).body(new CategoriaDto(categoria));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<CategoriaDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoCategoriaForm form) {
        Optional<Categoria> optional = categoriaRepository.findById(id);
        if (optional.isPresent()) {
            Categoria categoria = form.atualizar(id, categoriaRepository);
            return ResponseEntity.ok(new CategoriaDto(categoria));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> remover(@PathVariable Long id) {
        Optional<Categoria> optional = categoriaRepository.findById(id);
        if (optional.isPresent()) {
            categoriaRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
