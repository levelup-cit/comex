package br.com.alura.comex.adapter.controller.categoria;


import br.com.alura.comex.adapter.dto.categorias.CategoriaDto;
import br.com.alura.comex.adapter.dto.categorias.DetalhesDaCategoriaDto;
import br.com.alura.comex.adapter.form.AtualizacaoCategoriaForm;
import br.com.alura.comex.adapter.form.CategoriaForm;
import br.com.alura.comex.entity.categoria.Categoria;
import br.com.alura.comex.infra.categoria.CategoriaEntity;
import br.com.alura.comex.infra.categoria.CategoriaRepositoryImpl;
import br.com.alura.comex.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaRepositoryImpl categoriaRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @GetMapping
    public ResponseEntity<List<CategoriaDto>> listar() {
        List<Categoria> categorias = categoriaRepository.listarTodasCategorias();

        List<CategoriaEntity> categoriaEntities = categorias.stream().map(CategoriaEntity::converter).toList();

        return ResponseEntity.ok(CategoriaDto.converter(categoriaEntities));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<CategoriaDto> cadastrar(@RequestBody @Valid CategoriaForm form, UriComponentsBuilder uriComponentsBuilder) {

        Categoria categoria = form.converter();

        categoriaRepository.cadastrarCategoria(categoria);

        URI uri = uriComponentsBuilder.path("/api/categorias/{id}").buildAndExpand(categoria.getId()).toUri();
        return ResponseEntity.created(uri).body(new CategoriaDto(CategoriaEntity.converter(categoria)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhesDaCategoriaDto> buscarCategoriaPorId(@PathVariable Long id) {
        Categoria categoria = categoriaRepository.buscarCategoria(id);

        CategoriaEntity categoriaEntity = CategoriaEntity.converter(categoria);

        return ResponseEntity.ok().body(new DetalhesDaCategoriaDto(categoriaEntity));

    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<CategoriaDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoCategoriaForm form) {

        Categoria categoriaAtualizada = categoriaRepository.atualizar(id, form.getNome());

        return ResponseEntity.ok(new CategoriaDto(CategoriaEntity.converter(categoriaAtualizada)));

    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> remover(@PathVariable Long id) {

        categoriaRepository.deletarCategoria(id);

        return ResponseEntity.ok().build();

    }

    @PatchMapping("/{id}")
    @Transactional
    public ResponseEntity<CategoriaDto> atualizarStatus(@PathVariable Long id) {

        Categoria categoriaAtualizada = categoriaRepository.atualizarStatus(id);

        return ResponseEntity.ok(new CategoriaDto(CategoriaEntity.converter(categoriaAtualizada)));
    }

}
