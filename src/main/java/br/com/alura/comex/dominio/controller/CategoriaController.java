package br.com.alura.comex.controller;

import br.com.alura.comex.dominio.model.entities.Categoria;
import br.com.alura.comex.infra.repository.CategoriaRepository;
import br.com.alura.comex.a.service.CategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/categoria")
@CrossOrigin(origins = "*")
public class CategoriaController {

    @Autowired
    CategoriaRepository categoriaRepository;
    @Autowired
    CategoriaService categoriaService;

    @GetMapping
    @Operation(summary = "Lista todas as categorias")
    public ResponseEntity<List<Categoria>> listarTodos() {
        List<Categoria> lista = categoriaService.listarTodos();
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @GetMapping("/page")
    @Operation(summary = "Lista paginada de todas as categorias")
    public ResponseEntity<Page<Categoria>> obterPagina(
            @RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "5") Integer linhasPorPage,
            @RequestParam(defaultValue = "nome") String ordenarPor, @RequestParam(defaultValue = "ASC") String direcao){
        Page<Categoria> listaPaginada = categoriaService.obterPagina(page, linhasPorPage, ordenarPor, direcao);

        return ResponseEntity.ok().body(listaPaginada);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Lista a categoria com ID correspondente")
    public ResponseEntity<Categoria> listarPorCodigo(@PathVariable Long id) {
        Categoria categoria = categoriaService.listarPorCodigo(id);
        return new ResponseEntity<>(categoria, HttpStatus.OK);

    }

    @PostMapping("/cadastro")
    @Operation(summary = "Insere uma categoria no banco de dados")
    public ResponseEntity<Categoria> inserir(@RequestBody @Valid Categoria categoria) {
        categoriaService.inserir(categoria);
        return new ResponseEntity<>(categoria, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza a categoria com ID correspondente")
    public ResponseEntity<Categoria> atualizar(@Valid @PathVariable Long id, @RequestBody Categoria categoria) {
        boolean categoriaExiste = this.categoriaRepository.existsById(categoria.getId());

        if (!categoriaExiste) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        categoria.setId(id);
        categoria = categoriaService.atualizar(categoria);
        return new ResponseEntity<>(categoria, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Altera o status da categoria com ID correspondente")
    public ResponseEntity<Categoria> alterarStatus(@Valid @PathVariable Long id, Categoria categoria) {
        boolean categoriaExiste = this.categoriaRepository.existsById(categoria.getId());

        if (!categoriaExiste) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        categoria.setId(id);
        categoria = categoriaService.alterarStatus(categoria);
        return new ResponseEntity<>(categoria, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Remove a categoria com ID correspondente")
    public ResponseEntity<Void> remover(@PathVariable Long id) {

        if (!categoriaRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        categoriaService.remover(id);
        return ResponseEntity.noContent().build();
    }
}
